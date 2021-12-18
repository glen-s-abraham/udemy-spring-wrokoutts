package com.glen.BlogPostSpringBoot.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.glen.BlogPostSpringBoot.exception.BlogApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt.secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int expiryInMilliSecs;
	
	//Token generator function
	public String generateToken(Authentication auth) {
		String username= auth.getName();
		Date currentDate = new Date();
		Date expDate = new Date(currentDate.getTime()+expiryInMilliSecs);
		String token=Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
		return token;	
	}
	
	//Get username from the token
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser()
						.setSigningKey(jwtSecret)
						.parseClaimsJws(token)
						.getBody();
		return claims.getSubject();
	}
	
	//Vlidate token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		}catch (Exception e) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid token");
		}
		
		return true;
	}
}
