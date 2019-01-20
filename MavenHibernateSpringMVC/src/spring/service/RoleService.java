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
@Transactional //beste locatie hiervoor
public class RoleService {
	
	@Autowired
	private RoleDAO roleDAO;

	public RoleService() {
		super();
	}
	
	public void addRole(Role role) {
		roleDAO.insertRole(role);
	}
	
	public void deleteRole(long id) {
		roleDAO.deleteRole(id);
	}
	
	public void editRole(Role role) {
		roleDAO.updateRole(role);
	}

	public List<Role> getRoles() {
		return roleDAO.listRoles();
	}
	
	public Role roleGetById(long id) {
		return roleDAO.getRoleById(id);
	}
	
	public List<Role> roleGetByName(String name) {
		return roleDAO.getRoleByName(name);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
