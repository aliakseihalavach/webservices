package com.epam.mentoring.webservices.dao;

import com.epam.mentoring.webservices.bean.Task;

public class TaskDAO extends BeanDAO<Task> {

	@Override
	public Class<?> getBeanClass() {
		return Task.class;
	}
}
