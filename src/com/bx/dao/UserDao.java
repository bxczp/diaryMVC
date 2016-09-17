package com.bx.dao;

import java.util.Map;

import com.bx.entity.User;

/**
 *@date 2016年4月7日
 * UserDao.java
 *@author CZP
 *@parameter
 */
public interface UserDao {
	
	
	public User login(Map<String,Object> map);
	
	
	public int update(User user);

}
