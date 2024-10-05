package com.wonder.blog.mapper;

import com.wonder.blog.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口
 * @Author: Yond
 */
@Mapper
@Repository
public interface UserMapper {

    UserDO getByUserName(String username);

    UserDO getById(Long id);

    UserDO getByGuid(String guid);

    void updatePassword(Long id, String password);
}
