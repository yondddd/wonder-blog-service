package com.wish.blog.cache.local;

import com.github.benmanes.caffeine.cache.Cache;
import com.wish.blog.entity.BlogDO;
import com.wish.blog.web.view.vo.ArchiveVO;

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
