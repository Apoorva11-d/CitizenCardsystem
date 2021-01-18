
package com.userlogin.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.userlogin.demo.dao.AdminDAO;
import com.userlogin.demo.exceptionhandler.InputException;
import com.userlogin.demo.exceptionhandler.UserNotFoundException;
import com.userlogin.demo.model.Admin;
import com.userlogin.demo.model.User;


@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired(required=true)
	private AdminDAO repository;

	@Override
	public Admin saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(admin.getAemailid()==null)
		{
			throw new InputException("EmailId can't be empty");
		}
		else if(admin.getApass()==null)
		{
			throw new InputException("Password can't be empty");
		}
		try 
		{
			   return repository.save(admin);
	    }catch(DataIntegrityViolationException e)
		{
	    	throw new InputException("you are already an user, please go ahead and login");
		}
	}

	@Override
	public Admin updateAdminPassword(String aemailid, Admin admin1) {
		// TODO Auto-generated method stub
		Admin admin=null;
		if(repository.findById(aemailid).isPresent()) {
		admin=repository.findById(aemailid).get();		
		admin.setApass(admin1.getApass());
		
		}else 
			throw new UserNotFoundException(aemailid);
	 return repository.save(admin);
	}

	@Override
	public String getAdmin(String aemailid, String apass) {
		// TODO Auto-generated method stub
		Admin admin;
		String s="";
		if(repository.findById(aemailid).isPresent()) {
		admin= repository.findById(aemailid).get();
		if(admin.getApass().equals(apass)) 
			s="Logged in successfully";
		else
			s="Invalid password";
	}else
		//s="invalid";
		throw new UserNotFoundException(aemailid);
		return s;
	}



}
