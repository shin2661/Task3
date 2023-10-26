package com.crizen.task3.service;

import com.crizen.task3.vo.*;

public interface MemberService {

	// 회원가입
	int joinMember(MemberVO member);

	// 회원정보 조회
	MemberVO getMember(String id);

	// 비밀버호 실패 횟수 초기화
	void resetFailCount(int fail_count);

	// 비밀번호 실패로 계정 잠금
	void modifyMember(MemberVO member);

}
