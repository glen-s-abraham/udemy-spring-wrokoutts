package com.glen.BlogPostSpringBoot.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,length = 100)
	private String title;
	@Column(nullable = false,length = 1000)
	private String body;
	
	@OneToMany(mappedBy="post",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Comment>comments=new HashSet<>();
	
	public Post() {
		
	}
	public Post(Long id, String title, String body) {
		
		this.id = id;
		this.title = title;
		this.body = body;
	}
	public Post( String title, String body) {
		
		
		this.title = title;
		this.body = body;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	

}
