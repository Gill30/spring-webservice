package com.gill.webservices.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gill.webservices.Services.UserService;
import com.gill.webservices.model.User;

@RestController
public class UserContronller {

	@Autowired
	private UserService userService;
	
	//Get /users
	//retrieveAllUsers
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return userService.retrieveAllUsers();
	}
	
	//Get /users/{id}
	//retrieveUser(int id)
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id ){
		return userService.retrieveUser(id);
	}
	
	//post /users
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userService.createUser(user);
		
		URI location =ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
