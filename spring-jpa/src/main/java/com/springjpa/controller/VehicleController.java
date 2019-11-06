package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.model.Vehicle;
import com.springjpa.service.VehicleService;

@RestController
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("/test123")
	public String test() {
		return "working";
	}

	@PostMapping("/vehicle")
	public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
		return vehicleService.addVechile(vehicle);
	}
	
	@DeleteMapping("/vehicle")
	public String deleteVehicle(@RequestBody String vehicleName) {
		return vehicleService.deleteVehicle(vehicleName);
	}
	
	@PutMapping("/vehicle")
	public String updateVehicleDesc(@RequestBody VehicleDecUpdate vehicleDescUpdate) {
		return vehicleService.updateVehicleDesc(vehicleDescUpdate);
	}
	
	@GetMapping("/all-vehicle")
	public List<Vehicle> allVehicle(){
		return vehicleService.allVehicle();
	}


	
	@GetMapping("/vehicle")
	public List<Vehicle> vehicleManufacturedInGivenYear(@RequestParam("year")String year){
		return vehicleService.allVehicleWithGivenYear(year);
	}

	
	@DeleteMapping("/vehicles")
	public String deleteVehicleManufactureYear(@RequestParam("year")String year) {
		return vehicleService.deleteVehicleManufactureYear(year);
	}
	
	@GetMapping("/vehicleToExcel")
	public String exportVehicleDataToExcel() {
		try {
			return vehicleService.exportVehicleDataToExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Server error";
	}

}
