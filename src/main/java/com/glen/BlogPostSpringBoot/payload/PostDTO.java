package com.glen.BlogPostSpringBoot.payload;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.glen.BlogPostSpringBoot.entity.Comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class PostDTO {
	private Long id;
	
	@NotEmpty
	@Size(min = 2, message = "title should be of atleast 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min=10,message ="body of the post should be of atleast 10 characters")
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
