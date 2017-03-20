package com.epam.mentoring.webservices.soap;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.mentoring.webservices.bean.User;
import com.epam.mentoring.webservices.dao.UserDAO;

@WebService(endpointInterface = "com.epam.mentoring.webservices.soap.UserService", serviceName = "UserServiceImpl")
public class UserServiceImpl implements UserService {

	protected UserDAO userDAO;
	
	@Override
	public User getUser(long userID) {
		User user = userDAO.get(userID);
		return user;
	}

	@Override
	public long createUser(User user) {
		userDAO.save(user);
		return user.getID();
	}

	@Override
	public long updateUser(User user) {
		userDAO.save(user);
		return user.getID();
	}

	@Override
	public void deleteUser(long userID) {
		userDAO.delete(userID);
	}
	
	@Autowired
	public void setUserManager(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
