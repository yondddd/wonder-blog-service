package com.yond.blog.util;

import com.yond.blog.web.blog.view.dto.UserAgentDTO;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

/**
 * @Description: UserAgent解析工具类
 * @Author: Naccl
 * @Date: 2020-11-30
 */
public class UserAgentUtils {
    
    private static UserAgentAnalyzer userAgentAnalyzer;
    
    /**
     * 从User-Agent解析客户端操作系统和浏览器版本
     */
    public static UserAgentDTO parseOsAndBrowser(String userAgent) {
        init();
        UserAgent agent = userAgentAnalyzer.parse(userAgent);
        String os = agent.getValue(UserAgent.OPERATING_SYSTEM_NAME_VERSION_MAJOR);
        String browser = agent.getValue(UserAgent.AGENT_NAME_VERSION);
        return new UserAgentDTO(os, browser);
    }

    public static synchronized void init() {
        if (userAgentAnalyzer != null) {
            return;
        }
        userAgentAnalyzer = UserAgentAnalyzer
                .newBuilder()
                .useJava8CompatibleCaching()
                .withCache(10000)
                .hideMatcherLoadStats()
                .withField(UserAgent.OPERATING_SYSTEM_NAME_VERSION_MAJOR)
                .withField(UserAgent.AGENT_NAME_VERSION)
                .build();
    }
    
}
