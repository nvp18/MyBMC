package com.bmc.doctorService;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DoctorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorServiceApplication.class, args);
	}

	@Bean
	ObjectMetadata objectMetadata(){
		return new ObjectMetadata();
	}

}
