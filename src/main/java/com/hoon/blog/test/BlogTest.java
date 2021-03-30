package com.hoon.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogTest {

	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello HOON</h1>";
	}
}
