package com.glen.BlogPostSpringBoot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.glen.BlogPostSpringBoot.entity.Comment;
import com.glen.BlogPostSpringBoot.entity.Post;
import com.glen.BlogPostSpringBoot.exception.BlogApiException;
import com.glen.BlogPostSpringBoot.exception.ResourceNotFoundException;
import com.glen.BlogPostSpringBoot.payload.CommentDTO;
import com.glen.BlogPostSpringBoot.repository.CommentRepository;
import com.glen.BlogPostSpringBoot.repository.PostRepository;
import com.glen.BlogPostSpringBoot.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public CommentServiceImpl(
			CommentRepository commentRepository,
			PostRepository postRepository,
			ModelMapper modelMapper
	) {
		this.commentRepository = commentRepository;
		this.postRepository=postRepository;
		this.modelMapper=modelMapper;
	}
	
	private CommentDTO mapEntityToDto(Comment entity) {
		return modelMapper.map(entity, CommentDTO.class);
		
	}
	
	private Comment mapDtoToEntity(CommentDTO dto) {
		return modelMapper.map(dto, Comment.class);
		
	}
	
	private Comment fetchPostandCommentAndCheckCommentBelongsToPost(Long postId,Long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", postId.toString()));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId.toString()));
		if(!comment.getPost().equals(post))
			throw new BlogApiException(HttpStatus.BAD_REQUEST,
					"Comment does not belong to post"
			);
		return comment;
	}

	
	@Override
	public CommentDTO createComment(Long postId, CommentDTO commentDto) {
		Comment comment=mapDtoToEntity(commentDto);
		Post post = postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", postId.toString()));
		comment.setPost(post);	
		return mapEntityToDto(commentRepository.save(comment));
	}

	@Override
	public List<CommentDTO> getAllComments(Long postId) {
		List<CommentDTO> commentDtos = commentRepository.findByPostId(postId)
				.stream()
				.map(comment->mapEntityToDto(comment))
				.collect(Collectors.toList());
		return commentDtos;
	}

	@Override
	public CommentDTO getCommentById(Long postId, Long commentId) {
		return mapEntityToDto(
				fetchPostandCommentAndCheckCommentBelongsToPost(postId, commentId)
		);
	}

	@Override
	public CommentDTO updateCommentById(Long postId,Long commentId,CommentDTO commentDto) {
		Comment comment=fetchPostandCommentAndCheckCommentBelongsToPost(postId, commentId);
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return mapEntityToDto(commentRepository.save(comment));
	}

	@Override
	public void deleteCommentById(Long postId, Long commentId) {
		Comment comment=fetchPostandCommentAndCheckCommentBelongsToPost(postId, commentId);
		commentRepository.delete(comment);
		
	}
	
	
}
