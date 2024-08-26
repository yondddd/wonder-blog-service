package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.web.blog.admin.vo.BlogVO;

import java.util.List;

/**
 * @author yond
 * @date 8/20/2024
 * @description
 */
public class BlogConverter {

    public static BlogVO do2vo(BlogDO from, String categoryName, List<TagDO> tags) {
        BlogVO to = new BlogVO();
        to.setId(from.getId());
        to.setCategoryId(from.getCategoryId());
        to.setCategoryName(categoryName);
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
        if (tags != null) {
            to.setTags(tags.stream().map(TagConvert::do2vo).toList());
        }
        return to;
    }
}
