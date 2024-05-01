package com.yond.service;

import com.yond.entity.User;

public interface UserService {
	User findUserByUsernameAndPassword(String username, String password);

	User findUserById(Long id);

	boolean changeAccount(User user, String jwt);
}
