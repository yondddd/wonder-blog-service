package com.yond.blog.service;

import com.yond.blog.entity.UserDO;
import com.yond.blog.web.handler.session.UserSession;

public interface UserService {
    
    UserDO getByNameAndPassword(String username, String password);
    
    UserDO getById(Long id);
    
    UserDO getByGuid(String guid);
    
    String changeAccount(String userName, String pwd, UserSession userSession);
    
}
