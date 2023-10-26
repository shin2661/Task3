package com.crizen.task3.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.crizen.task3.mapper.*;
import com.crizen.task3.vo.*;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;

	// 회원가입
	@Override
	public int joinMember(MemberVO member) {
		return mapper.insertMember(member);
	}

}
