package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceAss1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceAss1Application.class, args);
	}

}
