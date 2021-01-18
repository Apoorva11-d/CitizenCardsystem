package com.userlogin.demo.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;






@Entity
@Table(name="userr")
public class User {
	
	@Id
	@Pattern(regexp="^[A-Za-z0-9+_.-]+@(.+)$", message="Invalid Id format")
	private String uemailid;
	@NotEmpty(message="Password should not be empty")
	private String upass;
	
	
	
	public User() {
		super();
	}
	
	public User(String uemailid, String upass) {
		super();
		this.uemailid = uemailid;
		this.upass = upass;
		
	}
	public String getUemailid() {
		return uemailid;
	}
	public void setUemailid(String uemailid) {
		this.uemailid = uemailid;
	}
	
	
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}


	
}
