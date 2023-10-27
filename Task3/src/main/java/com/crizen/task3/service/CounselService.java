package com.crizen.task3.service;

import java.util.List;

import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.ReplyVO;

public interface CounselService {

	// 글등록
	int registCounsel(CounselVO counsel);

	// 글목록
	List<CounselVO> getCounselList();

	// 글상세보기
	CounselVO getCounsel(int seq_counsel);
	
	// 댓글목록
	List<ReplyVO> getReplyList(int seq_counsel);

	// 글삭제
	int removeCounsel(int seq_counsel);

	// 글수정
	int modifyCounsel(CounselVO counsel);

	// 검색기능 추가한 목록 조회
	List<CounselVO> getCounselListForSearch(String searchType, String searchKeyword, int startRow, int listLimit);

	// 목록개수 조회
	int getCounselListCount(String searchType, String searchKeyword);

	

	
}
