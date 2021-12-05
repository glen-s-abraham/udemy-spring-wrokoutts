package com.glen.BlogPostSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glen.BlogPostSpringBoot.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
}
