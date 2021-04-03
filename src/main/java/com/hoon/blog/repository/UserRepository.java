package com.hoon.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hoon.blog.model.User;

//자동으로 bean등록 됨
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//SELECT * FROM USER WHERE USERNAME=?
	Optional<User> findByUsername(String username);
}


//JPA Naming전략
	//Select * from user where username = ? AND password = ?
//	User findByUserNameAndPassWord(String userName,String passWord);
	
//	@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);