package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.BlogTagDO;
import com.yond.blog.entity.CategoryDO;
import com.yond.blog.web.blog.admin.vo.BlogDetailVO;
import com.yond.blog.web.blog.admin.vo.BlogListVO;

import java.util.List;
import java.util.Map;

/**
 * @author yond
 * @date 8/20/2024
 * @description
 */
public class BlogConverter {

    public static BlogListVO do2vo(BlogDO from, Map<Long, String> categoryMap) {
        BlogListVO to = new BlogListVO();
        to.setId(from.getId());
        to.setCategoryId(from.getCategoryId());
        to.setCategoryName(categoryMap.get(from.getCategoryId().longValue()));
        to.setUserId(from.getUserId());
        to.setTitle(from.getTitle());
        to.setFirstPicture(from.getFirstPicture());
        to.setContent(from.getContent());
        to.setDescription(from.getDescription());
        to.setPublished(from.getPublished());
        to.setRecommend(from.getRecommend());
        to.setAppreciation(from.getAppreciation());
        to.setCommentEnabled(from.getCommentEnabled());
        to.setTop(from.getTop());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setViews(from.getViews());
        to.setWords(from.getWords());
        to.setReadTime(from.getReadTime());
        to.setPassword(from.getPassword());
        return to;
    }

    public static BlogDetailVO do2detail(BlogDO from, CategoryDO category, List<BlogTagDO> blogTags) {
        BlogDetailVO to = new BlogDetailVO();
        to.setId(from.getId());
        to.setCategoryId(from.getCategoryId());
        to.setCategoryName(category.getName());
        to.setUserId(from.getUserId());
        to.setTitle(from.getTitle());
        to.setFirstPicture(from.getFirstPicture());
        to.setContent(from.getContent());
        to.setDescription(from.getDescription());
        to.setPublished(from.getPublished());
        to.setRecommend(from.getRecommend());
        to.setAppreciation(from.getAppreciation());
        to.setCommentEnabled(from.getCommentEnabled());
        to.setTop(from.getTop());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setViews(from.getViews());
        to.setWords(from.getWords());
        to.setReadTime(from.getReadTime());
        to.setPassword(from.getPassword());
        for (BlogTagDO blogTag : blogTags) {

        }
        to.setTagList();
        return to;

    }
}
