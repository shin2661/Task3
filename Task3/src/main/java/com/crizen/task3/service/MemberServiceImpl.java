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

	// 회원정보 조회
	@Override
	public MemberVO getMember(String id) {
		return mapper.selectMember(id);
	}

	// 비밀번호 실패횟수 초기화
	@Override
	public void resetFailCount(int fail_count) {
		mapper.updateFailCount(fail_count);
	}

	@Override
	public void modifyMember(MemberVO member) {
		mapper.updateMember(member);
	}

}
