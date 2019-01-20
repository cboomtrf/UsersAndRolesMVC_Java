package spring.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.editor.*;
import spring.model.*;
import spring.service.*;

/**
 * 
 * @author CBoom (cboom.trf@gmail.com)
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleEditor roleEditor;

	private static String titleNew = "New role";
	private static String titleAgain = "New role, try again";
	private static String titleEdit = "Edit role";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, this.roleEditor);
	}

	@RequestMapping(value = "/list")
	public ModelAndView roleList() throws IOException {
		ModelAndView roleListView = new ModelAndView("ListRole");
		roleListView.addObject("roleList", roleService.getRoles());
		return roleListView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView roleAddPage() throws IOException {
		ModelAndView roleAddView = new ModelAndView("CreateRole");
		roleAddView.addObject("pageTitle", titleNew);
		roleAddView.addObject("role", new Role());
		return roleAddView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView userAdd(@ModelAttribute("role") @Valid Role role, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			ModelAndView roleAddView = new ModelAndView("CreateRole");
			roleAddView.addObject("pageTitle", titleAgain);
			roleAddView.addObject("role", role);
			return roleAddView;
		} else {
			ModelAndView roleListView = new ModelAndView("ListRole");
			roleService.addRole(role);
			roleListView.addObject("roleList", roleService.getRoles());
			String message = "Role was successfully added.";
			roleListView.addObject("message", message);
			return roleListView;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editRolePage(@PathVariable long id) {
		ModelAndView roleEditView = new ModelAndView("UpdateRole");
		roleEditView.addObject("pageTitle", titleEdit);
		roleEditView.addObject("role", roleService.roleGetById(id));
		return roleEditView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView roleEdit(@ModelAttribute("role") @Valid Role role, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			ModelAndView userEditView = new ModelAndView("UpdateRole");
			userEditView.addObject("pageTitle", titleAgain);
			userEditView.addObject("role", role);
			return userEditView;
		} else {
			ModelAndView roleListView = new ModelAndView("ListRole");
			roleService.editRole(role);
			roleListView.addObject("roleList", roleService.getRoles());
			String message = "Role was successfully edited.";
			roleListView.addObject("message", message);
			return roleListView;
		}
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ModelAndView removeRole(@PathVariable long id) {
		ModelAndView roleListView = new ModelAndView("ListRole");
		roleService.deleteRole(id);
		roleListView.addObject("roleList", roleService.getRoles());
		String message = "Role was successfully deleted.";
		roleListView.addObject("message", message);
		return roleListView;
	}
}
