package com.wonder.blog.web.admin.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 11/16/2024
 * @description
 */
public class BookshelfVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5619083920873132218L;

    private Integer id;
    private String bookName;
    private String bookCover;
    private String authorName;
    private String storageType;
    private String url;
    private String authorInfo;
    private String bookInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    @Override
    public String toString() {
        return "BookshelfVO{" +
                "id=" + id +
                ", bookName=" + bookName +
                ", bookCover=" + bookCover +
                ", authorName=" + authorName +
                ", storageType='" + storageType + '\'' +
                ", url='" + url + '\'' +
                ", authorInfo='" + authorInfo + '\'' +
                ", bookInfo='" + bookInfo + '\'' +
                '}';
    }
}
