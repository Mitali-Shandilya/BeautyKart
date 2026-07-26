package com.example.cartServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CartServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServicesApplication.class, args);
	}

}
