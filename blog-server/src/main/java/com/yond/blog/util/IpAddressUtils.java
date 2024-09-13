package com.yond.blog.util;

import com.yond.blog.util.ip.IpGeographicUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressUtils {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(IpAddressUtils.class);

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("getIpAddress exception:", e);
                }
                ip = inet.getHostAddress();
            }
        }
        return org.apache.commons.lang3.StringUtils.substringBefore(ip, ",");
    }


    public static String getCityInfo(String ip) {
        try {
            return IpGeographicUtil.getAddressByIp(ip);
        } catch (Exception e) {
            log.error("getCityInfo exception:", e);
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        String cityInfo = IpAddressUtils.getCityInfo("14.215.177.39");
        System.out.println(cityInfo);
    }

}