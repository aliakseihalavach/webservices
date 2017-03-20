package com.epam.mentoring.webservices.soap.client;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.webservices.bean.Task;
import com.epam.mentoring.webservices.soap.TaskService;

public class TaskClient {

	private TaskService taskClient;
	
	public Task getTask(long userID) {
		Task task = taskClient.getTask(userID);
		return task;
	}

	public long createTask(long userID, Task task) {
		taskClient.createTask(userID, task);
		return task.getTaskID();
	}

	public long updateTask(long userID, Task task) {
		taskClient.updateTask(userID, task);
		return task.getTaskID();
	}

	public void deleteTask(long taskID) {
		taskClient.deleteTask(taskID);
	}
	
	@Autowired
	public void setTaskClient(TaskService taskClient) {
		this.taskClient = taskClient;
	}
}
