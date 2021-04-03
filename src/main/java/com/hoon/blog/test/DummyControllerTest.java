package com.hoon.blog.test;

import java.util.List;
import java.util.function.Supplier;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoon.blog.model.RoleType;
import com.hoon.blog.model.User;
import com.hoon.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getRole());
		System.out.println(user.getEmail());
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		return "회원 가입 완료"; 
	}
	
	//{id} 주소로 파라미터 전달 받음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//없는 user를 찾을 경우 null이 return 되니 
		//null일 경우 파라미터값의 유저를 리턴
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("해당 유저는 없습니다.id :"+id);
			}
		});
		
		// Spring Boot = MessageConverter 얘가 응답시 자동작동
		// 만약 java object를 return하게 되면 MessageConverter가 Jackson 라이브러리 호출해서 json으로 변환해줌
		return user;
	}
	
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건 데이터 리턴 받아보기
	@GetMapping("/dummy/userP")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//영속성 컨텍스트
	// 메서드 종료시 트랜잭션이 변경감지하여 DB에 flush해준다(더티 체킹)
	@Transactional //함수 종료시 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) { //JSON 데이터 요청 => MessageConverter의 Jackson 라이브러리가 변환해서 받아줌
		System.out.println("id :"+id);
		System.out.println("password :"+requestUser.getPassword());
		System.out.println("email :"+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
	
		//userRepository.save(user);
		//save => id를 전달하지 않으면 insert, 있으면 update,id에 대한 데이터가 없으면 insert
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다.";
		}
		
		return "삭제 되었습니다:"+id;
	}
	
}

