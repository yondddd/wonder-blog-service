package com.yond.blog.web.blog.view.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 随机博客
 * @Author: Naccl
 * @Date: 2020-08-17
 */
public class RandomBlog implements Serializable {
    @Serial
    private static final long serialVersionUID = -9109899710786987182L;
    private Long id;
    private String title;//文章标题
    private String firstPicture;//文章首图，用于随机文章展示
    private Date createTime;//创建时间
    private String password;//文章密码
    private Boolean privacy;//是否私密文章

    public RandomBlog() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getFirstPicture() {
        return this.firstPicture;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getPrivacy() {
        return this.privacy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String toString() {
        return "RandomBlog(id=" + this.getId() + ", title=" + this.getTitle() + ", firstPicture=" + this.getFirstPicture() + ", createTime=" + this.getCreateTime() + ", password=" + this.getPassword() + ", privacy=" + this.getPrivacy() + ")";
    }
}
