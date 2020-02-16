package com.seucre.gateway.HttpSessionGateway;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", role=" + role + "]";
	}
	public UserInfo(String username, String role) {
		
		this.username = username;
		this.role = role;
	}
	
	private String username;
	private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
