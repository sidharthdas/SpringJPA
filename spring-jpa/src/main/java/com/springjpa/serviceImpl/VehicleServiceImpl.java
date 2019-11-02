package com.springjpa.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.VehicleDao;
import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.functionalInterface.PercentageCalculation;
import com.springjpa.model.Vehicle;
import com.springjpa.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private PercentageCalculation percentage;

	@Override
	public Vehicle addVechile(Vehicle vehicle) {
		return vehicleDao.save(vehicle);
	}

	@Override
	public String deleteVehicle(String vehicleName) {
		if (vehicleDao.findByVehicleName(vehicleName) != null) {
			vehicleDao.deleteVechile(vehicleName);
			return "Deleted";

		}
		return "Vehicle is not in database";

	}

	@Override
	public String updateVehicleDesc(VehicleDecUpdate vehicleDescUpdate) {
		// TODO Auto-generated method stub
		if (vehicleDao.findByVehicleName(vehicleDescUpdate.getVehicleName()) != null) {
			int i = vehicleDao.updateVehicleDesc(vehicleDescUpdate.getVehicleDesc(),
					vehicleDescUpdate.getVehicleName());
			if (i != 0) {
				return "Vehicle Desc is updated";
			}
			return "Internal issue.";
		}
		return "Vehicle is not repsent.";
	}

	@Override
	public List<Vehicle> allVehicle() {
		// TODO Auto-generated method stub
		return vehicleDao.allVehicle();
	}
	
	/*
	 * public void test() { float percent = percentage.percent(100, 20);
	 * System.out.println(percent);
	 * 
	 * }
	 */
	
	
}
