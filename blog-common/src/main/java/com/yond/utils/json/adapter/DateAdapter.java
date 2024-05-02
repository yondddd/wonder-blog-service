package com.yond.utils.json.adapter;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.List;

/**
 * DateAdapter
 **/
public interface DateAdapter {

    default Date convert(final String json, List<DateFormat> dateFormats, DateFormat yearDateFormat) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        synchronized (dateFormats) {
            for (DateFormat dateFormat : dateFormats) {
                try {
                    return dateFormat.parse(json);
                } catch (ParseException ignored) {
                }
            }

            //如果是4个字符则直接按年份处理
            if (json.length() == 4 && yearDateFormat != null) {
                try {
                    return yearDateFormat.parse(json);
                } catch (ParseException ignored) {
                }
            }
        }

        try {
            return new Date(Long.valueOf(json));
        } catch (NumberFormatException ignored) {
            //
        }

        try {
            return ISO8601Utils.parse(json, new ParsePosition(0));
        } catch (ParseException e) {
            throw new JsonSyntaxException(json, e);
        }
    }
}
