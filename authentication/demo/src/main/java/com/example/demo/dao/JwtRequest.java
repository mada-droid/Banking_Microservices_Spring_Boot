package com.example.demo.dao;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;


//	private String username;
	private String password;
	private String email;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
	}

	public JwtRequest(String email, String password) {
//		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getUsername() {
//		return this.username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}