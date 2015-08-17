package com.spring.security.service.impl;

import org.springframework.stereotype.Service;

import com.spring.security.dao.IUserDao;
import com.spring.security.domain.MyUser;

@Service
public class UserServiceImpl {

	IUserDao userDao;

	public void saveUser(MyUser user) {
		userDao.saveUser(user);
	}

}
