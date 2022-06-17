package com.example.demo.dao;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private  Collection<String>  role;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public JwtResponse(String jwttoken, Collection<String> role) {
		this.jwttoken = jwttoken;
		this.role = role;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public Collection<String> getRole() {
		return role;
	}

	public void setRole(Collection<String> role) {
		this.role = role;
	}
}