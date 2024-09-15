package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.web.blog.admin.req.BlogSaveReq;
import com.yond.blog.web.blog.admin.vo.BlogVO;
import com.yond.blog.web.blog.admin.vo.CategoryVO;

import java.util.List;
import java.util.Optional;

/**
 * @Author Yond
 * @date 8/20/2024
 * @description
 */
public class BlogConverter {

    public static BlogVO do2vo(BlogDO from, String categoryName, List<TagDO> tags) {
        BlogVO to = new BlogVO();
        to.setId(from.getId());
        CategoryVO categoryVO = CategoryVO.custom()
                .setId(from.getCategoryId().longValue())
                .setName(categoryName);
        to.setCategory(categoryVO);
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

    public static BlogDO save2do(BlogSaveReq from) {
        BlogDO to = BlogDO.custom()
                .setId(from.getId())
                .setTitle(from.getTitle())
                .setFirstPicture(from.getFirstPicture())
                .setContent(from.getContent())
                .setDescription(from.getDescription())
                .setPublished(from.getPublished())
                .setRecommend(from.getRecommend())
                .setAppreciation(from.getAppreciation())
                .setCommentEnabled(from.getCommentEnabled())
                .setTop(from.getTop())
                .setViews(from.getViews())
                .setWords(from.getWords())
                .setPassword(from.getPassword());
        Integer readTime = Optional.ofNullable(from.getReadTime())
                .filter(time -> time >= 0)
                .orElse((int) Math.round(from.getWords() / 200.0));
        to.setReadTime(readTime);
        return to;
    }

}
