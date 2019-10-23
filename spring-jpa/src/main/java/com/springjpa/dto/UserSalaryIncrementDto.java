package com.springjpa.dto;

public class UserSalaryIncrementDto {

	private String userName;
	private Long incrementPercent;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getIncrementPercent() {
		return incrementPercent;
	}

	public void setIncrementPercent(Long incrementPercent) {
		this.incrementPercent = incrementPercent;
	}

}
