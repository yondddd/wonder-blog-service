package com.wonder.blog.service.impl;

import com.wonder.blog.cache.local.BookCache;
import com.wonder.blog.entity.BookshelfDO;
import com.wonder.blog.entity.CategoryDO;
import com.wonder.blog.mapper.BookshelfMapper;
import com.wonder.blog.service.BookshelfService;
import com.wonder.common.enums.EnableStatusEnum;
import com.wonder.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yond
 * @date 11/16/2024
 * @description bookshelf
 */
@Service
public class BookshelfServiceImpl implements BookshelfService {

    @Resource
    private BookshelfMapper bookshelfMapper;

    @Override
    public List<String> allAuthor() {
        return this.listAll().stream()
                .filter(x -> EnableStatusEnum.ENABLE.getVal().equals(x.getStatus()))
                .map(BookshelfDO::getAuthorName).distinct().sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Pair<Integer, List<BookshelfDO>> page(Integer pageNo, Integer pageSize, String authorName, String bookName) {
        List<BookshelfDO> all = this.listAll().stream()
                .filter(x -> EnableStatusEnum.ENABLE.getVal().equals(x.getStatus()))
                .filter(x -> StringUtils.isBlank(authorName) || x.getAuthorName().contains(authorName))
                .filter(x -> StringUtils.isBlank(bookName) || x.getBookName().contains(bookName))
                .collect(Collectors.toList());
        return Pair.of(all.size(), PageUtil.pageList(all, pageNo, pageSize));
    }

    @Override
    public void insertSelective(BookshelfDO bookshelfDO) {
        bookshelfMapper.insertSelective(bookshelfDO);
        BookCache.del();
    }

    @Override
    public void updateSelective(BookshelfDO bookshelfDO) {
        bookshelfMapper.updateByPrimaryKeySelective(bookshelfDO);
        BookCache.del();
    }

    @Override
    public void deleteById(Long id) {
        BookshelfDO del = new BookshelfDO();
        del.setId(id.intValue());
        del.setStatus(EnableStatusEnum.DELETE.getVal());
        this.updateSelective(del);
    }

    @Override
    public BookshelfDO getById(Integer id) {
        return this.listAll().stream().filter(x -> id.equals(x.getId())).findFirst().orElse(null);
    }

    private List<BookshelfDO> listAll() {
        List<BookshelfDO> cache = BookCache.get();
        if (cache == null) {
            cache = bookshelfMapper.listAll();
            BookCache.set(cache);
        }
        return cache;
    }

}
