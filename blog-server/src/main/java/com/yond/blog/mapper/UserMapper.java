package com.yond.blog.mapper;

import com.yond.blog.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口
 * @Author: Naccl
 * @Date: 2020-07-19
 */
@Mapper
@Repository
public interface UserMapper {

    UserDO getByUserName(String username);

    UserDO getById(Long id);

    UserDO getByGuid(String guid);

    void updatePassword(Long id, String password);
}
