package com.epam.mentoring.webservices.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mentoring.webservices.bean.Task;
import com.epam.mentoring.webservices.bean.User;
import com.epam.mentoring.webservices.dao.UserDAO;

@Api(value = "User API")
@RestController
@RequestMapping("/user")
public class UserController {

	protected UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/{userID}")
	@ApiOperation(value = "Get User", notes = "Returns a user with the provided ID")
	public User getUser(@PathVariable long userID) {
		User user = userDAO.get(userID);
		setLinks(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/", consumes = {
			"application/xml", "application/json" })
	@ApiOperation(value = "Create User", notes = "Creates a user with provided parameters")
	public long createUser(@RequestBody User user) {
		userDAO.save(user);
		return user.getUserID();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{userID}", consumes = {
			"application/xml", "application/json" })
	@ApiOperation(value = "Update User", notes = "Updates a user with provided parameters")
	public long updateUser(@PathVariable long userID, @RequestBody User user) {
		user.setUserID(userID);
		userDAO.save(user);
		return user.getUserID();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userID}")
	@ApiOperation(value = "Delete User", notes = "Deletes a user with provided ID")
	public void deleteUser(@PathVariable long userID) {
		userDAO.delete(userID);
	}

	private void setLinks(User user) {
		if (user != null) {
			Link selfLink = linkTo(UserController.class)
					.slash(user.getUserID()).withSelfRel();
			user.add(selfLink);
			for (Task task : user.getTasks()) {
				Task methodLinkBuilder = methodOn(TaskController.class)
						.getTask(user.getUserID(), task.getID());
				Link taskLink = linkTo(methodLinkBuilder).withRel("getTask");
				user.add(taskLink);
			}
		}
	}
	
	@Autowired
	public void setUserManager(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
