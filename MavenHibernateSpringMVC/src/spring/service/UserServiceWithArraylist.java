package spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.model.*;
import spring.repository.*;

/**
 * THE NON DATABASE VARIANT OF USERSERVICE
 * * NOTE: For this, a generated ID is needed to get the GetById working.
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Service
public class UserServiceWithArraylist {
	
	private static List<User> userList;

	public UserServiceWithArraylist() {
		this.userList = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		userList.add(user);
	}

	public void deleteUser(long id) {
		userList.remove(userGetById(id));
	}

	public void editUser(User user) {
		userList.set(indexGetById(user.getId()), user);
	}

	public List<User> getUsers() {
		return userList;
	}

	/**
	 * Dit is alleen nodig voor de arraylist, non-db.
	 * Verder is het beter dit alleen in de DAO te doen.
	 * @param id
	 * @return
	 */
	public User userGetById(long id) {
		User searchuser = new User();
		for (User r : userList) {
			if (r.getId() == id) {
				searchuser = r;
			}
		}
		return searchuser;
	}

	/**
	 * Dit is alleen nodig voor de arraylist, non-db.
	 * @return
	 */
	public int indexGetById(long id) {
		int index = -1;
		for (User u : userList) {
			if (u.getId() == id) {
				index = userList.indexOf(u);
			}
		}
		return index;
	}

}
