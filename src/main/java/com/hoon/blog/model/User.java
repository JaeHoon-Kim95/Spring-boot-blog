package com.hoon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //전체 생성자
@Builder
@Entity  //User 클래 MySQL에 테이블 생성
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략 따라감 MySQL = auto_increment
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String userName; //아이디
	
	@Column(nullable = false, length = 100)
	private String passWord; 
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; 
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
}
