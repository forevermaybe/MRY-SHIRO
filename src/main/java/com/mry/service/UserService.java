package com.mry.service;

import com.mry.entity.User;

public interface UserService {

	String addone(User user);
	
	User findByName(String name);
}
