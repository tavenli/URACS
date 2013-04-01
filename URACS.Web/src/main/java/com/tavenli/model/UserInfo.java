package com.tavenli.model;

import java.util.ArrayList;
import java.util.List;


public class UserInfo {

	private int id;	
	private String userName;
	private List<Integer> roles = new ArrayList<Integer>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	
	
	
}
