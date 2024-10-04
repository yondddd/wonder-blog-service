package com.wish.blog.mapper;

import com.wish.blog.entity.BlogTagDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogTagMapper {
    
    List<BlogTagDO> listAll();

    int insertSelective(BlogTagDO record);
    
    int deleteByIds(@Param("ids") List<Long> ids);

}
