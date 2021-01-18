package com.userlogin.demo.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.demo.exceptionhandler.UserNotFoundException;
import com.userlogin.demo.model.User;
import com.userlogin.demo.service.IUserService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired(required=true)
	public IUserService userservice; 
	
	@GetMapping(value= "/login/{uemailid}/{upass}")
	public ResponseEntity<String> getUser(@Valid @PathVariable String uemailid, @PathVariable String upass)throws UserNotFoundException{

			String message=userservice.getUser(uemailid, upass);
	
		return new ResponseEntity<>(message,HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> saveUser(@Valid @RequestBody User newUser) {
		User user=userservice.saveUser(newUser);
		String uemailid=user.getUemailid();
		return new ResponseEntity<String>("User With ID :"+uemailid+" registered Successfully ",HttpStatus.OK);
	}
	
	
	
	@PatchMapping(value = "/resetpassword/{uemailid}")

	public ResponseEntity<User> updateUserPassword(@Valid @PathVariable String uemailid, @RequestBody User newuser)
	{

		return new ResponseEntity<>(userservice.updateUserPassword(uemailid, newuser),HttpStatus.OK);
		
	}

}
