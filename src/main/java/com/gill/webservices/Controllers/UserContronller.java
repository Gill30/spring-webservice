package com.gill.webservices.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gill.webservices.Exceptions.UserNotFoundException;
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
	public EntityModel<User> retrieveUser(@PathVariable int id ){
		User user = userService.retrieveUser(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+id);
		}
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
				
		return resource;
		
	}
	
	//post /users
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.createUser(user);
		
		URI location =ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id ){
		User user = userService.deleteUser(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+id);
		}
		return ResponseEntity.noContent().build();
	}
	
}
  