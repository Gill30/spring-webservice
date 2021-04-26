package com.gill.webservices.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gill.webservices.model.User;

@Component
public class UserDao {

	private static List<User> users = new ArrayList<User>();
	private static int usersCount  = 3;
	
	static {
		users.add(new User(1, "Ahmad", new Date()));
		users.add(new User(2, "Mujtaba", new Date()));
		users.add(new User(3, "Gill", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User Save(User user) {
		
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
