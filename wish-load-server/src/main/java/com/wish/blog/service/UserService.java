package com.wish.blog.service;

import com.wish.blog.entity.UserDO;
import com.wish.blog.web.handler.session.UserSession;

public interface UserService {
    
    UserDO getByNameAndPassword(String username, String password);
    
    UserDO getById(Long id);
    
    UserDO getByGuid(String guid);
    
    String changeAccount(String userName, String pwd, UserSession userSession);
    
}
