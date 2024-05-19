package com.yond.env.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * NICUtil
 * @version 1.0
 * @created 2023/09/26 20:16
 **/
public class NICUtil {

    private final static String LOCALHOST = "localhost";

    private final static String LOCAL_IP = "127.0.0.1";

    private final static List<String> INNER_NET_IP_PREX_LIST = new ArrayList<>(3);

    private final static String INNER_NET_IP_REGEX = "^172.(1[6-9]]|2|3[0-1])";

    static {
        INNER_NET_IP_PREX_LIST.add("10.");
        INNER_NET_IP_PREX_LIST.add("172.20.");
        INNER_NET_IP_PREX_LIST.add("192.168.");
    }

    public static String getMac() {
        final NetworkInterface anInterface = getNetworkInterface(null);
        if (anInterface == null) {
            return null;
        }

        try {
            byte[] mac = anInterface.getHardwareAddress();
            StringBuilder builder = new StringBuilder(mac.length);
            for (int i = 0; i < mac.length; i++) {
                builder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }

            return builder.toString();
        } catch (Throwable ex) {
            return null;
        }
    }

    public static String getLocalIpV4() {
        return getLocalIpV4(null);
    }

    public static String getLocalIpV4(final String tryIp) {
        if (LOCAL_IP.equalsIgnoreCase(tryIp) || LOCALHOST.equalsIgnoreCase(tryIp)) {
            return tryIp;
        }

        Enumeration<NetworkInterface> networkInterface = getNetworkInterfaces();
        String ip = null;

        while (networkInterface.hasMoreElements()) {
            NetworkInterface ni = networkInterface.nextElement();
            if (!isUp(ni)) {
                continue;
            }

            final Enumeration<InetAddress> niInetAddresses = ni.getInetAddresses();
            while (niInetAddresses.hasMoreElements()) {
                InetAddress inetAddress = niInetAddresses.nextElement();
                if (inetAddress instanceof Inet6Address) {
                    continue; // ignore ipv6
                }

                final String thisIp = inetAddress.getHostAddress();
                final boolean isValidAddress = isReachable(inetAddress) && !inetAddress.isLoopbackAddress() && thisIp.indexOf(":") == -1 && (isIntranetIpv4(thisIp) || ip == null);
                if (isValidAddress) {
                    ip = thisIp;
                    if (ip.equals(tryIp)) {
                        return tryIp;
                    }
                }
            }
        }

        return ip;
    }

    public static NetworkInterface getNetworkInterface(final String tryIp) {
        Enumeration<NetworkInterface> networkInterface = getNetworkInterfaces();
        NetworkInterface anInterface = null;
        String ip = null;
        while (networkInterface.hasMoreElements()) {
            NetworkInterface ni = networkInterface.nextElement();
            if (!isUp(ni)) {
                continue;
            }

            final Enumeration<InetAddress> niInetAddresses = ni.getInetAddresses();
            while (niInetAddresses.hasMoreElements()) {
                InetAddress inetAddress = niInetAddresses.nextElement();
                if (inetAddress instanceof Inet6Address) {
                    continue; // ignore ipv6
                }

                final String thisIp = inetAddress.getHostAddress();
                final boolean isValidAddress = isReachable(inetAddress) && !inetAddress.isLoopbackAddress() && thisIp.indexOf(":") == -1 && !"127.0.0.1".equals(thisIp) && (isIntranetIpv4(thisIp) || ip == null);
                if (isValidAddress) {
                    anInterface = ni;
                    ip = thisIp;
                    if (ip.equals(tryIp)) {
                        return anInterface;
                    }
                }
            }
        }

        return anInterface;
    }

    public static void main(String[] args) {
        final String vale = getLocalIpV4();
        System.out.println(vale);
        String mac = getMac();
        System.out.println(mac);
    }

    private static boolean isReachable(InetAddress inetAddress) {
        try {
            return inetAddress.isReachable(1000);
        } catch (Throwable ex) {
            return false;
        }
    }

    public static final Enumeration<NetworkInterface> getNetworkInterfaces() {
        try {
            return NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new IllegalStateException(e);
        }
    }

    private static boolean isIntranetIpv4(final String ip) {
        for (final String netIp : INNER_NET_IP_PREX_LIST) {
            if (ip.startsWith(netIp)) {
                return true;
            }
        }

        return ip.matches(INNER_NET_IP_REGEX);
    }

    private static boolean isUp(NetworkInterface ni) {
        try {
            return ni.isUp();
        } catch (Throwable ex) {
            return true;
        }
    }
}