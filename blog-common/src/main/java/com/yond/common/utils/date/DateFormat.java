package com.yond.common.utils.date;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DateFormat
 * <p>
 *
 * @version 1.0
 * @created 2021/12/08 00:29
 **/
public class DateFormat {

    private final List<SimpleDateFormat> dateFormats = new ArrayList<>();

    private final SimpleDateFormat outputDateFormat;

    private SimpleDateFormat yearDateFormat;

    public DateFormat(final String formatString) {
        this.outputDateFormat = new SimpleDateFormat(formatString);
        this.init();
    }

    private void init() {
        dateFormats.add(outputDateFormat);
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));
        dateFormats.add(new SimpleDateFormat("yyyy/MM/dd"));
        dateFormats.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        dateFormats.add(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));
        dateFormats.add(new SimpleDateFormat("yyyyMMdd HHmmss"));
        dateFormats.add(new SimpleDateFormat("yyyy-MMM-ddd hhh:mmm:sss a E"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        dateFormats.add(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz"));
        dateFormats.add(new SimpleDateFormat("yyyy-MM"));
        dateFormats.add(new SimpleDateFormat("yyyy/MM"));
        dateFormats.add(new SimpleDateFormat("yyyy.MM"));
        yearDateFormat = new SimpleDateFormat("yyyy");
    }

    public final String format(Date date) {
        return outputDateFormat.format(date);
    }

    public Date parse(String json) throws ParseException {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        for (java.text.DateFormat dateFormat : dateFormats) {
            try {
                return dateFormat.parse(json);
            } catch (ParseException ignored) {
            }
        }

        //如果是4个字符则直接按年份处理
        if (json.length() == 4) {
            try {
                return yearDateFormat.parse(json);
            } catch (ParseException ignored) {
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
            throw e;
        }
    }
}
