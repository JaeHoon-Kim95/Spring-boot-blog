package com.hoon.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//클라이언트 -> 응답(HTML파일)
//@Controller

//클라이언트 -> 응답(Data)
//@RestController

@RestController
public class HttpControllerTest {
	
	@GetMapping("/http/get")
	public String getTest(@RequestParam int id,@RequestParam String username) {
		return "get 요청:"+id+","+username;
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청"+m.getEmail()+","+m.getUsername();
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
