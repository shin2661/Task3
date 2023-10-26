package com.crizen.task3.mapper;

import org.apache.ibatis.annotations.*;

import com.crizen.task3.vo.*;

@Mapper
public interface MemberMapper {

	MemberVO read(String userId);
	
	// 회원가입
	int insertMember(MemberVO member);

}
