package com.hoon.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hoon.blog.model.Board;
import com.hoon.blog.model.User;

//자동으로 bean등록 됨
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
}
