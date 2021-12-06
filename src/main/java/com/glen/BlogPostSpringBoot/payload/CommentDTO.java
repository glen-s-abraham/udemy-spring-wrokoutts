package com.glen.BlogPostSpringBoot.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentDTO {
	
	private Long id;
	
	@NotBlank
	@Size(min = 2,message = "name should be atleast of 2 characters length")
	private String name;
	
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 1,message = "comment should be atleast of 1 character length")
	private String body;
	
	public CommentDTO() {
		
	}
	public CommentDTO(Long id, String name, String email, String body) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
