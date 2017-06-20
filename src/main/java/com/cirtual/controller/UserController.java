package com.cirtual.controller;

import com.cirtual.entity.User;
import com.cirtual.restrepository.UserRepository;
import com.cirtual.service.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	/*
	 * @Autowired protected AuthenticationManager authenticationManager;
	 */
	/*
	 * @Autowired private UserService userService;
	 */

	@RequestMapping("/listUsers")
	public Iterable<User> getAllUsers() {
		System.out.println("in listing");
		return userRepository.findAll();
	}

	/*
	 * @RequestMapping(value = "/user/register", method = RequestMethod.POST)
	 * public String registerPost(@Valid User user, BindingResult result) { if
	 * (result.hasErrors()) { return "user/register"; }
	 * 
	 * User registeredUser = userService.register(user); if (registeredUser !=
	 * null) { return "user/register-success"; } else {
	 * result.rejectValue("userName", "error.alreadyExists",
	 * "This username already exists."); return "user/register"; } }
	 * 
	 * @RequestMapping("/user/edit/{id}") public String edit(@PathVariable("id")
	 * Integer id, User user) { User u; User loggedInUser =
	 * userService.getLoggedInUser(); if(id == 0) { id = loggedInUser.getId(); }
	 * if(loggedInUser.getId() != id && !loggedInUser.isAdmin()) { return
	 * "user/premission-denied"; } else if (loggedInUser.isAdmin()) { u =
	 * userRepository.findOne(id); } else { u = loggedInUser; }
	 * 
	 * user.setId(u.getId()); user.setUserName(u.getUserName());
	 * user.setAge(u.getAge()); user.setProfession(u.getProfession());
	 * user.setEmailId(u.getEmailId()); user.setFirstName(u.getFirstName());
	 * user.setLastName(u.getLastName());
	 * 
	 * return "/user/edit"; }
	 * 
	 * @RequestMapping("/users") public String addNewUser(@RequestParam String
	 * firstName, @RequestParam String lastName, @RequestParam int
	 * age, @RequestParam String profession) { System.out.println("in /users");
	 * User user = new User();
	 * 
	 * user.setFirstName(firstName); user.setLastName(lastName);
	 * user.setAge(age); user.setProfession(profession);
	 * 
	 * userRepository.save(user);
	 * 
	 * return "Saved Successfully"; }
	 * 
	 * @RequestMapping("/listUsers/{userName}") public User
	 * getUsersByUserName(@PathVariable("userName") String userName) {
	 * System.out.println("hi i am here");
	 * 
	 * return userRepository.findOneByUserName(userName); }
	 */
}
