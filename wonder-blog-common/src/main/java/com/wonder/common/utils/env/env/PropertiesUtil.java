package com.wonder.common.utils.env.env;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class PropertiesUtil {

    private static final String SCHEMA_CLASSPATH = "classpath:";

    private static final String SCHEMA_FILE = "file:";

    public static Properties load(String resource) throws IOException {
        if (resource == null) {
            throw new NullPointerException("resource is null");
        }
        if (resource.startsWith(SCHEMA_CLASSPATH)) {
            return loadFromClassPath(resource.substring(SCHEMA_CLASSPATH.length()));
        } else if (resource.startsWith(SCHEMA_FILE)) {
            return loadFromFileSystem(resource.substring(SCHEMA_FILE.length()));
        } else {
            Properties props = loadFromClassPath(resource);
            if (props == null) {
                props = loadFromFileSystem(resource);
            }
            return props;
        }
    }

    public static Properties loadFromFileSystem(String file) throws IOException {
        File f = new File(file);
        if (!f.exists()) {
            return null;
        }
        URL url = f.toURI().toURL();
        return load(url);
    }

    public static Properties loadFromClassPath(String file) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(file);
        if (url == null) {
            return null;
        }
        return load(url);
    }

    public static Properties load(URL url) throws IOException {
        InputStream in = null;
        try {
            in = url.openStream();
            Properties props = new Properties();
            props.load(in);
            return props;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
