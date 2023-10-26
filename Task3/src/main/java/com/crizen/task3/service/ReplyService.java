package com.crizen.task3.service;

import com.crizen.task3.vo.ReplyVO;

public interface ReplyService {

	// 댓글등록
	int registReply(ReplyVO reply);

	// 댓글삭제
	int removeReply(int seq_reply);

	// 댓글수정
	int modifyReply(ReplyVO reply);

}
