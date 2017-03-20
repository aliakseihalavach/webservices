package com.epam.mentoring.webservices.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.mentoring.webservices.bean.User;
import com.epam.mentoring.webservices.soap.UserService;

public class UserClient {

	private UserService userClient;

	public User getUser(long userID) {
		User user = userClient.getUser(userID);
		return user;
	}

	public long createUser(@RequestBody User user) {
		userClient.createUser(user);
		return user.getUserID();
	}

	public long updateUser(@PathVariable long userID, @RequestBody User user) {
		userClient.updateUser(user);
		return user.getUserID();
	}

	public void deleteUser(long userID) {
		userClient.deleteUser(userID);
	}

	@Autowired
	public void setUserClient(UserService userClient) {
		this.userClient = userClient;
	}
}
