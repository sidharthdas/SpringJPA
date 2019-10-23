package com.springjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "USERDETAIL")
@Table(name = "USERDETAIL")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "userName")
	private String userName;
	@Column(name = "userAdd")
	private String userAdd;
	private double userSalary;
	@OneToOne
	private Vehicle vehicle;

	public long getId() {
		return id;
	}

	public double getUserSalary() {
		return userSalary;
	}

	public void setUserSalary(double userSalary) {
		this.userSalary = userSalary;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAdd() {
		return userAdd;
	}

	public void setUserAdd(String userAdd) {
		this.userAdd = userAdd;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
