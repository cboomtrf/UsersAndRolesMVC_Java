package spring.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.*;
import spring.repository.*;

/**
 * THE NON DATABASE VARIANT OF ROLESERVICE
 * NOTE: For this, a generated ID is needed to get the GetById working.
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Service
public class RoleServiceWithArraylist {
	
	private static List<Role> roleList;

	public RoleServiceWithArraylist() {
		this.roleList = new ArrayList<Role>();
	}
	
	public void addRole(Role role) {
		roleList.add(role);
	}
	
	public void deleteRole(long id) {
		roleList.remove(roleGetById(id));
	}
	
	public void editRole(Role role) {
		roleList.set(indexGetById(role.getId()), role);
	}

	public List<Role> getRoles() {
		return roleList; 
	}

	public Role roleGetById(long id) {
		Role searchrole = new Role();
		for (Role r : roleList) {
			if (r.getId() == id)
				searchrole = r;
		}
		return searchrole;
	}

	public int indexGetById(long id) {
		int index = -1;
		for (Role r : roleList) {
			if (r.getId() == id)
				index = roleList.indexOf(r);
		}
		return index;
	}

}
