package com.wonder.blog.service;

import com.wonder.blog.entity.BookshelfDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * @author yond
 * @date 11/16/2024
 * @description bookshelf
 */
public interface BookshelfService {

    List<String> allAuthor();

    Pair<Integer, List<BookshelfDO>> page(Integer pageNo, Integer pageSize, String authorName, String bookName);

    void insertSelective(BookshelfDO bookshelfDO);

    void updateSelective(BookshelfDO bookshelfDO);

    void deleteById(Long id);

    BookshelfDO getById(Integer id);
}
