package com.crizen.task3.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.crizen.task3.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	// 댓글등록
	int insertReply(ReplyVO reply);

	// 댓글삭제
	int deleteReply(int seq_reply);

	// 댓글수정
	int updateReply(ReplyVO reply);

}
