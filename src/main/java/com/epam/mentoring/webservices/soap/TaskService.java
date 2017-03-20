package com.epam.mentoring.webservices.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.epam.mentoring.webservices.bean.Task;

@WebService
public interface TaskService {

    @WebMethod
	public Task getTask(@WebParam(name = "taskID") long taskID);

    @WebMethod
	public long createTask(@WebParam(name = "userID") long userID, @WebParam(name = "task") Task task);

    @WebMethod
	public long updateTask(@WebParam(name = "userID") long userID, @WebParam(name = "task") Task task);

    @WebMethod
	public void deleteTask(@WebParam(name = "taskID") long taskID);
}
