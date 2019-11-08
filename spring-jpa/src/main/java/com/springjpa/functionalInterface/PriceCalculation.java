package com.springjpa.functionalInterface;


@FunctionalInterface
public interface PriceCalculation {
	
	public double newPriceCalculation(double price, int percent);

}
