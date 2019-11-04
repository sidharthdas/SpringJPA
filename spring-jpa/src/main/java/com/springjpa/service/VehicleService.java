package com.springjpa.service;

import java.util.List;

import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.model.Vehicle;

public interface VehicleService {
	
	Vehicle addVechile(Vehicle vehicle);
	String deleteVehicle(String vehicleName);
	String updateVehicleDesc(VehicleDecUpdate vehicleDescUpdate);
	List<Vehicle> allVehicle();
<<<<<<< HEAD
=======
	List<Vehicle> allVehicleWithGivenYear(String year);
>>>>>>> f500e379e79ac66f48164bcc2979000622b0c856


}
