package com.wish.common.utils.json.adapter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * DateInitManager
 **/
public class DateInitManager {

    private static final List<String> dateFormats = new ArrayList<>();

    private static volatile boolean init;

    static {
        dateFormats.add("yyyy-MM-dd");
        dateFormats.add("yyyy/MM/dd");
        dateFormats.add("yyyy/MM/dd HH:mm:ss");
        dateFormats.add("yyyy-MM-dd HH:mm:ss");
        dateFormats.add("yyyy年MM月dd日 HH时mm分ss秒");
        dateFormats.add("yyyyMMdd HHmmss");
        dateFormats.add("yyyy-MMM-ddd hhh:mmm:sss a E");
        dateFormats.add("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        dateFormats.add("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormats.add("EEE, dd MMM yyyy HH:mm:ss zzz");
        dateFormats.add("yyyy-MM");
        dateFormats.add("yyyy/MM");
        dateFormats.add("yyyy.MM");
    }

    public static List<String> getDateFormats() {
        return dateFormats;
    }

    private static void addDateFormat(String dateFormat) {
        if (dateFormats.contains(dateFormat)) {
            return;
        }

        dateFormats.add(dateFormat);
    }

    public static void init() {
        if (init) {
            return;
        }

        synchronized (DateInitManager.class) {
            if (init) {
                return;
            }

            final String dateFormats = "";
            if (StringUtils.isBlank(dateFormats)) {
                return;
            }

            final String[] splits = dateFormats.split(",");
            if (ArrayUtils.isEmpty(splits)) {
                return;
            }

            for (final String v : splits) {
                if (StringUtils.isBlank(v)) {
                    continue;
                }

                addDateFormat(v.trim());
            }
            init = true;
        }
    }
}
