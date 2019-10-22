package com.springjpa.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.VehicleDao;
import com.springjpa.model.Vehicle;
import com.springjpa.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	@Override
	public Vehicle addVechile(Vehicle vehicle) {
		return vehicleDao.save(vehicle);
	}
	@Override
	public String deleteVehicle(String vehicleName) {
		if(vehicleDao.findByVehicleName(vehicleName) != null) {
			vehicleDao.deleteVechile(vehicleName);
			return "Deleted";
			
		}
		return "Vehicle is not in database";
		
	}

}
