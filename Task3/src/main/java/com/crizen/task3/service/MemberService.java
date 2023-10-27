package com.crizen.task3.service;

import com.crizen.task3.vo.*;

public interface MemberService {

	// 회원가입
	int joinMember(MemberVO member);

	// 회원가입 시 ROLE_USER부여
	int insertAuth(AuthVO userAuth);

	// 회원정보 조회
	MemberVO getMember(String id);

	// 비밀버호 실패 횟수 초기화
	void resetFailCount(MemberVO member);

	// 비밀번호 실패로 계정 잠금
	void modifyMember(MemberVO member);

	// 사용자 비밀번호 확인
	String getPasswd(String id);

	// 비밀번호 변경
	int changePasswd(MemberVO member);




}
