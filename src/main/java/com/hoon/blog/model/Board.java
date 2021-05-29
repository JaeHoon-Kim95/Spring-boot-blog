package com.hoon.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //전체 생성자
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인 됨
	
	private int count;
	
	//fetch = FetchType.EAGER : 값 하나 뿐이니 무조건 가져와라
	//User model을 참조하고 자동으로 FK가 만들어짐
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One 여러개의 게시글은 한명의 유저에 의해 쓰일 수 있다.
	@JoinColumn(name="userId") //userId라는 이름으로 테이블에 값이 들어감 
	private User user; //DB는 오브젝트 저장 x, 자바는 오브젝트 저장 가능
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy : 연관관계의 주인이 아니다. DB에 컬럼을 만들지 말아라 //cascade = CascadeType.REMOVE 게시글 삭제시 댓글 같이 지워짐
	@JsonIgnoreProperties({"board"}) //reply class의 board는 getter호출 무시
	@OrderBy("id desc")
	private List<Reply> replys;      //replys를 리턴하면 replys에서 board를 다시 리턴, board는 다시 replys를 리턴 무한참조됨
	
	@CreationTimestamp
	private Timestamp createDate;
}
