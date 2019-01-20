package spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.*;
import spring.repository.*;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Service
@Transactional //This is the best location for Transactional.
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public UserService() {
		super();
	}
	
	public void addUser(User user) {
		userDAO.insertUser(user);
	}

	public void deleteUser(long id) {
		userDAO.deleteUser(id);
	}

	public void editUser(User user) {
		userDAO.updateUser(user);
	}

	public List<User> getUsers() {
		return userDAO.listUsers();
	}
	
	public User userGetById(long id) {
		return userDAO.getUserById(id);
	}
	
	public List<User> userGetByName(String name) {
		return userDAO.getUserByName(name);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
