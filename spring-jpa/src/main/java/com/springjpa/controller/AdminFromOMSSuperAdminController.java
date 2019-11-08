package com.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("api")
public class AdminFromOMSSuperAdminController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public String test() {
		return "working";
	}

	@GetMapping("/all-super-admins")
	@HystrixCommand(fallbackMethod = "getFallbackGetAllAdmin", commandProperties = { /*
																						 * @HystrixProperty(name =
																						 * "hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds",
																						 * value = "2000"),
																						 * 
																						 * @HystrixProperty(name =
																						 * "circuitBreaker.requestVolumeThreshold",
																						 * value = "5"),
																						 * 
																						 * @HystrixProperty(name =
																						 * "circuitBreaker.errorThresholdPercentage",
																						 * value = "50"),
																						 * 
																						 * @HystrixProperty(name =
																						 * "circuitBreaker.sleepwindowInMilliseconds",
																						 * value = "5000")
																						 */ })
	public Object getAllAdmin() {
		Object allAdmins = restTemplate.getForObject("http://localhost:9090/api/super-admin/show-all-admins",
				Object.class);
		return allAdmins;
	}

	public String getFallbackGetAllAdmin() {
		return "Tmporrary Out of Service";
	}

}
