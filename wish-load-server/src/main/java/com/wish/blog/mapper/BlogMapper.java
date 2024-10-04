package com.wish.blog.mapper;

import com.wish.blog.entity.BlogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    List<BlogDO> listByStatus(@Param("status") Integer status);
    
    int insertSelective(BlogDO blogDO);

    int updateSelective(BlogDO blogDO);

    void incrBlogView(List<Long> blogIds, Integer incr);

}
