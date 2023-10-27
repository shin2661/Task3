package com.crizen.task3.mapper;

import org.apache.ibatis.annotations.*;

import com.crizen.task3.vo.*;

@Mapper
public interface MemberMapper {

	MemberVO read(String userId);
	
	// 회원가입
	int insertMember(MemberVO member);
	
	// 회원가입 시 ROLE_USER부여
	int insertAuth(AuthVO userAuth);

	// 회원정보 조회
	MemberVO selectMember(String id);

	// 비밀번호 실패횟수 초기화
	void updateFailCount(MemberVO member);

	// 계정 잠금
	void updateLocked(MemberVO member);

	// 회원정보 수정
	void updateMember(MemberVO member);

	// 사용자 비밀번호 확인
	String selectPasswd(String id);

	// 비밀번호 변경
	int updatePasswd(MemberVO member);




}
