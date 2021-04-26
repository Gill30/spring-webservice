package com.gill.webservices.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gill.webservices.DAO.UserDao;
import com.gill.webservices.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Override
	public List<User> retrieveAllUsers() {
		
		return userDao.findAll();
	}

	@Override
	public User retrieveUser(int id) {
		
		return userDao.findOne(id);
	}

	@Override
	public User createUser(User user) {
		return userDao.Save(user);
	}
	
	public User deleteUser(int id) {
		return userDao.deleteById(id);
	}
}
