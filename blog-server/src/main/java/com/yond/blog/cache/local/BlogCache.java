package com.yond.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.view.vo.ArchiveVO;
import com.yond.blog.web.blog.view.vo.BlogInfo;
import com.yond.blog.web.blog.view.vo.PageResult;

import java.util.List;

/**
 * @Author Yond
 */
public class BlogCache {
    
    // 文章归档缓存
    
    private final static String BLOG_GROUP_KEY = "blogArchive";
    
    private final static Cache<String, ArchiveVO> cache = LocalCache.buildCache(1);
    
    public static ArchiveVO getBlogArchive() {
        return cache.getIfPresent(BLOG_GROUP_KEY);
    }
    
    public static void setBlogArchive(ArchiveVO blogArchive) {
        cache.put(BLOG_GROUP_KEY, blogArchive);
    }
    
    public static void delBlogGroup() {
        cache.invalidate(BLOG_GROUP_KEY);
    }
    
    // 按页缓存首页文章
    
    private final static String BLOG_INFO_KEY = "blogInfo";
    
    private final static Cache<String, PageResult<BlogInfo>> infoCache = LocalCache.buildCache(1000);
    
    
    public static void delInfo() {
        infoCache.invalidateAll();
    }
    
    public static PageResult<BlogInfo> getInfoByPage(int page) {
        return infoCache.getIfPresent(buildInfoKey(page));
    }
    
    public static void setInfoByPage(int page, PageResult<BlogInfo> data) {
        infoCache.put(buildInfoKey(page), data);
    }
    
    public static String buildInfoKey(int page) {
        return BLOG_INFO_KEY + page;
    }
    
    // 全部文章缓存
    
    private final static String BLOG_ALL_KEY = "ALL_ENABLE";
    
    private final static Cache<String, List<BlogDO>> allEnableCache = LocalCache.buildCache(1);
    
    public static List<BlogDO> listEnable() {
        return allEnableCache.getIfPresent(BLOG_ALL_KEY);
    }
    
    public static void setAllEnable(List<BlogDO> blogs) {
        allEnableCache.put(BLOG_ALL_KEY, blogs);
    }
    
    public static void delAllBlogs() {
        allEnableCache.invalidateAll();
    }
    
}
