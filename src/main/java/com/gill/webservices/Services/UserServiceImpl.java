package com.gill.webservices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gill.webservices.DAO.UserDao;
import com.gill.webservices.model.User;
import com.gill.webservices.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
//	private UserDao userDao;
	private UserRepository userDao;
	@Override
	public List<User> retrieveAllUsers() {
		
		return userDao.findAll();
	}

	@Override
	public Optional<User> retrieveUser(int id) {
		
		return userDao.findById(id);
	}

	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}
	
	public void deleteUser(int id) {
		userDao.deleteById(id);
	}
}
