package com.example.productServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients  //to enable microservices
@SpringBootApplication
public class BeautyCartBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautyCartBackendApplication.class, args);
	}

}
