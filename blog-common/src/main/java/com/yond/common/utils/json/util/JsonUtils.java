package com.yond.common.utils.json.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonSyntaxException;
import com.yond.common.resp.PageableResult;
import com.yond.common.resp.Result;
import com.yond.common.utils.json.adapter.DateInitManager;
import com.yond.common.utils.json.adapter.jackson.DateDeserializer;
import com.yond.common.utils.json.adapter.jackson.LocalDateTimeDeserializer;
import com.yond.common.utils.json.adapter.jackson.LocalDateTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 简单封装Jackson
 **/
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    /**
     * defaultMapper
     */
    private static final JsonMapper DEFAULT_MAPPER = new JsonMapper();

    /**
     * non default mapper
     */
    private static final JsonMapper NON_DEFAULT_MAPPER = DEFAULT_MAPPER.nonDefaultMapper();

    /**
     * non null mapper
     */
    private static final JsonMapper NON_NULL_MAPPER = DEFAULT_MAPPER.nonNullMapper();

    public static JsonMapper getDefaultMapper() {
        return DEFAULT_MAPPER;
    }

    public static String toJson(Object obj) {
        return DEFAULT_MAPPER.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return DEFAULT_MAPPER.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, JavaType clazz) {
        return DEFAULT_MAPPER.fromJson(json, clazz);
    }

    public static <T> Result<T> toResponse(String json, Class<T> clazz) {
        JavaType javaType = getDefaultMapper().constructParametricType(Result.class, clazz);
        return DEFAULT_MAPPER.fromJson(json, javaType);
    }

    public static <T> Result<T> toResponse(String json, JavaType javaType) {
        return DEFAULT_MAPPER.fromJson(json, javaType);
    }

    public static <T> PageableResult<T> toPageableResponse(String json, Class<T> clazz) {
        JavaType javaType = getDefaultMapper().constructParametricType(PageableResult.class, clazz);
        return DEFAULT_MAPPER.fromJson(json, javaType);
    }

    public static <T> PageableResult<T> toPageableResponse(String json, JavaType javaType) {
        return DEFAULT_MAPPER.fromJson(json, javaType);
    }

    public static JavaType toJavaType(Class<?> parametrized, Class<?>... parameterClasses) {
        return getDefaultMapper().constructParametricType(parametrized, parameterClasses);
    }

    public static JavaType toJavaType(Class<?> parametrized, JavaType... parameterClasses) {
        return getDefaultMapper().constructParametricType(parametrized, parameterClasses);
    }

    public static String toJsonP(String func, Object obj) {
        return DEFAULT_MAPPER.toJsonP(func, obj);
    }

    public static String toJsonIgnoreNull(Object object) {
        return NON_NULL_MAPPER.toJson(object);
    }

    public static String toJsonIgnoreNonDefault(Object object) {
        return NON_DEFAULT_MAPPER.toJson(object);
    }

    public static boolean isValidJson(String str) {
        try {
            DEFAULT_MAPPER.fromJson(str, Object.class);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        }
    }

    public static String toJsonpIgnoreNull(String func, Object object) {
        return NON_DEFAULT_MAPPER.toJsonP(func, object);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        JavaType collectionType = DEFAULT_MAPPER.constructCollectionType(List.class, clazz);
        return DEFAULT_MAPPER.fromJson(json, collectionType);
    }

    public static <T> Map<String, T> parseMap(String json, Class<T> clazz) {
        final JavaType type = JsonUtils.getDefaultMapper().constructMapType(Map.class, String.class, clazz);
        return DEFAULT_MAPPER.fromJson(json, type);
    }

    /**
     * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
     * <p>
     * 封装不同的输出风格, 使用不同的builder函数创建实例.
     *
     * @author calvin
     */
    public static class JsonMapper {

        /**
         * objectmapper mapper
         */
        private final ObjectMapper mapper;

        public JsonMapper() {
            this(null);
        }

        public JsonMapper(JsonInclude.Include include) {
            DateInitManager.init();
            mapper = new ObjectMapper();
            // 设置输出时包含属性的风格
            if (include != null) {
                mapper.setSerializationInclusion(include);
            }
            JavaTimeModule timeModule = new JavaTimeModule();
            mapper.registerModule(timeModule);
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addDeserializer(Date.class, DateDeserializer.getDateDeserializer());
            simpleModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
            simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
            mapper.registerModule(simpleModule);
            // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

        /**
         * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
         */
        public JsonMapper nonEmptyMapper() {
            return new JsonMapper(JsonInclude.Include.NON_EMPTY);
        }

        /**
         * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
         */
        public JsonMapper nonDefaultMapper() {
            return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
        }

        /**
         * 创建只输出非Null的属性到Json字符串的Mapper，较为节约的存储方式，建议在内部接口，压缩日志输出中使用。
         */
        public JsonMapper nonNullMapper() {
            return new JsonMapper(JsonInclude.Include.NON_NULL);
        }

        /**
         * Object可以是POJO，也可以是Collection或数组。
         * 如果对象为Null, 返回"null".
         * 如果集合为空集合, 返回"[]".
         */
        public String toJson(Object object) {
            try {
                if (object instanceof String) {
                    return (String) object;
                }
                return mapper.writeValueAsString(object);
            } catch (IOException e) {
                logger.warn("write to json string error:" + object, e);
                throw new RuntimeException(e);
            }
        }

        /**
         * 反序列化POJO或简单Collection如List《String》.
         * <p>
         * 如果JSON字符串为Null或"null"字符串, 返回Null.
         * 如果JSON字符串为"[]", 返回空集合.
         * <p>
         * 如需反序列化复杂Collection如List#MyBean#, 请使用fromJson(String, JavaType)
         *
         * @see #fromJson(String, JavaType)
         */
        public <T> T fromJson(String jsonString, Class<T> clazz) {
            if (StringUtils.isBlank(jsonString)) {
                return null;
            }

            try {
                return mapper.readValue(jsonString, clazz);
            } catch (IOException e) {
                logger.warn("parse json string error:" + jsonString, e);
                throw new RuntimeException(e);
            }
        }

        /**
         * 反序列化复杂Collection如List《Bean》, 先使用#constructCollectionType()或contructMapType()构造类型, 然后调用本函数.
         */
        @SuppressWarnings("unchecked")
        public <T> T fromJson(String jsonString, JavaType javaType) {
            if (StringUtils.isBlank(jsonString)) {
                return null;
            }

            try {
                return mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                logger.warn("parse json string error:" + jsonString, e);
                throw new RuntimeException(e);
            }
        }

        /**
         * 构造Collection类型.
         */
        public JavaType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
            return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
        }

        /**
         * 构造Map类型.
         */
        public JavaType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
            return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
        }

        /**
         * 构造类型.
         */
        public JavaType constructParametricType(Class<?> rawType, Class... parameterTypes) {
            return mapper.getTypeFactory().constructParametricType(rawType, parameterTypes);
        }

        /**
         * 构造类型.
         */
        public JavaType constructParametricType(Class<?> rawType, JavaType... parameterTypes) {
            return mapper.getTypeFactory().constructParametricType(rawType, parameterTypes);
        }

        public ObjectMapper getMapper() {
            return mapper;
        }

        /**
         * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
         */
        public void update(String jsonString, Object object) {
            try {
                mapper.readerForUpdating(object).readValue(jsonString);
            } catch (IOException e) {
                logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
                throw new RuntimeException(e);
            }
        }

        /**
         * 輸出JSONP格式數據.
         */
        public String toJsonP(String functionName, Object object) {
            return toJson(new JSONPObject(functionName, object));
        }
    }
}