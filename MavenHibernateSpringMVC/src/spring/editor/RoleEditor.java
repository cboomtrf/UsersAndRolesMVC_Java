package spring.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.model.Role;
import spring.service.RoleService;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Component
public class RoleEditor extends PropertyEditorSupport {

	@Autowired
	private RoleService roleService;

	// Used in Editor. Converts a String role id to a Role (when submitting form)
	@Override
	public void setAsText(String text) {
		//<form:option value="0">Select a role</form:option> in createUser is the link for text.equals("0");
		if (text.equals("0")) {
			Role r = null;
		} else {
			Role r = this.roleService.roleGetById(Long.valueOf(text));
			this.setValue(r);
		}
	}
}
