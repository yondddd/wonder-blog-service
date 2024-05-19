package com.yond.extension;


import com.yond.extension.ordered.OrderedManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ExtensionLoader
 *
 * @version 1.0
 * @created 2018/10/19 17:18
 **/
public class ExtensionLoader {

    private static final Map<Class<?>, Object> extensionMap = new ConcurrentHashMap<Class<?>, Object>();

    private static final Map<Class<?>, List<?>> extensionListMap = new ConcurrentHashMap<Class<?>, List<?>>();

    private ExtensionLoader() {
    }

    public static <T> T getExtension(Class<T> clazz) {
        T extension = (T) extensionMap.get(clazz);
        if (extension == null) {
            extension = newExtension(clazz);
            if (extension != null) {
                extensionMap.put(clazz, extension);
            }
        }
        return extension;
    }

    public static <T> T getExtensionOrDefault(Class<T> clazz, T defaultValue) {
        T extension = (T) extensionMap.get(clazz);
        if (extension == null) {
            extension = newExtension(clazz);
            if (extension != null) {
                extensionMap.put(clazz, extension);
            }
        }

        return extension == null ? defaultValue : extension;
    }

    public static <T> List<T> getExtensionList(Class<T> clazz) {
        List<T> extensions = (List<T>) extensionListMap.get(clazz);
        if (extensions == null) {
            extensions = newExtensionList(clazz);
            if (!extensions.isEmpty()) {
                extensionListMap.put(clazz, extensions);
            }
        }
        return extensions;
    }

    public static <T> List<T> getOrderedExtensionList(Class<T> clazz) {
        List<T> extensions = (List<T>) extensionListMap.get(clazz);
        if (extensions == null) {
            extensions = newOrderedExtensionList(clazz);
            if (!extensions.isEmpty()) {
                extensionListMap.put(clazz, extensions);
            }
        }

        return extensions;
    }

    public static <T> T newExtension(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        Iterator<T> iterator = serviceLoader.iterator();
        if (null == iterator || !iterator.hasNext()) {
            return null;
        }

        return iterator.next();
    }

    public static <T> List<T> newExtensionList(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        List<T> extensions = new ArrayList<>();
        for (T service : serviceLoader) {
            extensions.add(service);
        }

        return extensions;
    }

    private static <T> List<T> newOrderedExtensionList(Class<T> clazz) {
        ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz);
        List<T> extensions = new ArrayList<>();
        for (T service : serviceLoader) {
            extensions.add(service);
        }

        if (!extensions.isEmpty()) {
            extensions.sort(OrderedManager.COMPARATOR);
        }

        return extensions;
    }
}
