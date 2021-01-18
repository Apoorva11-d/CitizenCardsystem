package com.userlogin.demo.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;






@Entity
@Table(name="admin")
public class Admin {
	@Id
	@Pattern(regexp="^[A-Za-z0-9+_.-]+@(.+)$", message="Invalid Id format")
	private String aemailid;
	@NotEmpty(message="Password should not be empty")
	private String apass;
	
	
	public Admin() {
		super();
	}
	public Admin(String aemailid, String apass){
		super();
		this.aemailid = aemailid;
		this.apass = apass;
	}
	public String getAemailid() {
		return aemailid;
	}
	public void setAemailid(String aemailid) {
		this.aemailid = aemailid;
	}
	public String getApass() {
		return apass;
	}
	public void setApass(String apass) {
		this.apass = apass;
	}
	

	

}
