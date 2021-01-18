package com.userlogin.demo.service;

import com.userlogin.demo.model.Admin;


public interface IAdminService {
	public Admin saveAdmin(Admin admin);
	public Admin updateAdminPassword(String aemailid,Admin admin);
	public String getAdmin(String aemailid, String apass);
}
