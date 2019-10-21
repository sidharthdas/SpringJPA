package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.UserDetail;
import com.springjpa.service.UserDetailService;

@RestController
public class UserDetailController {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@PostMapping("/add-user")
	public UserDetail addUser(@RequestBody UserDetail ud) {
		return userDetailService.addUser(ud);
	}
	
	@PostMapping("/user")
	public List<UserDetail> fetchUser(@RequestBody UserDetail ud) {
		return userDetailService.fetchUser(ud);
	}

}
