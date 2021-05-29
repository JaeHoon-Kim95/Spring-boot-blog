package com.hoon.blog.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.hoon.blog.config.auth.PrincipalDetail;
import com.hoon.blog.dto.ReplySaveRequestDto;
import com.hoon.blog.model.Board;
import com.hoon.blog.model.Reply;
import com.hoon.blog.model.RoleType;
import com.hoon.blog.model.User;
import com.hoon.blog.repository.BoardRepository;
import com.hoon.blog.repository.ReplyRepository;
import com.hoon.blog.repository.UserRepository;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void doPost(Board board, User user) { //예외 발생시 handler/GlobalExceptionHandler return값 출력
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> doSelectList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board doDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패");
				});
	}
	
	@Transactional
	public void doDelete(int id, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");  
        });
		
		if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalStateException("글 삭제 실패 : 해당 글을 삭제할 권한이 없습니다.");
        }
        boardRepository.delete(board);
	}
	
	@Transactional
	public void doUpdate(int id, Board requestBoard, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");  
        }); //영속화 완료 DB 테이블에 있는 데이터와 동기화 완료
		
		if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalStateException("글 수정 실패 : 해당 글을 수정할 권한이 없습니다.");
        }
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//해당 service 종료 시 트랜잭션이 종료됨. 이때 더티체킹 - 자동 업데이트 됨(db flush)
	}
	
	@Transactional
	public void replyPost(ReplySaveRequestDto replySaveRequestDto) {						
		
		replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}
	
	@Transactional
	public void replyDelete(int replyId, PrincipalDetail principal) {
		Reply reply = replyRepository.findById(replyId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 찾기 실패 : 해당 댓글이 존재하지 않습니다.");  
        });
		
		if (reply.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalStateException("댓글 삭제 실패 : 댓글을 삭제할 권한이 없습니다.");
        }
        replyRepository.deleteById(replyId);
	}
}
