package com.glen.BlogPostSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glen.BlogPostSpringBoot.payload.CommentDTO;
import com.glen.BlogPostSpringBoot.service.CommentService;



@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
	
	CommentService commentService;
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	

	@PostMapping
	public ResponseEntity<CommentDTO> createPost(
			@PathVariable("postId")Long postId,
			@RequestBody CommentDTO commentDto
	)
	{
		return new ResponseEntity<>(
				commentService.createComment(postId, commentDto),
				HttpStatus.OK
		);
	}
	@GetMapping
	public List<CommentDTO> getCommentsOnPost(
			@PathVariable("postId")Long postId
	){
		return commentService.getAllComments(postId);
	}
	
	@GetMapping("/{commentId}")
	public CommentDTO getCommentOnPostById(
			@PathVariable("postId")Long postId,
			@PathVariable("commentId")Long commentId
	){
		return commentService.getCommentById(postId,commentId);
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<CommentDTO> updateCommentOnPostById(
			@PathVariable("postId")Long postId,
			@PathVariable("commentId")Long commentId,
			@RequestBody CommentDTO commentDto
	){
		return new ResponseEntity<>(
				commentService.updateCommentById(postId,commentId,commentDto),
				HttpStatus.OK
		);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteCommentOnPostById(
			@PathVariable("postId")Long postId,
			@PathVariable("commentId")Long commentId
	){
		commentService.deleteCommentById(postId,commentId);
		return new ResponseEntity<>(
				"Comment Deleted",
				HttpStatus.OK
		);
	}
	
	
}
