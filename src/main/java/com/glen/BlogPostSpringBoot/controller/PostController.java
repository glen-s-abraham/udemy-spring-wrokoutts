package com.glen.BlogPostSpringBoot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glen.BlogPostSpringBoot.payload.PostDTO;
import com.glen.BlogPostSpringBoot.payload.PostResponse;
import com.glen.BlogPostSpringBoot.service.PostService;
import com.glen.BlogPostSpringBoot.utils.AppConstants;



@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO post){
		return new ResponseEntity<>(postService.createPost(post),HttpStatus.CREATED);
	}
	
	@GetMapping
	public PostResponse  getAllPosts(
			@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER)Integer pageNo,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_FIELD,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir
	){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{id}")
	public PostDTO getPostById(@PathVariable Long id){
		return postService.getPostById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDTO> updatePostById(
			@PathVariable Long id,
			@Valid @RequestBody PostDTO postDto
	){
		return new ResponseEntity<>(postService.updatePostById(id,postDto),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePostById(@PathVariable Long id){
		postService.deletePostById(id);
		return new ResponseEntity<>("Post Deleted",HttpStatus.OK);
				
	}

}
