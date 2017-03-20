package com.epam.mentoring.webservices.dao;

import com.epam.mentoring.webservices.bean.User;

public class UserDAO extends BeanDAO<User> {

	@Override
	public Class<?> getBeanClass() {
		return User.class;
	}
}
