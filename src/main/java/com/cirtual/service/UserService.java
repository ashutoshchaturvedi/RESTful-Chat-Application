package com.cirtual.service;

import com.cirtual.entity.User;

public interface UserService {
	public boolean exists(Integer id);
	public User findUserByEmail(String email);
	public User findById(Integer id);
	public void saveUser(User user);
}