package com.userlogin.demo.service;

import com.userlogin.demo.model.User;

public interface IUserService {

	public User saveUser(User user);
	public User updateUserPassword(String uemailid, User user);
	public String getUser(String uemailid, String upass);
	
}
