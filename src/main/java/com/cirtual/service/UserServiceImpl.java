package com.cirtual.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cirtual.entity.Role;
import com.cirtual.entity.User;
import com.cirtual.repository.RoleRepository;
import com.cirtual.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cirtual.service.UserService#saveUser(com.cirtual.entity.User)
	 */
	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cirtual.service.UserService#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			result.add(user);
		}
		return result;
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean exists(Integer id) {
		return userRepository.exists(id);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			System.out.println(role.getRole());
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true,
				true, true, authorities);
	}

	@Override
	public void updateUser(Integer id, User user) {
		if (exists(id)) {
			User user1 = findById(id);
			if (user.getFirstName() != null) {
				user1.setFirstName(user.getFirstName());
			}
			if (user1.getLastName() != null) {
				user1.setLastName(user.getLastName());
			}
			userRepository.save(user1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cirtual.service.UserService#validateUser(org.springframework.security
	 * .core.Authentication, java.lang.Integer)
	 */
	@Override
	public boolean validateUser(Authentication authentication, Integer id) {
		if (authentication.getName().equalsIgnoreCase("anonymousUser")) {
			return false;
		}
		return userRepository.findByEmail(authentication.getName()).getId() == id;
	}

}
