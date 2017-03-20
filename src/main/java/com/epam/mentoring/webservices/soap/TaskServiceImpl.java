package com.epam.mentoring.webservices.soap;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.webservices.bean.Task;
import com.epam.mentoring.webservices.bean.User;
import com.epam.mentoring.webservices.dao.TaskDAO;
import com.epam.mentoring.webservices.dao.UserDAO;

@WebService(endpointInterface = "com.epam.mentoring.webservices.soap.TaskService", serviceName = "UserServiceImpl")
public class TaskServiceImpl implements TaskService {

	protected TaskDAO taskDAO;
	protected UserDAO userDAO;
	
	@Override
	public Task getTask(long taskID) {
		Task task = taskDAO.get(taskID);
		return task;
	}

	@Override
	public long createTask(long userID, Task task) {
		User user = userDAO.get(userID);
		task.setUser(user);
		taskDAO.save(task);
		return task.getID();
	}

	@Override
	public long updateTask(long userID, Task task) {	
		User user = userDAO.get(userID);
		task.setUser(user);
		taskDAO.save(task);
		return task.getID();
	}

	@Override
	public void deleteTask(long taskID) {
		taskDAO.delete(taskID);
	}


	@Autowired
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
