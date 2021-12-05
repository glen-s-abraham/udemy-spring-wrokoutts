package com.glen.BlogPostSpringBoot.payload;

import java.util.Set;

import com.glen.BlogPostSpringBoot.entity.Comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class PostDTO {
	private Long id;
	private String title;
	private String body;
	private Set<Comment> comments;
	
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public PostDTO() {
		
	}
	public PostDTO(Long id,String title, String body) {
		this.id =id;
		this.title = title;
		this.body = body;
	}
	
	public PostDTO(Long id, String title, String body, Set<Comment> comments) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.comments = comments;
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
	
}
