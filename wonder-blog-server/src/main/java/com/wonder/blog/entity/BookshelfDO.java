package com.wonder.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 书架
 * @TableName bookshelf
 */
public class BookshelfDO implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍封面
     */
    private String bookCover;

    /**
     * 作者姓名
     */
    private String authorName;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 作者信息
     */
    private String authorInfo;

    /**
     * 书籍信息
     */
    private String bookInfo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 书籍名称
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 书籍名称
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 书籍封面
     */
    public String getBookCover() {
        return bookCover;
    }

    /**
     * 书籍封面
     */
    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    /**
     * 作者姓名
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 作者姓名
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 文件地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 文件地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 作者信息
     */
    public String getAuthorInfo() {
        return authorInfo;
    }

    /**
     * 作者信息
     */
    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    /**
     * 书籍信息
     */
    public String getBookInfo() {
        return bookInfo;
    }

    /**
     * 书籍信息
     */
    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    /**
     * 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BookshelfDO{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookCover='" + bookCover + '\'' +
                ", authorName='" + authorName + '\'' +
                ", url='" + url + '\'' +
                ", authorInfo='" + authorInfo + '\'' +
                ", bookInfo='" + bookInfo + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}