package com.glen.BlogPostSpringBoot.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassWordEncoderGenerator {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		System.out.println(passwordEncoder.encode("password"));
	}
}
