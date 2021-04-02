package com.hoon.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 exception 발생하면 이 클래스 참조
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handelArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
