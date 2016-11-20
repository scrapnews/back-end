package com.scrapnews.model.dao;

import java.util.List;

import com.scrapnews.model.businessobjects.User;

public interface UserDao {

	User findByUserName(String username);
	
	List<User> listAllUsers();
	
	User getUserById(int i);
	
	User createUser(User user);
	
	void deleteUser(User user);
	
	void deleteUserByUsername(String username);
}