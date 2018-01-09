package com.mry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mry.entity.User;
import com.mry.rps.UserRps;
import com.mry.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRps userRps;

	@Override
	@Transactional
	public String addone(User user) {
		try {
			userRps.save(user);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	@Override
	public User findByName(String name) {
		return userRps.findByName(name);
	}

}
