package com.userlogin.demo.dao;


import org.springframework.data.repository.CrudRepository;

import com.userlogin.demo.model.Admin;
public interface AdminDAO  extends CrudRepository<Admin, String>{
	
}
