package com.epam.mentoring.webservices.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mentoring.webservices.bean.Task;
import com.epam.mentoring.webservices.bean.User;
import com.epam.mentoring.webservices.dao.TaskDAO;
import com.epam.mentoring.webservices.dao.UserDAO;

@Api(value = "Task API")
@RestController
@RequestMapping("/user/{userID}/task")
public class TaskController {

	protected TaskDAO taskDAO;
	protected UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/{taskID}")
	@ApiOperation(value = "Get Task", notes = "Returns a task with the provided ID")
	public Task getTask(@PathVariable long userID, @PathVariable long taskID) {
		Task task = taskDAO.get(taskID);
		if (task != null) {
			User methodLinkBuilder = methodOn(UserController.class).getUser(
					userID);
			Link userLink = linkTo(methodLinkBuilder).withRel("getUser");
			task.add(userLink);
		}

		return task;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/", consumes = {
			"application/xml", "application/json" })
	@ApiOperation(value = "Create Task", notes = "Creates a task with provided parameters")
	public long createTask(@PathVariable long userID, @RequestBody Task task) {
		User user = userDAO.get(userID);
		task.setUser(user);
		taskDAO.save(task);
		return task.getTaskID();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{taskID}", consumes = {
			"application/xml", "application/json" })
	@ApiOperation(value = "Update Task", notes = "Updates a task with provided parameters")
	public long updateTask(@PathVariable long userID,
			@PathVariable long taskID, @RequestBody Task task) {
		User user = userDAO.get(userID);
		task.setUser(user);
		task.setTaskID(taskID);
		taskDAO.save(task);
		return task.getTaskID();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{taskID}")
	@ApiOperation(value = "Delete Task", notes = "Deletes a task with provided ID")
	public void deleteTask(@PathVariable long userID, @PathVariable long taskID) {
		taskDAO.delete(taskID);
	}

	@Autowired
	public void setTaskDAO(TaskDAO taskManager) {
		this.taskDAO = taskManager;
	}

	@Autowired
	public void setUserDAO(UserDAO userManager) {
		this.userDAO = userManager;
	}
}
