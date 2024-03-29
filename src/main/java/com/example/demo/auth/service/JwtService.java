package com.example.demo.auth.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
	String getToken(String key, Object value);
	
	Claims getClaims(String token);
	
	boolean isValid(String token);
	
	int getId(String toekn);
}
