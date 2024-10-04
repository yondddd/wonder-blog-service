package com.wish.blog.web.view.req;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yond
 * @date 10/1/2024
 * @description
 */
public class BlogSearchReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -3344879255893697380L;

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public BlogSearchReq setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    @Override
    public String toString() {
        return "BlogSearchReq{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
