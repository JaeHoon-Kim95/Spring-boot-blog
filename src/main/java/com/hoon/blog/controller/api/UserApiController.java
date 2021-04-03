package com.hoon.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoon.blog.dto.ResponseDto;
import com.hoon.blog.model.RoleType;
import com.hoon.blog.model.User;
import com.hoon.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	

	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {

		userService.save(user);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}
