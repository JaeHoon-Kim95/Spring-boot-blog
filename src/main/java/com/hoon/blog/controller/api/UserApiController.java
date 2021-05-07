package com.hoon.blog.controller.api;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoon.blog.dto.ResponseDto;
import com.hoon.blog.model.User;
import com.hoon.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;  
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {

		userService.save(user);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
		// 여기서 트랜잭션이 종료되기 때문에 DB값 변경
		// 하지만 세션값은 변경되지 않은 상태
		
		//세션등록
		// 로그인 요청 -> 인증 필터 -> 유저패스인증토큰 -> AuthenticationManager는 Authentication객체 만듦, 만들기 위해서는 유저패스인증토큰 필요
		// Authentication 객체 만들기 전에 DB에 존재하는지 확인하기 위해 UserDetailService에 존재여부 확인 요청
		// 존재하면 Authentication 만들고 SecurityContext안에 넣음
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}
