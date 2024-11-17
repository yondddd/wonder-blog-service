package com.wonder.blog.mapper;
import java.util.List;

import com.wonder.blog.entity.BookshelfDO;

public interface BookshelfMapper {

    int deleteByPrimaryKey(Long id);

    int insert(BookshelfDO record);

    int insertSelective(BookshelfDO record);

    BookshelfDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BookshelfDO record);

    int updateByPrimaryKey(BookshelfDO record);

    List<BookshelfDO> listAll();
}
