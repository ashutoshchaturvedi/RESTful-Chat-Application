package com.cirtual.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.cirtual.entity.User;

public interface UserService {	
	public User findUserByEmail(String email);
	public User findById(Integer id);
	public void saveUser(User user);
	public void updateUser(Integer id, User user);
	public boolean exists(Integer id);
	public boolean validateUser(Authentication authentication, Integer id);
	public List<User> getAllUsers();
}