package com.userService.user;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	ObjectMetadata objectMetadata(){
		return new ObjectMetadata();
	}

}
