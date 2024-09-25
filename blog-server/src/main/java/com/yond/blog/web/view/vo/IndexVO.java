package com.yond.blog.web.view.vo;

import com.yond.blog.entity.CategoryDO;
import com.yond.blog.entity.TagDO;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: Yond
 */
public class IndexVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 8728104087748512189L;
    
    private List<NewBlog> newBlogList;
    
    private List<CategoryDO> categoryList;
    private List<TagDO> tagList;
    private List<RandomBlog> randomBlogList;
    
    public List<NewBlog> getNewBlogList() {
        return newBlogList;
    }
    
    public IndexVO setNewBlogList(List<NewBlog> newBlogList) {
        this.newBlogList = newBlogList;
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
    
    public List<RandomBlog> getRandomBlogList() {
        return randomBlogList;
    }
    
    public IndexVO setRandomBlogList(List<RandomBlog> randomBlogList) {
        this.randomBlogList = randomBlogList;
        return this;
    }
    
    @Override
    public String toString() {
        return "IndexVO{" +
                "newBlogList=" + newBlogList +
                ", categoryList=" + categoryList +
                ", tagList=" + tagList +
                ", randomBlogList=" + randomBlogList +
                '}';
    }
}
