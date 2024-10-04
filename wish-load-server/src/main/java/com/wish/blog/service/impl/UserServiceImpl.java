package com.wish.blog.service.impl;

import com.wish.blog.entity.UserDO;
import com.wish.blog.mapper.UserMapper;
import com.wish.blog.service.UserService;
import com.wish.blog.util.encrypt.AesUtil;
import com.wish.blog.web.handler.session.UserSession;
import com.wish.common.exception.NotFoundException;
import com.wish.common.utils.env.env.EnvConstant;
import com.wish.common.utils.env.env.Environment;
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
        if (!password.equals(AesUtil.decrypt(user.getPassword(), Environment.getProperty(EnvConstant.USER_PASSWORD_SECRET_KEY)))) {
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
        userMapper.updatePassword(currentUser.getId(), AesUtil.encrypt(pwd, Environment.getProperty(EnvConstant.USER_PASSWORD_SECRET_KEY)));
        return null;
    }

}
