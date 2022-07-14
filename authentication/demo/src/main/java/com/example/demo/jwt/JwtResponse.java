package com.example.demo.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private  String  role;
	private int userId;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public JwtResponse(String jwttoken, String role) {
		this.jwttoken = jwttoken;
		this.role = role;
	}

	public JwtResponse(String jwttoken, String role, int userId) {
		this.jwttoken = jwttoken;
		this.role = role;
		this.userId = userId;
	}

	public String getToken() {
		return this.jwttoken;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}