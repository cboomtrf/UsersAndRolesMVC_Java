package spring.repository;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.*;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory; //refers to spring xml.
	private Session session;

	public UserDAO() {
		super();
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void insertUser(User user) {
		session = getCurrentSession();
		session.save(user);
	}

	public void updateUser(User user){
		session = getCurrentSession();
		long id = user.getId();
		String hql = "from User u where u.id= :id";
		Query query = session.createQuery(hql);	
		query.setParameter("id", id);
		session.update(user);	
	}
	
	public void deleteUser(long id) {
		session = getCurrentSession();
		String hql = "from User u where u.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		User user = (User) query.list().get(0);
		session.delete(user);
	}

	public List<User> listUsers() {
		if ( sessionFactory != null ) {
			session = getCurrentSession();
			String hql = "from User";
			Query query = session.createQuery(hql);
			List<User> userList = (List<User>) query.list();
			return userList;
		} else {
			System.out.println("WARNING: Session factory does not exist!");
			return null;
		}
	}
	
	/**
	 * Het is beter deze methode te gebruiken dan die in UserService.
	 * @param id
	 * @return
	 */
	public User getUserById(long id) {
		session = getCurrentSession();
		String hql = "from User u where u.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<User> result = (List<User>) query.list();
		User user = result.get(0);
		return user;
	}
	
	public List<User> getUserByName(String name) {
		Session session = getCurrentSession();
		String hql = "from User u where u.name= :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<User> userList = (List<User>) query.list();
		return userList;
	}

}
