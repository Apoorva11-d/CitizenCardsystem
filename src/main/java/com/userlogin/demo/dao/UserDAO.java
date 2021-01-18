package com.userlogin.demo.dao;


import org.springframework.data.repository.CrudRepository;

import com.userlogin.demo.model.User;
public interface UserDAO  extends CrudRepository<User, String>{
	
}