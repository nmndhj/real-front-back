package com.example.demo.auth.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service("jwtService")
public class JwtServiceImpl implements JwtService {
	
	private String secretKey= "asldjf;alskdjflkjsdl;kfjalk@@sldkjfjklsdfjlksdjfo##j1oijs";
	
	@Override
	public String getToken(String key, Object value) {
		
		Date expTime = new Date();
		expTime.setTime(expTime.getTime() + 1000 * 60 * 30);  // 1000 : 1sec
		
		byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
		Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
		
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("typ", "JWT");
		headerMap.put("alg", "HS256");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		
		JwtBuilder builder = Jwts.builder().setHeader(headerMap)
											.setClaims(map)
											.setExpiration(expTime)
											.signWith(signKey, SignatureAlgorithm.HS256);
		
		return builder.compact();

	}

	@Override
	public Claims getClaims(String token) {

		if(token != null && !token.equals("")) {
			try {
				byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
				Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
				Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();

				return claims;
			}catch(ExpiredJwtException e) {
				//만료됨
			}catch(JwtException e){
				//유효하지 않음
			}
		}
		return null;
	}

	@Override
	public boolean isValid(String token) {

		return this.getClaims(token) != null;
	}

	@Override
	public int getId(String toekn) {
		
		Claims claims = this.getClaims(toekn);
		
		if(claims !=null) {
			return Integer.parseInt(claims.get("id").toString());
		}
		return 0;
	}

}
