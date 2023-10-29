package com.crizen.task3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.ReplyVO;

@Mapper
public interface CounselMapper {

	// 글등록
	int insertCounsel(CounselVO counsel);

	// 글목록
	List<CounselVO> selectCounselList();

	// 글상세보기
	CounselVO selectCounsel(int seq_counsel);
	
	// 댓글목록
	List<ReplyVO> selectReplyList(int seq_counsel);

	// 글삭제
	int deleteCounsel(int seq_counsel);

	// 글수정
	int updateCounsel(CounselVO counsel);

	// 검색기능 추가한 목록 조회
	List<CounselVO> selectCounselListForSearch(@Param("searchType") String searchType, 
												@Param("searchKeyword") String searchKeyword, 
												@Param("searchDate") String searchDate, 
												@Param("startRow") int startRow, 
												@Param("listLimit") int listLimit);

	// 
	int selectCounselListCount(@Param("searchType") String searchType, 
								@Param("searchKeyword") String searchKeyword, 
								@Param("searchDate") String searchDate);

	

}
