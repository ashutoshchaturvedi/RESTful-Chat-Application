package com.cirtual.controller;

import com.cirtual.entity.User;
import com.cirtual.service.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getUserDetails(@PathVariable Integer id){
		if(userService.exists(id)){
			return userService.findById(id).toString();
		}
		return "The user id does not exists.";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createNewUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			return "There is already a user registered with the email provided";
		}
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			StringBuilder sb = new StringBuilder();
		    for (FieldError error : errors ) {
		        sb.append(error.getObjectName()).append(error.getDefaultMessage());
		    }
			return sb.toString();
		} else {
			userService.saveUser(user);
			String res = "User registered! The new user id is: " + user.getId();
			return res;
		}
	}
	
	@RequestMapping(value = "/editProfile/{id}", method = RequestMethod.PUT)
	public String editUser(@PathVariable Integer id, @RequestBody  @Valid User user, BindingResult bindingResult) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(userService.validateUser(authentication, id)){
			userService.updateUser(id,user);
			return "User updated successfully";
		}
		return "Only profile owner can edit his/her own profile";
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getUsersList() {
		return userService.getAllUsers();		
	}	
}

