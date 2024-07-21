package com.yond.blog.util.ip;

import com.yond.blog.cache.local.IpCache;
import com.yond.common.utils.json.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Description: ip地理位置工具类
 * @Date: 2023/5/24
 */
public class IpGeographicUtil {


    private static final Logger logger = LoggerFactory.getLogger(IpGeographicUtil.class);

    /**
     * 一分钟最多请求45次
     */
    private static final int MAX_REQUESTS_PER_MINUTE = 44;

    private static final long MINUTE_IN_MILLIS = 60 * 1000;

    private static final AtomicInteger REQUEST_COUNT = new AtomicInteger(0);

    private static volatile long startTime = System.currentTimeMillis();

    private final static String DOMAIN = "http://ip-api.com/json/";

    /**
     * fields=status,message,country,city,lat,lon,query,continent,region
     */
    private final static String QUERY = "?fields=66846719&lang=zh-CN";

    private static final CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setRetryHandler((exception, executionCount, context) -> {
                if (executionCount >= 3) {
                    // 最多重试3次
                    return false;
                }
                // 超时异常，进行重试
                return exception instanceof java.net.SocketTimeoutException;
            })
            .build();

    private static final RequestConfig requestConfig = RequestConfig.custom()
            // 默认连接超时
            .setConnectionRequestTimeout(1000)
            // 请求超时
            .setSocketTimeout(5000)
            .build();

    public static String getAddressByIp(String ip) {
        String exist = IpCache.get(ip);
        if (exist != null) {
            return exist;
        }
        if (isLanIp(ip)) {
            return "局域网ip";
        }
        if (!allowRequest()) {
            return "ip地址获取频率过高";
        }
        String json = doGet(ip);
        if (json == null) {
            return "ip地址获取失败";
        }
        IpDTO ipDTO = JsonUtils.fromJson(json, IpDTO.class);
        List<String> list = List.of(ipDTO.getContinent(), ipDTO.getCountry(), ipDTO.getRegionName(), ipDTO.getCity());
        String address = list.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining("|"));
        IpCache.set(ip, address);
        return address;
    }

    private static String doGet(String ip) {
        String url = buildUrl(ip);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        long duration = 0;
        long startTime = System.currentTimeMillis();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            duration = System.currentTimeMillis() - startTime;
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            logger.error("<|>IpGeographicUtil_getByLimit<|>ip:{}<|>duration:{}", ip, duration, e);
        }
        return null;
    }


    private static boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        // 如果超过了1分钟，则重置请求计数器和起始时间
        if (elapsedTime >= MINUTE_IN_MILLIS) {
            REQUEST_COUNT.set(0);
            startTime = currentTime;
        }
        return REQUEST_COUNT.incrementAndGet() <= MAX_REQUESTS_PER_MINUTE;
    }

    /**
     * @param address ip地址
     * @return http://ip-api.com/json/{address}?fields=57553
     */
    private static String buildUrl(String address) {
        return DOMAIN + address + QUERY;
    }


    /**
     * 是否局域网ip
     */
    public static boolean isLanIp(String ip) {
        if (ip == null) {
            return false;
        }
        return ip.startsWith("10.") || ip.startsWith("172.") || ip.startsWith("192.") || ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1");
    }
}
