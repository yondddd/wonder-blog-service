package com.wish.blog.web.manager;

import com.wish.blog.entity.SiteConfigDO;
import com.wish.blog.service.SiteConfigService;
import com.wish.blog.util.agent.UserAgentUtils;
import com.wish.common.constant.SiteConfigConstant;
import com.wish.common.utils.env.env.EnvConstant;
import com.wish.common.utils.env.env.Environment;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 初始化
 * @Author: Yond
 */
@Component
@DependsOn("siteConfigServiceImpl")
public class InitManager implements InitializingBean {
    
    @Resource
    private SiteConfigService siteConfigService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        // 环境配置
        Thread.startVirtualThread(this::configInit);
        Thread.startVirtualThread(this::userAgentInit);
    }
    
    private void configInit() {
        List<SiteConfigDO> list = siteConfigService.listAll();
        for (SiteConfigDO setting : list) {
            switch (setting.getKey()) {
                case SiteConfigConstant.KEY_TENCENT_MAP_IP ->
                        Environment.setProperty(EnvConstant.TENCENT_IP_KEY, setting.getValue());
                case SiteConfigConstant.KEY_USER_PASSWORD_SECRET ->
                        Environment.setProperty(EnvConstant.USER_PASSWORD_SECRET_KEY, setting.getValue());
                default -> {
                }
            }
        }
    }
    
    private void userAgentInit() {
        UserAgentUtils.init();
    }
    
}
