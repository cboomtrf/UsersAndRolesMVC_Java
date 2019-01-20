package spring.repository;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.model.*;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Repository
@Transactional
public class RoleDAO {
	
	@Autowired
	private SessionFactory sessionFactory; //refers to spring xml's bean.
	private Session session;
	
	public RoleDAO() {
		super();
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void insertRole(Role role) {
		session = getCurrentSession();
		session.save(role);
	}

	public List<Role> listRoles() {
		if ( sessionFactory != null ) {
			session = getCurrentSession();
			String hql = "from Role";
			Query query = session.createQuery(hql);
			List<Role> roleList = (List<Role>) query.list();
			return roleList;
		} else {
			System.out.println("WARNING: Session factory does not exist!");
			return null;
		}
	}
	
	public Role getRoleById(long id) {
		session = getCurrentSession();
		String hql = "from Role u where u.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Role role = (Role) query.list().get(0);
		return role;
	}

	public List<Role> getRoleByName(String name) {
		session = getCurrentSession();
		String hql = "from Role u where u.name= :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<Role> roleList = (List<Role>) query.list();
		return roleList;
	}

	public void deleteRole(long id) {
		session = getCurrentSession();
		String hql = "from Role u where u.id= :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Role role = (Role) query.list().get(0);
		session.delete(role);
	}

	public void updateRole(Role role){
		session = getCurrentSession();
		long id = role.getId();
		String hql = "from Role u where u.id= :id";
		Query query = session.createQuery(hql);	
		query.setParameter("id", id);
		session.update(role);
	}
}

