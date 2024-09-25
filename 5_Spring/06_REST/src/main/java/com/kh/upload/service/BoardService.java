package com.kh.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;

import com.kh.upload.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	public void insert(Board vo) {
		mapper.insert(vo); // BoardMapper의 insert 메서드를 호출하여 DB에 저장
	}
	
	public List<Board> selectAll(Paging paging) { // 페이징 처리를 적용하여 게시글 목록을 조회하는 메서드
		
		/*
		 * limit가 10인 경우
		 * page = 1 -> offset = 0
		 * page = 2 -> offset = 10
		 * page = 3 -> offset = 20 ...
		 * 
		 * offset = limit * (page - 1)
		 * */
		paging.setOffset(paging.getLimit() * (paging.getPage() - 1)); // 현재 페이지와 한 페이지에 표시할 항목 수를 기준으로 offset을 계산
		
		return mapper.selectAll(paging);
	}
	
	public int total() {
		return mapper.total(); // total 메서드를 호출하여 게시글 총 개수를 반환
	}
	
	public Board select(int no) {
		return mapper.select(no); // select 메서드를 호출하여 게시글 번호(no)에 해당하는 게시글을 반환
	}
	
	public void update(Board vo) {
		mapper.update(vo); // BoardMapper의 update 메서드를 호출하여 DB에서 업데이트
	}
	
	public void delete(int no) {
		mapper.delete(no);
	}
}