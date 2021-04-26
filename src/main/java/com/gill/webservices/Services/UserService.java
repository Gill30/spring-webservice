package com.gill.webservices.Services;

import java.util.List;

import com.gill.webservices.model.User;

public interface UserService {

	public List<User> retrieveAllUsers();
	public User retrieveUser(int id);
	public User createUser(User user);
}
