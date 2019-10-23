package com.springjpa.functionalInterface;

import com.springjpa.dto.UserSalaryIncrementDto;

@FunctionalInterface
public interface SalarayIncrement {
	
	double incrementCalculation(UserSalaryIncrementDto userSalaryIncrementDto);
}
