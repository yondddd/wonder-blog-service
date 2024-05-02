package com.yond.utils.json.json.manager;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.reflect.TypeToken;
import com.yond.utils.json.util.JsonUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * JsonManager
 **/
public final class JsonTypeConvertManager {

    private static final TypeFactory FACTORY = JsonUtils.getDefaultMapper().getMapper().getTypeFactory();

    private static final Type NULL = new Type() {
        @Override
        public String getTypeName() {
            return null;
        }
    };

    private static final Map<Field, Type> cache = new HashMap<>();

    public static Type getFieldGenericType(Field field) {
        Type javaType = cache.get(field);
        if (null != javaType) {
            return javaType == NULL ? null : javaType;
        }

        javaType = getFieldGenericType0(field);
        return javaType;
    }

    private synchronized static Type getFieldGenericType0(Field field) {
        Type javaType = cache.get(field);
        if (null != javaType) {
            return javaType == NULL ? null : javaType;
        }

        Optional<JavaType> jackson = getFieldGenericTypeByJackson(field);
        if (null != jackson) {
            if (jackson.isPresent()) {
                cache.put(field, jackson.get());
            } else {
                cache.put(field, NULL);
            }

            return jackson.isPresent() ? jackson.get() : null;
        }

        Optional<Type> gson = getFieldGenericTypeByGson(field);
        if (null == gson) {
            return null;
        }

        if (gson.isPresent()) {
            cache.put(field, gson.get());
        } else {
            cache.put(field, NULL);
        }

        return gson.isPresent() ? gson.get() : null;
    }

    private static Optional<Type> getFieldGenericTypeByGson(Field field) {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return Optional.empty();
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        if (null == typeArguments || typeArguments.length == 0) {
            return Optional.empty();
        }

        Type typeToken = null;
        try {
            typeToken = TypeToken.getParameterized(field.getType(), typeArguments).getType();
        } catch (Throwable ex) {
            //
        }

        return typeToken == null ? null : Optional.of(typeToken);
    }

    private synchronized static Optional<JavaType> getFieldGenericTypeByJackson(Field field) {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return Optional.empty();
        }

        final ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        if (ArrayUtils.isEmpty(typeArguments)) {
            return Optional.empty();
        }

        JavaType[] javaTypes = new JavaType[typeArguments.length];
        for (int j = 0, len = typeArguments.length; j < len; j++) {
            try {
                javaTypes[j] = getJavaType(typeArguments[j]);
            } catch (Throwable ex) {
                javaTypes = null;
                break;
            }
        }

        if (null != javaTypes) {
            return Optional.of(FACTORY.constructParametricType(field.getType(), javaTypes));
        }

        return null;
    }

    private static JavaType getJavaType(final Type type) {
        JavaType javaType = null;
        if (type instanceof Class<?>) {
            if (((Class<?>) type).isArray()) {
                javaType = FACTORY.constructArrayType(((Class<?>) type).getComponentType());
            } else {
                javaType = FACTORY.constructType(type);
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            JavaType[] types = new JavaType[typeArguments.length];
            for (int i = 0; i < types.length; i++) {
                types[i] = getJavaType(typeArguments[i]);
            }

            javaType = FACTORY.constructParametricType((Class<?>) parameterizedType.getRawType(), types);
        }

        return javaType;
    }
}