package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.dto.UserSalaryIncrementDto;
import com.springjpa.dto.UserVechileDto;
import com.springjpa.model.UserDetail;
import com.springjpa.service.UserDetailService;

@RestController
@RequestMapping("api")
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

	@PostMapping("/vechile-to-user")
	public String addVehicleToUser(@RequestBody UserVechileDto ud) {
		System.out.println(ud.getVehicleName());
		return userDetailService.addVehicleToUser(ud.getUserName(), ud.getVehicleName());
	}

	@DeleteMapping("/user")
	public String deleteUser(@RequestBody String userName) {
		System.out.println(userName);
		return userDetailService.deleteUser(userName);
	}

	@PutMapping("/user")
	public String updateUser(@RequestBody UserDetail userDetail) {
		return userDetailService.updateUser(userDetail);
	}

	@GetMapping("/all-users")
	public List<UserDetail> allUsers() {
		return userDetailService.allUsers();
	}

	@PutMapping("/salary-increment")
	public String updateUserSalary(@RequestBody UserSalaryIncrementDto userSalaryIncrementDto) {
		return userDetailService.updateUserSalary(userSalaryIncrementDto);
	}

	@GetMapping("/salary-more-than-10k")
	public List<UserDetail> userSalaryMoreThan10k() {
		return userDetailService.userSalaryMoreThan10k();
	}
	
	@PutMapping("/update-salary-of-all-users")
	public List<UserDetail> updatedSalaryOfAllUser(@RequestBody String bonusAmount) {
		return userDetailService.bonusToUsers(bonusAmount);
	}

}
