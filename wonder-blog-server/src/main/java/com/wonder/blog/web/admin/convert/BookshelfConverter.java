package com.wonder.blog.web.admin.convert;

import com.wonder.blog.entity.BookshelfDO;
import com.wonder.blog.web.admin.vo.BookshelfVO;

/**
 * @author yond
 * @date 11/16/2024
 * @description
 */
public class BookshelfConverter {

    public static BookshelfVO do2vo(BookshelfDO from) {
        BookshelfVO bookshelfVO = new BookshelfVO();
        bookshelfVO.setId(from.getId());
        bookshelfVO.setBookName(from.getBookName());
        bookshelfVO.setBookCover(from.getBookCover());
        bookshelfVO.setAuthorName(from.getAuthorName());
        bookshelfVO.setUrl(from.getUrl());
        bookshelfVO.setAuthorInfo(from.getAuthorInfo());
        bookshelfVO.setBookInfo(from.getBookInfo());
        return bookshelfVO;
    }

    public static BookshelfDO vo2do(BookshelfVO from) {
        BookshelfDO to = new BookshelfDO();
        to.setId(from.getId());
        to.setBookName(from.getBookName());
        to.setBookCover(from.getBookCover());
        to.setAuthorName(from.getAuthorName());
        to.setUrl(from.getUrl());
        to.setAuthorInfo(from.getAuthorInfo());
        to.setBookInfo(from.getBookInfo());
        return to;
    }

}
