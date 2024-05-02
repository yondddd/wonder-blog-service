package com.yond.utils.json.adapter.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.yond.utils.json.adapter.DateAdapter;
import com.yond.utils.json.adapter.DateInitManager;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DateDeserializer
 **/
public class DateDeserializer extends StdScalarDeserializer<Date> implements ContextualDeserializer, DateAdapter {

    /**
     * List of 1 or more different date formats used for de-serialization attempts.
     * The first of them (default US format) is used for serialization as well.
     */
    private final List<DateFormat> dateFormats = new ArrayList<DateFormat>();

    private final SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");

    /**
     * Let's also keep format String for reference, to use for error messages
     */
    protected final String _formatString;

    public static DateDeserializer getDateDeserializer() {
        return new DateDeserializer();
    }

    protected DateDeserializer() {
        super(Date.class);
        _formatString = null;
        init();
    }

    protected DateDeserializer(DateDeserializer base, String formatStr) {
        super(base._valueClass);
        _formatString = formatStr;
        init();
    }

    private void init() {
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        dateFormats.add(DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            dateFormats.add(DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT));
        }

        if (JavaVersion.isJava9OrLater()) {
            dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(DateFormat.DEFAULT, DateFormat.DEFAULT));
        }

        List<String> dateFormats = DateInitManager.getDateFormats();
        if (CollectionUtils.isNotEmpty(dateFormats)) {
            for (String dateFormat : dateFormats) {
                this.dateFormats.add(new SimpleDateFormat(dateFormat));
            }
        }
    }

    public DateDeserializer withDateFormat(DateFormat df, String formatString) {
        return new DateDeserializer(this, formatString);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        final JsonFormat.Value format = findFormatOverrides(ctxt, property, handledType());
        if (format != null) {
            TimeZone tz = format.getTimeZone();
            final Boolean lenient = format.getLenient();

            // First: fully custom pattern?
            if (format.hasPattern()) {
                final String pattern = format.getPattern();
                final Locale loc = format.hasLocale() ? format.getLocale() : ctxt.getLocale();
                SimpleDateFormat df = new SimpleDateFormat(pattern, loc);
                if (tz == null) {
                    tz = ctxt.getTimeZone();
                }
                df.setTimeZone(tz);
                if (lenient != null) {
                    df.setLenient(lenient);
                }
                return withDateFormat(df, pattern);
            }
            // But if not, can still override timezone
            if (tz != null) {
                DateFormat df = ctxt.getConfig().getDateFormat();
                // one shortcut: with our custom format, can simplify handling a bit
                if (df.getClass() == StdDateFormat.class) {
                    final Locale loc = format.hasLocale() ? format.getLocale() : ctxt.getLocale();
                    StdDateFormat std = (StdDateFormat) df;
                    std = std.withTimeZone(tz);
                    std = std.withLocale(loc);
                    if (lenient != null) {
                        std = std.withLenient(lenient);
                    }
                    df = std;
                } else {
                    // otherwise need to clone, re-set timezone:
                    df = (DateFormat) df.clone();
                    df.setTimeZone(tz);
                    if (lenient != null) {
                        df.setLenient(lenient);
                    }
                }
                return withDateFormat(df, _formatString);
            }
            // or maybe even just leniency?
            if (lenient != null) {
                DateFormat df = ctxt.getConfig().getDateFormat();
                String pattern = _formatString;
                // one shortcut: with our custom format, can simplify handling a bit
                if (df.getClass() == StdDateFormat.class) {
                    StdDateFormat std = (StdDateFormat) df;
                    std = std.withLenient(lenient);
                    df = std;
                    pattern = std.toPattern();
                } else {
                    // otherwise need to clone,
                    df = (DateFormat) df.clone();
                    df.setLenient(lenient);
                    if (df instanceof SimpleDateFormat) {
                        ((SimpleDateFormat) df).toPattern();
                    }
                }
                if (pattern == null) {
                    pattern = "[unknown]";
                }
                return withDateFormat(df, pattern);
            }
        }
        return this;
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentTokenId() == JsonTokenId.ID_NULL) {
            return getNullValue(ctxt);
        }
        return _parseDate(p, ctxt);
    }

    @Override
    protected Date _parseDate(JsonParser p, DeserializationContext ctxt) throws IOException {
        return convert(p.getText(), dateFormats, yearDateFormat);
    }
}
