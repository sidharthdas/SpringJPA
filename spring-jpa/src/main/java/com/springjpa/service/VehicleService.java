package com.springjpa.service;

import java.util.List;

import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.model.Vehicle;

public interface VehicleService {
	
	Vehicle addVechile(Vehicle vehicle);
	String deleteVehicle(String vehicleName);
	String updateVehicleDesc(VehicleDecUpdate vehicleDescUpdate);
	List<Vehicle> allVehicle();


}
