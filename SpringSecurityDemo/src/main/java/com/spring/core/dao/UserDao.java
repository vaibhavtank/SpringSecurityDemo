package com.spring.core.dao;

import com.spring.core.model.User;

public interface UserDao {
	
	/**
	 * @param username
	 * @return User
	 */
	User findByUserName(String username);
}