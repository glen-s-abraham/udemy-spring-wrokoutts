package com.glen.BlogPostSpringBoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String body;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id",nullable = false)
	@JsonIgnore
	private Post post;
	
	public Comment() {
		
		
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Comment(Long id, String name, String email, String body,Post post) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
		this.post=post;
	}
	public Comment(String name, String email, String body,Post post) {
		this.name = name;
		this.email = email;
		this.body = body;
		this.post=post;
	}
	public Comment(String name, String email, String body) {
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
