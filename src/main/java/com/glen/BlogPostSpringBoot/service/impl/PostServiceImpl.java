package com.glen.BlogPostSpringBoot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.glen.BlogPostSpringBoot.entity.Post;
import com.glen.BlogPostSpringBoot.exception.ResourceNotFoundException;
import com.glen.BlogPostSpringBoot.payload.PostDTO;
import com.glen.BlogPostSpringBoot.payload.PostResponse;
import com.glen.BlogPostSpringBoot.repository.PostRepository;
import com.glen.BlogPostSpringBoot.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	
	private PostRepository postRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper=modelMapper;
	}
	

	private PostDTO mapEntityToDto(Post entity) {
		//return new PostDTO(entity.getId(),entity.getTitle(),entity.getBody());
		return modelMapper.map(entity, PostDTO.class);
	}
	
	

	private Post mapDtoToEntity(PostDTO dto) {
		//return new Post(dto.getTitle(),dto.getBody());
		return modelMapper.map(dto, Post.class);
	}
	
	


	@Override
	public PostDTO createPost(PostDTO postDto) {
		Post newPost = postRepository.save(mapDtoToEntity(postDto));
		return mapEntityToDto(newPost);
	}

	@Override
	public PostResponse getAllPosts(
			Integer pageNo,Integer pageSize,String sortBy,String sortDir
	) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
				Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		Page<Post> post = postRepository.findAll(pageable);
		List<PostDTO> content = post
				.getContent().stream()
				.map(postEntity->mapEntityToDto(postEntity))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse(
				content, 
				post.getNumber(), 
				post.getSize(), 
				post.getTotalElements(), 
				post.getTotalPages(),
				post.isLast()
		);
		return postResponse;
	}

	@Override
	public PostDTO getPostById(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", id.toString()));
		return mapEntityToDto(post);
	}

	@Override
	public PostDTO updatePostById(Long id, PostDTO postDto) {
		Post post=postRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", id.toString()));
		post.setTitle(postDto.getTitle());
		post.setBody(postDto.getBody());
		postRepository.save(post);
		return mapEntityToDto(post);
	}

	@Override
	public void deletePostById(Long id) {
		Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id.toString()));
		postRepository.delete(post);
	}

	
	
	
	

}
