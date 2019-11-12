package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.dto.UpdatePriceOfVehicleDto;
import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.model.Vehicle;
import com.springjpa.service.ExcelExportService;
import com.springjpa.service.VehicleService;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private ExcelExportService excelExportService;

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
	public List<Vehicle> allVehicle() {
		return vehicleService.allVehicle();
	}

	@GetMapping("/vehicle")
	public List<Vehicle> vehicleManufacturedInGivenYear(@RequestParam("year") String year) {
		return vehicleService.allVehicleWithGivenYear(year);
	}

	@DeleteMapping("/vehicles")
	public String deleteVehicleManufactureYear(@RequestParam("year") String year) {
		return vehicleService.deleteVehicleManufactureYear(year);
	}

	@GetMapping("/vehicleToExcel")
	public String exportVehicleDataToExcel() {
		try {
			return excelExportService.vehicleDataExport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Server error";
	}

	@PutMapping("/vehicle-price-update")
	String updatePriceOfVehicle(@RequestBody UpdatePriceOfVehicleDto updatePriceOfVehicleDto) {
		return vehicleService.updatePriceOfVehicle(updatePriceOfVehicleDto);
	}

	@GetMapping("/vehicles")
	public List<Vehicle> vehicleInGivenRange(@RequestParam("price") String price) {
		return vehicleService.vehicleInGivenRange(price);
	}

	@PutMapping("/update-vehicle-price")
	public String updatePriceOfAllVehicleAfterDiscount(@RequestParam("discount") int discount) {
		return vehicleService.updatePriceOfAllVehicleAfterDiscount(discount);
	}

	@PutMapping("/update-quantity")
	public ResponseEntity<String> updateQuantityOfVehicle(@RequestParam("vehicle-name") String vehicleName,
			@RequestBody int quantity) {
		return new ResponseEntity<String>(vehicleService.updateVehicleQuantity(quantity, vehicleName), HttpStatus.OK);

	}

	@PutMapping("/update-quantity-of-all-vehicle")
	public ResponseEntity<String> updateQuantityOfAllVehicles(@RequestBody int quantity) {
		return new ResponseEntity<String>(vehicleService.updateQuantityOfAllVehicles(quantity), HttpStatus.OK);

	}

}
