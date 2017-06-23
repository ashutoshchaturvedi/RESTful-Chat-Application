package com.cirtual.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(){
		return "Welcome to the RESTful Chat Application.";
	}
}
