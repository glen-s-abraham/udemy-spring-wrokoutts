package com.glen.BlogPostSpringBoot.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glen.BlogPostSpringBoot.entity.Role;
import com.glen.BlogPostSpringBoot.entity.User;
import com.glen.BlogPostSpringBoot.payload.JwtAuthResponse;
import com.glen.BlogPostSpringBoot.payload.LoginDTO;
import com.glen.BlogPostSpringBoot.payload.SignUpDTO;
import com.glen.BlogPostSpringBoot.repository.RoleRespository;
import com.glen.BlogPostSpringBoot.repository.UserRepository;
import com.glen.BlogPostSpringBoot.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRespository roleRespository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDTO login){
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					login.getNameOrEmail(),login.getPassword()
		));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		return new ResponseEntity<>(new JwtAuthResponse(token),HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?>registerUser(@RequestBody SignUpDTO signup){
		if(userRepository.existsByEmail(signup.getEmail()))
			return new ResponseEntity<>("Email Already exists",HttpStatus.BAD_REQUEST);
		User user = new User();
		user.setName(signup.getName());
		user.setEmail(signup.getEmail());
		user.setPassword(passwordEncoder.encode(signup.getPassword()));
		
		Role roles= roleRespository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));
		userRepository.save(user);
		return new ResponseEntity<>("Successfully registered",HttpStatus.OK);
		
	}

}
