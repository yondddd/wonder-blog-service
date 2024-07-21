package com.yond.blog.service.impl;

import com.yond.blog.entity.UserDO;
import com.yond.blog.mapper.UserMapper;
import com.yond.blog.service.UserService;
import com.yond.blog.util.encrypt.AesUtil;
import com.yond.blog.util.jwt.JwtUtil;
import com.yond.common.constant.JwtConstant;
import com.yond.common.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户业务层接口实现类
 * @Author: Naccl
 * @Date: 2020-07-19
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
    public boolean changeAccount(UserDO user, String jwt) {
        Claims claims = JwtUtil.validateJwt(jwt, JwtConstant.DEFAULT_SECRET);
        String guid = claims.getSubject();
        UserDO currentUser = userMapper.getByGuid(guid);
        if (currentUser == null) {
            return false;
        }
        if (!currentUser.getUsername().equals(user.getUsername())) {
            return false;
        }
        userMapper.updatePassword(currentUser.getId(), AesUtil.encrypt(user.getPassword()));
        return true;
    }

    @Override
    public UserDO getByGuid(String guid) {
        return userMapper.getByGuid(guid);
    }

}
