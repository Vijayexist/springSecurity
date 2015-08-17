package com.spring.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.dao.impl.UserDaoImpl;
import com.spring.security.domain.MyUser;

@Service
public class AuthServiceImpl implements  UserDetailsService {

	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDaoImpl userDao;
		userDao=new UserDaoImpl();
		MyUser details = userDao.getUser(username);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(
				"ROLE_USER");
		SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(
				"ROLE_ADMIN");
		if (details.getRole().equals("user"))
			authorities.add(userAuthority);
		else if (details.getRole().equals("admin")) {
			authorities.add(userAuthority);
			authorities.add(adminAuthority);
		}
		UserDetails user = new User(details.getUsername(),
				details.getPassword(), true, true, true, true, authorities);
		return user;
	}
}
