package com.wonder.blog.web.admin.req;

import com.wonder.common.req.PageReq;

import java.io.Serial;

/**
 * @author yond
 * @date 11/16/2024
 * @description book page
 */
public class BookshelfPageReq extends PageReq {

    @Serial
    private static final long serialVersionUID = -8070339217206825654L;

    private String authorName;
    private String bookName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "BookshelfPageReq{" +
                "authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

}
