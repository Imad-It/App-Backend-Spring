package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class AppBackendSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBackendSpringApplication.class, args);
	}

}
