package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.yond.blog.web.blog.view.vo.FriendInfo;

/**
 * @Description:
 * @Author: Yond
 * @Date: 2024-09-02
 */
@Model("")
public class CommentCache {


    private final static Cache<String, FriendInfo> cache = LocalCache.buildCache(1);

}
