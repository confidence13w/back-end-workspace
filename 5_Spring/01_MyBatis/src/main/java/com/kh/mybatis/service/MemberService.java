package com.kh.mybatis.service;

import org.springframework.stereotype.Service;

import com.kh.mybatis.model.vo.Member;

import mapper.MemberMapper;

@Service
public class MemberService {

	private MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public void register(Member member) {
		memberMapper.register(member);
	}
	
}
