package com.hoon.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hoon.blog.model.Board;
import com.hoon.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService; 

	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=3, sort="id",  direction=Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.doSelectList(pageable);

		int pageNumber = boards.getPageable().getPageNumber(); //현재 페이지
		int totalPages=boards.getTotalPages();
		int pageBlock = 5; // 1 2 3 4 5 
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; 
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
		
		model.addAttribute("startBlockPage",startBlockPage);
		model.addAttribute("endBlockPage",endBlockPage);
		model.addAttribute("boards",boards);
		return "index";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.doDetail(id));
		return "board/detail";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
