package com.springjpa.service;

import java.util.List;

import com.springjpa.dto.UserSalaryIncrementDto;
import com.springjpa.model.UserDetail;

public interface UserDetailService {
	
	public UserDetail addUser(UserDetail ud);
	List<UserDetail> fetchUser(UserDetail userDetail);
	String addVehicleToUser(String userName, String vehicleName);
	String deleteUser(String userName);
	String updateUser(UserDetail userDetail);
	List<UserDetail> allUsers();
	String updateUserSalary(UserSalaryIncrementDto userSalaryIncrementDto);
	List<UserDetail> userSalaryMoreThan10k();
	List<UserDetail> bonusToUsers(String bonusAmount);

}
