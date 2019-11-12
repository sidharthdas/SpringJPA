package com.springjpa.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.springjpa.dto.UpdatePriceOfVehicleDto;
import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.model.Vehicle;

public interface VehicleService {

	Vehicle addVechile(Vehicle vehicle);

	String deleteVehicle(String vehicleName);

	String updateVehicleDesc(VehicleDecUpdate vehicleDescUpdate);

	List<Vehicle> allVehicle();

	List<Vehicle> allVehicleWithGivenYear(String year);

	String deleteVehicleManufactureYear(String vechileManufactureYear);

	String updatePriceOfVehicle(UpdatePriceOfVehicleDto updatePriceOfVehicleDto);

	List<Vehicle> vehicleInGivenRange(String price);

	String updatePriceOfAllVehicleAfterDiscount(int discount);

	List<Vehicle> changeManufactureyear(String year);

	String updateVehicleQuantity(int quantity, String vehicleName);
	
	String updateQuantityOfAllVehicles(int quantity);

}
