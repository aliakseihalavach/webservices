package com.epam.mentoring.webservices.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.epam.mentoring.webservices.bean.User;

@WebService
public interface UserService {

    @WebMethod
	public User getUser(@WebParam(name = "userID") long userID);

    @WebMethod
	public long createUser(@WebParam(name = "user") User user);

    @WebMethod
	public long updateUser(@WebParam(name = "user") User user);

    @WebMethod
	public void deleteUser(@WebParam(name = "userID") long userID);
}
