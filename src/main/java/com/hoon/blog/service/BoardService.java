package com.hoon.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoon.blog.model.Board;
import com.hoon.blog.model.RoleType;
import com.hoon.blog.model.User;
import com.hoon.blog.repository.BoardRepository;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void save(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	public Page<Board> SelectList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

}
