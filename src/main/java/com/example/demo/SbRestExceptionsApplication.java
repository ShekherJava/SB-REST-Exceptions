package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.User;

@SpringBootApplication
public class SbRestExceptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbRestExceptionsApplication.class, args);
	}
	
	@Bean
	public Map<Integer, User>  users() {
		Map<Integer, User> usersMap = new HashMap<>();
		usersMap.put(101, new User(101, "Clark", "clark@gmail.com"));
		usersMap.put(102, new User(102, "Blake", "blake@gmail.com"));
		usersMap.put(103, new User(103, "Mark", "mark@gmail.com"));
		return usersMap;
	}

}
