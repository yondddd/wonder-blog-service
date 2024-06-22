package com.yond.cache;

import com.yond.cache.constant.RedisKeyConstant;
import com.yond.model.vo.BlogInfo;
import com.yond.model.vo.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author yond
 * @date 6/21/2024
 * @description blog info cache
 */
@Component
public class BlogInfoCache {

    @Resource
    public RedisCache<PageResult<BlogInfo>> redisCache;


    public void delete() {
        redisCache.delete(RedisKeyConstant.HOME_BLOG_INFO_LIST);
    }

}
