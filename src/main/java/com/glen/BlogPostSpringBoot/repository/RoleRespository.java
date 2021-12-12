package com.glen.BlogPostSpringBoot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glen.BlogPostSpringBoot.entity.Role;

@Repository
public interface RoleRespository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
