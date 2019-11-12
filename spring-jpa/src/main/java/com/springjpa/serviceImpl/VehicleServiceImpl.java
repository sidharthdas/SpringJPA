package com.springjpa.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.VehicleDao;
import com.springjpa.dto.UpdatePriceOfVehicleDto;
import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.functionalInterface.PriceCalculation;
import com.springjpa.model.Vehicle;
import com.springjpa.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	
	PriceCalculation priceCalculation = (double price, int percent) ->{
		double increaseAmount = (price * percent) / 100;
		double newPriceTag = price - increaseAmount;
		return newPriceTag;
	};

	@Override
	public Vehicle addVechile(Vehicle vehicle) {
		Optional<Vehicle> vehicles = vehicleDao.findByVehicleName(vehicle.getVehicleName());
		if(!vehicles.isPresent()) {
		return vehicleDao.save(vehicle);
		}
		return new Vehicle();
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


	@Override
	public List<Vehicle> allVehicleWithGivenYear(String year) {
		// TODO Auto-generated method stub
		List<Vehicle> allVehicles = vehicleDao.allVehicle();
		List<Vehicle> vehicleWithManufacturedYear = allVehicles.stream().filter((x) -> x.getVechileManufactureYear().equals(year)).collect(Collectors.toList());
		return vehicleWithManufacturedYear;
	}


	@Override
	public String deleteVehicleManufactureYear(String vechileManufactureYear) {
		// TODO Auto-generated method stub
		vehicleDao.deleteVehiclesOfGivenDate(vechileManufactureYear);
		return "Vehicle made in year "+vechileManufactureYear+" is now removed from database";
	}
	
	
	@Override
	public String updatePriceOfVehicle(UpdatePriceOfVehicleDto updatePriceOfVehicleDto) {
		// TODO Auto-generated method stub
		Optional<Vehicle> vehicle = vehicleDao.findByVehicleName(updatePriceOfVehicleDto.getVehicleName());
		if(vehicle.isPresent()) {
			int i = vehicleDao.updatePriceOfVehicle(updatePriceOfVehicleDto.getVehicleName(), updatePriceOfVehicleDto.getVehiclePrice());
			if(i != 0) {
				return "Updated";
			}
		}
		return "Vehicle not present";
	}

	@Override
	public List<Vehicle> vehicleInGivenRange(String price) {
		// TODO Auto-generated method stub
		double vehiclePrice = Double.valueOf(price);
		List<Vehicle> allVehicle = vehicleDao.allVehicle();
		List<Vehicle> vehicleLessThanGivenRange = allVehicle.stream().filter((x) -> (x.getVehiclePrice() <= vehiclePrice)).collect(Collectors.toList());
		return vehicleLessThanGivenRange;
	}

	@Override
	public String updatePriceOfAllVehicleAfterDiscount(int discount) {
		// TODO Auto-generated method stub
		List<Vehicle> allVehicles = allVehicle();
		for(Vehicle v : allVehicles) {
			double newPrice = priceCalculation.newPriceCalculation(v.getVehiclePrice(), discount);
			UpdatePriceOfVehicleDto upvd = new UpdatePriceOfVehicleDto();
			upvd.setVehicleName(v.getVehicleName());
			upvd.setVehiclePrice(newPrice);
			updatePriceOfVehicle(upvd);
		}
		return "Price for all vehicles is updated";
	}

	@Override
	public List<Vehicle> changeManufactureyear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateVehicleQuantity(int quantity, String vehicleName) {
		Optional<Vehicle> vehicle = vehicleDao.findByVehicleName(vehicleName);
		if(!vehicle.equals(null)) {
			int i = vehicleDao.updateVehicleQuantity(quantity, vehicleName);
			if(i != 0) {
				return "Quantity is updated for vehicle "+vehicleName;
			}
			return "Internal Server error.";
		}
		return "Vehicle not present.";
	}

	@Override
	public String updateQuantityOfAllVehicles(int quantity) {
		// TODO Auto-generated method stub
		return null;
	}
}
