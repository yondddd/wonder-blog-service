package com.yond.common.utils.json.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.yond.common.resp.PageResponse;
import com.yond.common.resp.Response;
import com.yond.common.utils.json.adapter.DateInitManager;
import com.yond.common.utils.json.adapter.gson.DateTypeAdapter;
import com.yond.common.utils.json.adapter.gson.LocalDateTimeTypeAdapter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 简单封装Gson
 **/
public class GsonUtil {

    private static final Gson gson;

    private static Gson prettyGson;

    static {
        DateInitManager.init();
        gson = getGB()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

    }

    private static GsonBuilder getGB() {
        return new GsonBuilder();
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return gson.fromJson(json, typeOfT);
    }

    public static <T> Response<T> toResponse(String json, Class<T> clazz) {
        Type typeToken = TypeToken.getParameterized(Response.class, clazz).getType();
        return gson.fromJson(json, typeToken);
    }

    public static <T> PageResponse<T> toPageableResponse(String json, Class<T> clazz) {
        Type typeToken = TypeToken.getParameterized(Response.class, clazz).getType();
        return gson.fromJson(json, typeToken);
    }


    public static <T> T fromJson(JsonElement jsonElement, Class<T> classOfT) {
        return gson.fromJson(jsonElement, classOfT);
    }

    public static <T> T fromJson(JsonElement jsonElement, Type typeOfT) {
        return gson.fromJson(jsonElement, typeOfT);
    }

    public static String toJson(Object o) {
        if (o instanceof String) {
            return (String) o;
        }
        return gson.toJson(o);
    }

    public static String toPrettyPrintJson(Object o) {
        checkPrettyGB();
        return prettyGson.toJson(o);
    }

    private static void checkPrettyGB() {
        if (prettyGson != null) {
            return;
        }

        synchronized (GsonUtil.class) {
            if (prettyGson == null) {
                prettyGson =
                        getGB()
                                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                                .setPrettyPrinting().create();
            }
        }
    }

    public static boolean isValidJson(String str) {
        try {
            gson.fromJson(str, Object.class);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static Gson getGson() {
        return gson;
    }

    public static JsonElement parseJson(String json) {
        return new JsonParser().parse(json);
    }
}