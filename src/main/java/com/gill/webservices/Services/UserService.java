package com.gill.webservices.Services;

import java.util.List;
import java.util.Optional;

import com.gill.webservices.model.User;

public interface UserService {

	public List<User> retrieveAllUsers();
	public Optional<User> retrieveUser(int id);
	public User createUser(User user);
	public void deleteUser(int id);
}
