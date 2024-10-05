package com.wonder.blog.web.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 随机博客
 * @Author: Yond
 */
public class RandomBlogVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -9109899710786987182L;

    private Long id;
    private String title;//文章标题
    private String firstPicture;//文章首图，用于随机文章展示
    private Date createTime;//创建时间
    private Boolean privacy;//是否私密文章

    public RandomBlogVO() {
    }

    public Long getId() {
        return id;
    }

    public RandomBlogVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RandomBlogVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public RandomBlogVO setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RandomBlogVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public RandomBlogVO setPrivacy(Boolean privacy) {
        this.privacy = privacy;
        return this;
    }

    @Override
    public String toString() {
        return "RandomBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", createTime=" + createTime +
                ", privacy=" + privacy +
                '}';
    }
}
