package com.yond.blog.web.blog.admin.convert;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.web.blog.admin.vo.BlogListVO;

/**
 * @author yond
 * @date 8/20/2024
 * @description
 */
public class BlogConverter {

    public static BlogListVO convert(BlogDO from) {
        BlogListVO blogListVO = new BlogListVO();
        blogListVO.setId(from.getId());
        blogListVO.setCategoryId(from.getCategoryId());
        blogListVO.setUserId(from.getUserId());
        blogListVO.setTitle(from.getTitle());
        blogListVO.setFirstPicture(from.getFirstPicture());
        blogListVO.setContent(from.getContent());
        blogListVO.setDescription(from.getDescription());
        blogListVO.setPublished(from.getPublished());
        blogListVO.setRecommend(from.getRecommend());
        blogListVO.setAppreciation(from.getAppreciation());
        blogListVO.setCommentEnabled(from.getCommentEnabled());
        blogListVO.setTop(from.getTop());
        blogListVO.setCreateTime(from.getCreateTime());
        blogListVO.setUpdateTime(from.getUpdateTime());
        blogListVO.setViews(from.getViews());
        blogListVO.setWords(from.getWords());
        blogListVO.setReadTime(from.getReadTime());
        blogListVO.setPassword(from.getPassword());
        return blogListVO;
    }

}
