package com.userlogin.demo.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
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
import com.userlogin.demo.model.Admin;
import com.userlogin.demo.service.IAdminService;




@RestController
@Validated
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired(required=true)
	public IAdminService service; 
	
	@GetMapping(value= "/login/{aemailid}/{apass}")
	public ResponseEntity<String> getAdmin(@Valid @PathVariable String aemailid, @PathVariable String apass)throws UserNotFoundException{

			String message=service.getAdmin(aemailid, apass);
	
		return new ResponseEntity<>(message,HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> saveAdmin(@Valid @RequestBody Admin newAdmin) {
		Admin admin=service.saveAdmin(newAdmin);
		String aemailid=admin.getAemailid();
		return new ResponseEntity<String>("Admin With ID :"+aemailid+" registered Successfully ",HttpStatus.OK);
	}
	
	
	
	@PatchMapping(value = "/resetpassword/{aemailid}")

	public ResponseEntity<Admin> updateAdminPassword(@Valid @PathVariable String aemailid, @RequestBody Admin newadmin)
	{

		return new ResponseEntity<>(service.updateAdminPassword(aemailid, newadmin),HttpStatus.OK);
		
	}

}

