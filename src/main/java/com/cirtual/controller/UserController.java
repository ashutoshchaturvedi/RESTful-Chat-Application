package com.cirtual.controller;

import com.cirtual.entity.User;
import com.cirtual.service.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createNewUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		System.out.println("got in controller");
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			return "There is already a user registered with the email provided";
		}
		if (bindingResult.hasErrors()) {
			return "There is some error while registration of the user.";
		} else {
			userService.saveUser(user);
			String res = "The new user id is: " + user.getId();
			return res;
		}
	}
	
	/*@RequestMapping(value = "/register", method = RequestMethod.PUT)
	public String editUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		System.out.println("got in controller");
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			return "There is already a user registered with the email provided";
		}
		if (bindingResult.hasErrors()) {
			return "There is some error while registration of the user.";
		} else {
			userService.saveUser(user);
			String res = "The new user id is: " + user.getId();
			return res;
		}
	}*/
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<String> getUsersList() {
		System.out.println("getUsersList in controller");
		return null;
		
	}
	
}

