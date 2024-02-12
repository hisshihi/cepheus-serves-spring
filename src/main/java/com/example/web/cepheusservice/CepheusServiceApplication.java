package com.example.web.cepheusservice;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CepheusServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepheusServiceApplication.class, args);
	}

}
