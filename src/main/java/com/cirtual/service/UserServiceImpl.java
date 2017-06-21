package com.cirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cirtual.entity.User;
import com.cirtual.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
/*	@Autowired
    private RoleRepository roleRepository;*/
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(User user) {
		System.out.println("here in service impl");
		System.out.println(user.getPassword());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
   //     Role userRole = roleRepository.findByRole("ADMIN");
   //     user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean exists(Integer id) {
		return userRepository.exists(id);
	}
}
