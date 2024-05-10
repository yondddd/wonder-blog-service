package com.yond.common.utils.json.adapter.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.yond.common.utils.json.adapter.DateAdapter;
import com.yond.common.utils.json.adapter.DateInitManager;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * LocalDateTimeTypeAdapter
 **/
public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> implements DateAdapter {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * List of 1 or more different date formats used for de-serialization attempts.
     * The first of them (default US format) is used for serialization as well.
     */
    private final List<DateFormat> dateFormats = new ArrayList<DateFormat>();

    private final SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");

    public LocalDateTimeTypeAdapter() {
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

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        String string = in.nextString();
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(string, DEFAULT_FORMATTER);
        } catch (Throwable ex) {
            final Date date = convert(string, dateFormats, yearDateFormat);
            Instant instant = date.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            dateTime = LocalDateTime.ofInstant(instant, zone);
        }
        return dateTime;
    }

    @Override
    public synchronized void write(JsonWriter out, LocalDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        out.value(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}