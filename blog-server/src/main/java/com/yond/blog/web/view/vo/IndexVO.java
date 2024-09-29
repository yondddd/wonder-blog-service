package com.yond.blog.web.view.vo;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yond
 */
public class IndexVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8728104087748512189L;

    private Map<String, String> config;
    private List<NewBlogVO> newBlogVOList;
    private List<CategoryDO> categoryList;
    private List<TagDO> tagList;
    private List<RandomBlogVO> randomBlogVOList;

    public Map<String, String> getConfig() {
        return config;
    }

    public IndexVO setConfig(Map<String, String> config) {
        this.config = config;
        return this;
    }

    public List<NewBlogVO> getNewBlogList() {
        return newBlogVOList;
    }

    public IndexVO setNewBlogList(List<NewBlogVO> newBlogVOList) {
        this.newBlogVOList = newBlogVOList;
        return this;
    }

    public List<CategoryDO> getCategoryList() {
        return categoryList;
    }

    public IndexVO setCategoryList(List<CategoryDO> categoryList) {
        this.categoryList = categoryList;
        return this;
    }

    public List<TagDO> getTagList() {
        return tagList;
    }

    public IndexVO setTagList(List<TagDO> tagList) {
        this.tagList = tagList;
        return this;
    }

    public List<RandomBlogVO> getRandomBlogList() {
        return randomBlogVOList;
    }

    public IndexVO setRandomBlogList(List<RandomBlogVO> randomBlogVOList) {
        this.randomBlogVOList = randomBlogVOList;
        return this;
    }

    @Override
    public String toString() {
        return "IndexVO{" +
                "newBlogList=" + newBlogVOList +
                ", categoryList=" + categoryList +
                ", tagList=" + tagList +
                ", randomBlogList=" + randomBlogVOList +
                '}';
    }
}
