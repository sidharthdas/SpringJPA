package com.springjpa.service;

import java.util.List;

import com.springjpa.model.UserDetail;

public interface UserDetailService {
	
	public UserDetail addUser(UserDetail ud);
	List<UserDetail> fetchUser(UserDetail userDetail);
	String addVehicleToUser(String userName, String vehicleName);
	String deleteUser(String userName);

}
