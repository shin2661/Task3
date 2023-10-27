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
	
	// 회원가입 시 ROLE_USER부여
	@Override
	public int insertAuth(AuthVO userAuth) {
		return mapper.insertAuth(userAuth);
	}

	// 회원정보 조회
	@Override
	public MemberVO getMember(String id) {
		System.out.println("getMember");
		return mapper.selectMember(id);
	}

	// 비밀번호 실패횟수 초기화
	@Override
	public void resetFailCount(MemberVO member) {
		mapper.updateFailCount(member);
	}

	// 회원정보 수정
	@Override
	public void modifyMember(MemberVO member) {
		mapper.updateMember(member);
	}

	// 사용자 비밀번호 확인
	@Override
	public String getPasswd(String id) {
		return mapper.selectPasswd(id);
	}

	// 비밀번호 변경
	@Override
	public int changePasswd(MemberVO member) {
		return mapper.updatePasswd(member);
	}


}
