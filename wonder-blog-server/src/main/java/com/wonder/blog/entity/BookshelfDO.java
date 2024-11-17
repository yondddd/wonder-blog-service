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
     * 存储类型
     */
    private String storageType;

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
     * 存储类型
     */
    public String getStorageType() {
        return storageType;
    }

    /**
     * 存储类型
     */
    public void setStorageType(String storageType) {
        this.storageType = storageType;
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
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BookshelfDO other = (BookshelfDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookName() == null ? other.getBookName() == null : this.getBookName().equals(other.getBookName()))
            && (this.getBookCover() == null ? other.getBookCover() == null : this.getBookCover().equals(other.getBookCover()))
            && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
            && (this.getStorageType() == null ? other.getStorageType() == null : this.getStorageType().equals(other.getStorageType()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getAuthorInfo() == null ? other.getAuthorInfo() == null : this.getAuthorInfo().equals(other.getAuthorInfo()))
            && (this.getBookInfo() == null ? other.getBookInfo() == null : this.getBookInfo().equals(other.getBookInfo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookName() == null) ? 0 : getBookName().hashCode());
        result = prime * result + ((getBookCover() == null) ? 0 : getBookCover().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getStorageType() == null) ? 0 : getStorageType().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getAuthorInfo() == null) ? 0 : getAuthorInfo().hashCode());
        result = prime * result + ((getBookInfo() == null) ? 0 : getBookInfo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bookName=").append(bookName);
        sb.append(", bookCover=").append(bookCover);
        sb.append(", authorName=").append(authorName);
        sb.append(", storageType=").append(storageType);
        sb.append(", url=").append(url);
        sb.append(", authorInfo=").append(authorInfo);
        sb.append(", bookInfo=").append(bookInfo);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}