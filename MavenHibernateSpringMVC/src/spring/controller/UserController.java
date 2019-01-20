package spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
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
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleEditor roleEditor;
	
//	@Autowired //alternative 1 for hidden id in form... is not a good idea.
//	private HttpSession httpSession;

	private static String titleNew = "New user";
	private static String titleAgain = "New user, try again";
	private static String titleEdit = "Edit user";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, this.roleEditor);
	}

	@RequestMapping(value = "/list")
	public ModelAndView userList() throws IOException {
		ModelAndView userListView = new ModelAndView("ListUser");
		userListView.addObject("userList", userService.getUsers());
		return userListView;
	}

	/**
	 * Is used when visiting page add.
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView userAddPage() throws IOException { //alternative2: kan ook als HttpSession session als argument meegeven bij request handler
		ModelAndView userAddView = new ModelAndView("CreateUser");
		userAddView.addObject("pageTitle", titleNew);
		User user = new User();
//		httpSession.setAttribute("newUser", user); //alternative1
		userAddView.addObject("user", user);
		userAddView.addObject("roleList", roleService.getRoles());
		return userAddView;
	}

	/**
	 * Is used when pressing submit button in /add.
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView userAdd(@ModelAttribute("user") @Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			ModelAndView userAddView = new ModelAndView("CreateUser");
			userAddView.addObject("pageTitle", titleAgain);
			userAddView.addObject("user", user);
			userAddView.addObject("roleList", roleService.getRoles());
			return userAddView;
		} else {
			ModelAndView userListView = new ModelAndView("ListUser");
			userService.addUser(user);
			userListView.addObject("userList", userService.getUsers());
			String message = "User was successfully added.";
			userListView.addObject("message", message);
			return userListView;
		}
	}

	/**
	 * Is used to edit user in a displayed list.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable long id) {
		ModelAndView userEditView = new ModelAndView("UpdateUser");
		userEditView.addObject("pageTitle", titleEdit);
		userEditView.addObject("user", userService.userGetById(id));
		userEditView.addObject("roleList", roleService.getRoles());
		return userEditView;
	}

	/**
	 * Is used when visiting /edit page where params are given.
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView userEdit(@ModelAttribute("user") @Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			ModelAndView userEditView = new ModelAndView("UpdateUser");
			userEditView.addObject("pageTitle", titleAgain);
			userEditView.addObject("user", user);
			userEditView.addObject("roleList", roleService.getRoles());
			return userEditView;
		} else {
			ModelAndView userListView = new ModelAndView("ListUser");
			userService.editUser(user);
			userListView.addObject("userList", userService.getUsers());
			String message = "User was successfully edited.";
			userListView.addObject("message", message);
			return userListView;
		}
	}

	/**
	 * Is used to remove user from a displayed list.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public ModelAndView removeUser(@PathVariable long id) {
		ModelAndView userListView = new ModelAndView("ListUser");
		userService.deleteUser(id);
		userListView.addObject("userList", userService.getUsers());
		String message = "User was successfully deleted.";
		userListView.addObject("message", message);
		return userListView;
	}
}
