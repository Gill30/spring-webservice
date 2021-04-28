package com.gill.webservices.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.gill.webservices.Services.PostService;
import com.gill.webservices.Services.UserService;
import com.gill.webservices.model.Post;
import com.gill.webservices.model.User;

@RestController
public class UserContronller {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
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
		Optional<User> user = userService.retrieveUser(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user.get());
		
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
		userService.deleteUser(id);
//		if(user == null) {
//			throw new UserNotFoundException("id - "+id);
//		}
		return ResponseEntity.noContent().build();
	}
	

	
	@GetMapping(path = "/users/{id}/posts")
	public List<Post> retrieveAllUserPosts(@PathVariable int id ){
		Optional<User> user = userService.retrieveUser(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		return user.get().getPosts();
	}
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Object> createUser(@RequestBody Post post, @PathVariable int id) {
		Optional<User> user = userService.retrieveUser(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id - "+id);
		}
		
		User userObject = user.get();
		post.setUser(userObject);
		Post savedPost = postService.createPost(post);
		URI location =ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedPost.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
  