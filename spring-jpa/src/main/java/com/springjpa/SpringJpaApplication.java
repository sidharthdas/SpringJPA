package com.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com")
@EnableJpaRepositories(basePackages  ="com.springjpa.dao")
@EntityScan(basePackages = "com.springjpa.model")
@EnableTransactionManagement
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

}
