package com.userlogin.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.userlogin.demo.dao.UserDAO;
import com.userlogin.demo.exceptionhandler.InputException;
import com.userlogin.demo.exceptionhandler.UserNotFoundException;
import com.userlogin.demo.model.User;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired(required=true)
	private UserDAO repository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		if(user.getUemailid()==null)
		{
			throw new InputException("EmailId can't be empty");
		}
		else if(user.getUpass()==null)
		{
			throw new InputException("Password can't be empty");
		}
		try 
		{
			   return repository.save(user);
	    }catch(DataIntegrityViolationException e)
		{
	    	throw new InputException("you are already an user, please go ahead and login");
		}
	}

	@Override
	public User updateUserPassword(String uemailid, User user1) {
		// TODO Auto-generated method stub
		User user=null;
		if(repository.findById(uemailid).isPresent()) {
		user=(User)repository.findById(uemailid).get();
		
		user.setUpass(user1.getUpass());
		
		}else 
			throw new UserNotFoundException(uemailid);
	 return repository.save(user);
	}

	@Override
	public String getUser(String uemailid, String upass) {
		// TODO Auto-generated method stub
		User user;
		String s="";
		if(repository.findById(uemailid).isPresent()) {
		user= repository.findById(uemailid).get();
		if(user.getUpass().equals(upass)) 
			s="Logged in successfully";
		else
			s="Invalid password";
	}else
		//s="invalid";
		throw new UserNotFoundException(uemailid);
		return s;
	}

}
