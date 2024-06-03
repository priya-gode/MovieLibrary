package com.emp.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.emp.springboot"})
public class MovieManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieManagementSystemApplication.class, args);
		System.out.println("Welcome to Movie Management System");
	}
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
