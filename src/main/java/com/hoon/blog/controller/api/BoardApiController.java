package com.hoon.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoon.blog.config.auth.PrincipalDetail;
import com.hoon.blog.dto.ReplySaveRequestDto;
import com.hoon.blog.dto.ResponseDto;
import com.hoon.blog.model.Board;
import com.hoon.blog.model.Reply;
import com.hoon.blog.model.RoleType;
import com.hoon.blog.model.User;
import com.hoon.blog.service.BoardService;
import com.hoon.blog.service.UserService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.doPost(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> doDelete(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal){
		boardService.doDelete(id, principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> doUpdate(@PathVariable int id, @RequestBody Board board,
				@AuthenticationPrincipal PrincipalDetail principal){
		boardService.doUpdate(id,board,principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replyPost(@RequestBody ReplySaveRequestDto replySaveRequestDto) {

		boardService.replyPost(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId,
				@AuthenticationPrincipal PrincipalDetail principal){
		boardService.replyDelete(replyId, principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
