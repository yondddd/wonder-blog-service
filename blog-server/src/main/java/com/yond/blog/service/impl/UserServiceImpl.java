package com.yond.blog.service.impl;

import com.yond.blog.entity.UserDO;
import com.yond.blog.mapper.UserMapper;
import com.yond.blog.service.UserService;
import com.yond.blog.util.encrypt.AesUtil;
import com.yond.blog.web.handler.session.UserSession;
import com.yond.common.exception.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户业务层接口实现类
 * @Author: Yond
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDO getByNameAndPassword(String username, String password) {
        UserDO user = userMapper.getByUserName(username);
        if (user == null) {
            return null;
        }
        if (!password.equals(AesUtil.decrypt(user.getPassword()))) {
            return null;
        }
        return user;
    }

    @Override
    public UserDO getById(Long id) {
        UserDO user = userMapper.getById(id);
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    public UserDO getByGuid(String guid) {
        return userMapper.getByGuid(guid);
    }

    @Override
    public String changeAccount(String userName, String pwd, UserSession userSession) {
        UserDO currentUser = userMapper.getByGuid(userSession.getGuid());
        if (currentUser == null) {
            return "当前操作用户查找为空";
        }
        if (!currentUser.getUsername().equals(userName)) {
            return "当前操作用户与修改用户不一致";
        }
        userMapper.updatePassword(currentUser.getId(), AesUtil.encrypt(pwd));
        return null;
    }

}
