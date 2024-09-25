package com.yond.blog.web.view.convert;

import com.yond.blog.entity.BlogDO;
import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.blog.web.view.vo.BlogCategoryVO;
import com.yond.blog.web.view.vo.BlogDetailVO;
import com.yond.blog.web.view.vo.BlogTagVO;
import com.yond.blog.web.view.vo.BlogVO;
import com.yond.common.constant.BlogConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yond
 */
public class BlogViewConverter {
    
    
    public static List<BlogVO> do2vo(List<BlogDO> from,
                                     Map<Long, CategoryDO> categoryMap,
                                     Map<Long, List<TagDO>> blogTagMap) {
        List<BlogVO> blogVOlist = new ArrayList<>();
        for (BlogDO blogDO : from) {
            BlogVO to = new BlogVO();
            to.setId(blogDO.getId());
            to.setTitle(blogDO.getTitle());
            to.setCreateTime(blogDO.getCreateTime());
            to.setViews(blogDO.getViews());
            to.setWords(blogDO.getWords());
            to.setReadTime(blogDO.getReadTime());
            to.setTop(blogDO.getTop());
            boolean privacy = StringUtils.isNotBlank(blogDO.getPassword());
            if (privacy) {
                to.setDescription(BlogConstant.PRIVATE_BLOG_DESCRIPTION);
            } else {
                to.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogDO.getDescription()));
            }
            to.setPrivacy(privacy);
            CategoryDO category = categoryMap.get(to.getId());
            if (category != null) {
                BlogCategoryVO c = new BlogCategoryVO();
                c.setId(category.getId());
                c.setName(category.getName());
                to.setCategory(c);
            }
            List<TagDO> tags = blogTagMap.get(to.getId());
            if (CollectionUtils.isNotEmpty(tags)) {
                List<BlogTagVO> blogTag = new ArrayList<>();
                for (TagDO tag : tags) {
                    BlogTagVO blogTagVO = new BlogTagVO();
                    blogTagVO.setId(tag.getId());
                    blogTagVO.setName(tag.getName());
                    blogTagVO.setColor(tag.getColor());
                    blogTag.add(blogTagVO);
                }
                to.setTagList(blogTag);
            }
        }
        return blogVOlist;
    }
    
    
    public static BlogDetailVO do2detail(BlogDO from, CategoryDO category, List<TagDO> tags) {
        BlogDetailVO to = new BlogDetailVO();
        to.setId(from.getId());
        to.setTitle(from.getTitle());
        to.setContent(from.getContent());
        to.setAppreciation(from.getAppreciation());
        to.setCommentEnabled(from.getCommentEnabled());
        to.setTop(from.getTop());
        to.setCreateTime(from.getCreateTime());
        to.setUpdateTime(from.getUpdateTime());
        to.setViews(from.getViews());
        to.setWords(from.getWords());
        to.setReadTime(from.getReadTime());
        if (category != null) {
            BlogCategoryVO c = new BlogCategoryVO();
            c.setId(category.getId());
            c.setName(category.getName());
            to.setCategory(c);
        }
        if (CollectionUtils.isNotEmpty(tags)) {
            List<BlogTagVO> blogTag = new ArrayList<>();
            for (TagDO tag : tags) {
                BlogTagVO blogTagVO = new BlogTagVO();
                blogTagVO.setId(tag.getId());
                blogTagVO.setName(tag.getName());
                blogTagVO.setColor(tag.getColor());
                blogTag.add(blogTagVO);
            }
            to.setTags(blogTag);
        }
        return to;
    }
    
}
