package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

@RestController
@RequestMapping( value = "/users")
public class UserController {
	
	@Autowired
	Map<Integer, User> usersMap;
	
	@GetMapping( 
			value = "/{id}",
			produces = "application/json"
			)
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		User user = usersMap.get(id);
		if(user == null)
			throw new UserNotFoundException("User not found with id : " + id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping(
			value = "/create",
			consumes = "application/json"
			)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if( user.getId() == null || usersMap.containsKey(user.getId())) {
			throw new IllegalArgumentException("User id is invalie or already exist");
		}
		usersMap.put(user.getId(), user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	/*
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<>("Error : "+ ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<>("Invalid request : "+ ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	*/

}
