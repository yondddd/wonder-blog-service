package com.yond.blog.service;

import com.yond.blog.entity.UserDO;

public interface UserService {

    UserDO getByNameAndPassword(String username, String password);

    UserDO getById(Long id);

    boolean changeAccount(UserDO user, String jwt);

    UserDO getByGuid(String guid);
}
