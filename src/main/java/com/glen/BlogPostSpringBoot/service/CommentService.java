package com.glen.BlogPostSpringBoot.service;

import java.util.List;

import com.glen.BlogPostSpringBoot.payload.CommentDTO;

public interface CommentService {
	CommentDTO createComment(Long postId,CommentDTO commentDto);

	List<CommentDTO> getAllComments(Long postId);

	CommentDTO getCommentById(Long postId, Long commentId);

	CommentDTO updateCommentById(Long postId, Long commentId,CommentDTO commentDto);

	void deleteCommentById(Long postId, Long commentId);
}
