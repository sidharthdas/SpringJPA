package com.springjpa.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.UserRepository;
import com.springjpa.dao.VehicleDao;
import com.springjpa.dto.UserSalaryIncrementDto;
import com.springjpa.functionalInterface.BonusComponent;
import com.springjpa.functionalInterface.SalarayIncrement;
import com.springjpa.model.UserDetail;
import com.springjpa.model.Vehicle;
import com.springjpa.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserRepository userRepository;
	
	
	

	@Autowired
	private VehicleDao vechileDao;

	SalarayIncrement salaryIncrement = (UserSalaryIncrementDto userSalaryIncrementDto) -> {
		Optional<UserDetail> users = userRepository.findByUserName(userSalaryIncrementDto.getUserName());
		double newSalary = ((users.get().getUserSalary() * userSalaryIncrementDto.getIncrementPercent()) / 100)
				+ users.get().getUserSalary();
		return newSalary;
	};

	BonusComponent bonusComponent = (UserDetail userDetail, String bonusAmount) -> {
		double bonusAmount1 = Double.valueOf(bonusAmount) + userDetail.getUserSalary();
		int i = userRepository.updateUserDetail(userDetail.getUserAdd(), bonusAmount1, userDetail.getUserName());
		if (i != 0) {
			Optional<UserDetail> user = userRepository.findByUserName(userDetail.getUserName());

			UserDetail ud = new UserDetail();
			ud.setId(user.get().getId());
			ud.setUserAdd(user.get().getUserAdd());
			ud.setUserName(user.get().getUserName());
			ud.setUserSalary(user.get().getUserSalary());
			ud.setVehicle(user.get().getVehicle());
			return ud;
		}
		return null;
	};

	@Override
	public UserDetail addUser(UserDetail ud) {
		// TODO Auto-generated method stub
		Optional<UserDetail> udd = userRepository.findByUserName(ud.getUserName());
		if (!udd.isPresent()) {
			return userRepository.save(ud);
		}
		System.out.println("User already present.");
		return null;
	}

	@Override
	public List<UserDetail> fetchUser(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return userRepository.fetchUser(userDetail.getUserName(), userDetail.getUserAdd());
	}

	@Override
	public String addVehicleToUser(String userName, String vehicleName) {
		// TODO Auto-generated method stub
		Optional<Vehicle> vehicle = vechileDao.findByVehicleName(vehicleName);
		if (userRepository.findByUserName(userName) != null) {
			if (vehicle.isPresent()) {
				int i = userRepository.addVehicleToUser(userName, vehicle.get().getVehicleId());
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

	@Override
	public String updateUser(UserDetail userDetail) {
		if (userRepository.findByUserName(userDetail.getUserName()) != null) {
			int i = userRepository.updateUserDetail(userDetail.getUserAdd(), userDetail.getUserSalary(),
					userDetail.getUserName());
			if (i != 0) {
				return "Userdetail is updated.";
			}
			return "Internal Server error.";
		}
		return "User is not available";
	}

	@Override
	public List<UserDetail> allUsers() {
		// TODO Auto-generated method stub
		return userRepository.allUsers();
	}

	@Override
	public String updateUserSalary(UserSalaryIncrementDto userSalaryIncrementDto) {
		// TODO Auto-generated method stub
		Optional<UserDetail> user = userRepository.findByUserName(userSalaryIncrementDto.getUserName());
		if (user.isPresent()) {
			double userNewSalary = salaryIncrement.incrementCalculation(userSalaryIncrementDto);
			// user.get().setUserSalary(userNewSalary);
			int i = userRepository.updateUserDetail(user.get().getUserAdd(), userNewSalary, user.get().getUserName());
			if (i != 0) {
				return "Updated";
			}
			return "Internal server error";
		}
		return "user not found";
	}

	@Override
	public List<UserDetail> userSalaryMoreThan10k() {
		// TODO Auto-generated method stub
		List<UserDetail> allUsers = userRepository.allUsers();
		List<UserDetail> userSalaryMoreThan1k = allUsers.stream().filter((x) -> x.getUserSalary() > 100000)
				.collect(Collectors.toList());
		return userSalaryMoreThan1k;
	}

	@Override
	public List<UserDetail> bonusToUsers(String bonusAmount) {
		// TODO Auto-generated method stub
		List<UserDetail> allUsers = userRepository.allUsers();
		List<UserDetail> updatedUsersWithBonus = allUsers.stream()
				.map((x) -> bonusComponent.updatedUserWithBonus(x, bonusAmount)).collect(Collectors.toList());
		return updatedUsersWithBonus;
	}

	@Override
	public List<UserDetail> allUsersLessThanSalaryRange(String salary) {
		// TODO Auto-generated method stub
		double sal = Double.valueOf(salary);
		return userRepository.allUsersLessThanSalaryRange(sal);
	}

	@Override
	public List<UserDetail> allUserGreaterThanSalaryRange(String salary) {
		// TODO Auto-generated method stub
		double sal = Double.valueOf(salary);
		List<UserDetail> allUsers = userRepository.allUsers();
		List<UserDetail> allUserGreaterThanSalaryRange = allUsers.stream().filter((x) -> x.getUserSalary() > sal).collect(Collectors.toList());
		return allUserGreaterThanSalaryRange;
	}

}
