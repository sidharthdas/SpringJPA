package com.springjpa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.UserRepository;
import com.springjpa.dao.VehicleDao;
import com.springjpa.model.UserDetail;
import com.springjpa.model.Vehicle;
import com.springjpa.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VehicleDao vechileDao;

	@Override
	public UserDetail addUser(UserDetail ud) {
		// TODO Auto-generated method stub
		return userRepository.save(ud);
		// return null;
	}

	@Override
	public List<UserDetail> fetchUser(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return userRepository.fetchUser(userDetail.getUserName(), userDetail.getUserAdd());
	}

	@Override
	public String addVehicleToUser(String userName, String vehicleName) {
		// TODO Auto-generated method stub
		Vehicle vehicle = vechileDao.findByVehicleName(vehicleName);
		if (userRepository.findByUserName(userName) != null) {
			if (vehicle != null) {
				int i = userRepository.addVehicleToUser(userName, vehicle.getVehicleId());
				System.out.println(i);
				return "Updated";
			}
			return "Vechile not found";
		}
		return "User not found";
	}

	@Override
	public String deleteUser(String userName) {
		// TODO Auto-generated method stub

		if (userRepository.findByUserName(userName) != null) {
			userRepository.deleteUser(userName);
			return "Deleted";
		}

		return "User Not Present.";
	}

}
