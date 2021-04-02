package com.hoon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoon.blog.model.User;

//자동으로 bean등록 됨
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
