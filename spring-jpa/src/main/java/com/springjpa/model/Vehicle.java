package com.springjpa.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;
	private String vehicleType;
	private String vehicleName;


	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVechileId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVechileType(String vehicleType) {
		this.vehicleType =vehicleType;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVechileName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

}
