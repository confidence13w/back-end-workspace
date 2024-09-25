package com.kh.upload.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.upload.model.dto.BoardDTO;
import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;
import com.kh.upload.service.BoardService;

/*
 * REST(Representational State Transfer)
 * : API를 설계하는 아키텍쳐
 * 
 * RESTful : REST 원칙을 준수하는 방식
 * 
 * API(Application Programming Interface)
 * : 서로 다른 애플리케이션들이 서로 데이터를 주고받을 수 있게 하는 도구
 * */

@RestController
@RequestMapping("/api/*")
public class BoardController {

	private String path = "\\\\192.168.10.51\\upload\\";

	@Autowired
	private BoardService service;

	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		// 중복 방지를 위한 UUID 적용
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString() + "_" + file.getOriginalFilename();

		File copyFile = new File(path + fileName);
		file.transferTo(copyFile); // 업로드한 파일이 지정한 path 위치로 저장
		return fileName;
	}

	// CRUD : Create - Post, Read - Get, Update - Put, Delete - Delete
	
	// Create - Post
	@PostMapping("/board")
	public ResponseEntity write(Board vo) throws IllegalStateException, IOException {
		// 1. 파일 업로드 처리
		String url = fileUpload(vo.getFile()); // 파일 업로드 메서드 호출
		vo.setUrl(url); // 파일 URL을 Board 객체에 설정

		// 2. 해당 파일 URL과 함께 title, content DB에 저장
		service.insert(vo); // BoardService의 insert 메서드를 호출하여 DB에 저장

		return ResponseEntity.status(HttpStatus.OK).build(); // 작성한 글 페이지로 리다이렉트
	}

	// Read - Get : 전체 목록 보기
	@GetMapping("/board")
	public ResponseEntity list(Paging paging) {

		List<Board> list = service.selectAll(paging);

		for (Board b : list) {
			LocalDateTime date = b.getDate();
			Date formatDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
			b.setFormatDate(formatDate);
		}

		return ResponseEntity.status(HttpStatus.OK).body(new BoardDTO(list, new Paging(paging.getPage(), service.total())));
	}

	// Read - Get : 1개 가져오기
	@GetMapping("/board/{no}")
	public ResponseEntity view(@PathVariable int no) {
		Board board = service.select(no);
		if(board!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(board);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	//Update - Put
	
	@PutMapping("/board")
	public ResponseEntity update(Board vo) throws IllegalStateException, IOException {

		if (!vo.getFile().isEmpty()) {
			// 파일이 비어있지 않다면 기존 이미지 삭제(delete)
			if(vo.getUrl()!=null) { // 기존 이미지가 null이 아닌 경우
				File file = new File(path + vo.getUrl());
				file.delete(); // 기존 파일 삭제
			}
			
			// 새 이미지 등록
			String url = fileUpload(vo.getFile()); // 새 파일 업로드
			vo.setUrl(url); // 새 파일 URL을 Board 객체에 설정
		}

		service.update(vo); // BoardService의 update 메서드를 호출하여 DB에서 업데이트

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/board/{no}")
	public ResponseEntity delete(@PathVariable int no) {
		
		// 업로드한 파일 삭제 (필요한 건 URL)
		Board board = service.select(no); // 글 정보를 가져옴
		if(board.getUrl()!=null) {
			File file = new File(path + board.getUrl());
			file.delete(); // 파일 삭제
		}
		service.delete(no); // BoardService의 delete 메서드를 호출하여 DB에서 삭제
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}