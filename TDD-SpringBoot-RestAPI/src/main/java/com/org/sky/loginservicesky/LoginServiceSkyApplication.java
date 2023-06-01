package com.org.sky.loginservicesky;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoginServiceSkyApplication {


	@Bean  // Step 6 - Including model mapper
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(LoginServiceSkyApplication.class, args);
	}

}
