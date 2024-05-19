package com.yond.env.util;

import org.apache.commons.lang3.StringUtils;

/**
 * net 相关工具类
 * @version 1.0
 * @created 2022/08/23 20:16
 **/
public class NetUtil {

    private final static String LOCALHOST = "localhost";

    private final static String LOCAL_IP = "127.0.0.1";

    public static final String localIp = NICUtil.getLocalIpV4(null);

    public static final String localIpV4 = NICUtil.getLocalIpV4(null);

    public static String getIpV4() {
        return NICUtil.getLocalIpV4(null);
    }

    public static boolean isLocalIp(final String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }

        if (LOCALHOST.equalsIgnoreCase(ip) || LOCAL_IP.equalsIgnoreCase(ip)) {
            return true;
        }

        return ip.equalsIgnoreCase(NICUtil.getLocalIpV4(null));
    }
}