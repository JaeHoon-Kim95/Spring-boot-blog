package com.hoon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.hoon.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
	//댓글 작성 네이티브 쿼리 작성
	@Modifying // int만 리턴 가능
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())", nativeQuery = true)
	int mSave(int userId,  int boardId, String content); //업데이트 된 행의 개수 리턴
}
