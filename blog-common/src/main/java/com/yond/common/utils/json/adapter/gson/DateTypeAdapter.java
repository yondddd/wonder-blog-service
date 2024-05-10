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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * DateTypeAdapter
 **/
public class DateTypeAdapter extends TypeAdapter<Date> implements DateAdapter {

    /**
     * List of 1 or more different date formats used for de-serialization attempts.
     * The first of them (default US format) is used for serialization as well.
     */
    private final List<DateFormat> dateFormats = new ArrayList<DateFormat>();

    private final SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");

    public DateTypeAdapter() {

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

    public static void main(String[] args) {
        DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();
        System.out.println(dateTypeAdapter);
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        return convert(in.nextString(), dateFormats, yearDateFormat);
    }

    @Override
    public synchronized void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        out.value(value.getTime());
    }
}