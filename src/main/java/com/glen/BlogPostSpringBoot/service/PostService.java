package com.glen.BlogPostSpringBoot.service;

import java.util.List;

import com.glen.BlogPostSpringBoot.payload.PostDTO;
import com.glen.BlogPostSpringBoot.payload.PostResponse;

public interface PostService {
	PostDTO createPost(PostDTO post);
	PostResponse  getAllPosts(Integer pageNo,Integer pageSize,String sortBy,String sortDir);
	PostDTO getPostById(Long id);
	PostDTO updatePostById(Long id, PostDTO postDto);
	void deletePostById(Long id);
}
