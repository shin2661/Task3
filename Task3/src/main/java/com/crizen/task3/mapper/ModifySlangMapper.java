package com.crizen.task3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.ReplyVO;
import com.crizen.task3.vo.SlangVO;

@Mapper
public interface ModifySlangMapper {

	// 비속어 리스트 조회
	List<SlangVO> selectSlangList();
	
	// 게시글 리스트 조회
	List<CounselVO> selectCounselList();

	// 답글 리스트 조회
	List<ReplyVO> selectReplyList();

	// 게시글 비속어 마스킹
	void updateCounselSlang(CounselVO counsel);

	// 답글 비속어 마스킹
	void updateReplySlang(ReplyVO reply);







}
