package com.yond.blog.util.ip;

import com.yond.blog.cache.local.IpCache;
import com.yond.common.utils.env.env.EnvConstant;
import com.yond.common.utils.env.env.Environment;
import com.yond.common.utils.json.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: ip地理位置工具类
 */
public class IpGeographicUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(IpGeographicUtil.class);
    
    private static final int MAX_REQUESTS_PER_MINUTE = 44;
    private static final long MINUTE_IN_MILLIS = 60_000;
    
    private static final AtomicInteger requestCount = new AtomicInteger(0);
    private static final AtomicLong startTime = new AtomicLong(System.currentTimeMillis());
    
    private static final String IP_API_URL = "http://ip-api.com/json/%s?fields=66846719&lang=zh-CN";
    private static final String TENCENT_URL = "https://apis.map.qq.com/ws/location/v1/ip?ip=%s&key=%s";
    
    private static final CloseableHttpClient httpClient = createHttpClient();
    private static final RequestConfig requestConfig = createRequestConfig();
    
    public static String getAddressByIp(String ip) {
        if (ip == null) {
            return "Invalid IP";
        }
        if (isLanIp(ip)) {
            return "局域网ip";
        }
        
        String cachedAddress = IpCache.get(ip);
        if (cachedAddress != null) {
            return cachedAddress;
        }
        
        String address = getTencentAddress(ip);
        if (address != null) {
            return address;
        }
        
        if (!allowRequest()) {
            return "ip地址获取频率过高";
        }
        
        return getIpApiAddress(ip);
    }
    
    private static String getTencentAddress(String ip) {
        String tencentKey = Environment.getValue(EnvConstant.TENCENT_IP_KEY, Function.identity());
        if (StringUtils.isBlank(tencentKey)) {
            return null;
        }
        String json = doGet(String.format(TENCENT_URL, ip, tencentKey));
        if (StringUtils.isBlank(json)) {
            return null;
        }
        TencentResponse response = JsonUtils.fromJson(json, TencentResponse.class);
        if (response != null && response.getStatus() == 0) {
            TencentResponse.Result.AdInfo adInfo = response.getResult().getAd_info();
            if ("中国".equals(adInfo.getNation())) {
                String address = formatAddress(adInfo);
                IpCache.set(ip, address);
                return address;
            }
        }
        return null;
    }
    
    private static String getIpApiAddress(String ip) {
        String json = doGet(String.format(IP_API_URL, ip));
        if (json == null) {
            return "ip地址获取失败";
        }
        
        IpDTO ipDTO = JsonUtils.fromJson(json, IpDTO.class);
        String address = formatAddress(ipDTO);
        IpCache.set(ip, address);
        return address;
    }
    
    private static String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        long start = System.currentTimeMillis();
        
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            logger.error("<|>IpGeographicUtil_getByLimit<|>ip:{}<|>duration:{}",
                    url, System.currentTimeMillis() - start, e);
            return null;
        }
    }
    
    private static boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long lastStartTime = startTime.get();
        if (currentTime - lastStartTime >= MINUTE_IN_MILLIS) {
            requestCount.set(0);
            startTime.set(currentTime);
        }
        return requestCount.incrementAndGet() <= MAX_REQUESTS_PER_MINUTE;
    }
    
    private static String formatAddress(IpDTO ipDTO) {
        return joinNonBlank(ipDTO.getContinent(), ipDTO.getCountry(),
                ipDTO.getRegionName(), ipDTO.getCity());
    }
    
    private static String formatAddress(TencentResponse.Result.AdInfo adInfo) {
        return joinNonBlank(adInfo.getNation(), adInfo.getProvince(),
                adInfo.getCity(), adInfo.getDistrict());
    }
    
    private static String joinNonBlank(String... parts) {
        return Arrays.stream(parts)
                .filter(x -> StringUtils.isNotBlank(x) && !"null".equals(x))
                .collect(Collectors.joining("|"));
    }
    
    public static boolean isLanIp(String ip) {
        return ip != null && (ip.startsWith("10.") || ip.startsWith("172.") || ip.startsWith("192.")
                || "0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip));
    }
    
    private static CloseableHttpClient createHttpClient() {
        return HttpClientBuilder.create()
                .setRetryHandler((exception, executionCount, context) ->
                        executionCount < 3 && exception instanceof java.net.SocketTimeoutException)
                .build();
    }
    
    private static RequestConfig createRequestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000)
                .build();
    }
    
}
