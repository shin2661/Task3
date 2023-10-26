package com.crizen.task3.mapper;

import org.apache.ibatis.annotations.*;

import com.crizen.task3.vo.*;

@Mapper
public interface MemberMapper {

	MemberVO read(String userId);
	
	// 회원가입
	int insertMember(MemberVO member);

	// 회원정보 조회
	MemberVO selectMember(String id);

	// 비밀번호 실패횟수 초기화
	void updateFailCount(int fail_count);

	// 회원정보 수정
	void updateMember(MemberVO member);

}
