package com.epam.mentoring.webservices.dao;

import com.epam.mentoring.webservices.bean.AbstaractBean;

public interface IBeanDAO <T extends AbstaractBean> {

    public Class<?> getBeanClass();
    
    public T get(long beanID);
    public void save(T bean);
    public void delete(long beanID);
}
