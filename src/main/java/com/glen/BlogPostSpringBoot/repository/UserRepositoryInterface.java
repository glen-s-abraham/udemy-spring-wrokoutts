package com.glen.BlogPostSpringBoot.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glen.BlogPostSpringBoot.entity.User;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	Optional<User> findByNameOrEmail(String name,String email);
	Optional<User> findByName(String name);
	Boolean existsByName(String name);
	Boolean existsByEmail(String email);
}
