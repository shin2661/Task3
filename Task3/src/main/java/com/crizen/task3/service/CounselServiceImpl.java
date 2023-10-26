package com.crizen.task3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crizen.task3.mapper.CounselMapper;
import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.ReplyVO;

@Service("counselService")
public class CounselServiceImpl implements CounselService {
	
	@Autowired
	private CounselMapper mapper;

	// 글등록
	public int registCounsel(CounselVO counsel) {
		return mapper.insertCounsel(counsel);
	}

	// 글목록
	public List<CounselVO> getCounselList() {
		return mapper.selectCounselList();
	}

	// 글상세보기
	public CounselVO getCounsel(int seq_counsel) {
		return mapper.selectCounsel(seq_counsel);
	}
	
	// 댓글목록
	public List<ReplyVO> getReplyList(int seq_counsel) {
		return mapper.selectReplyList(seq_counsel);
	}
		

	// 글삭제
	public int removeCounsel(int seq_counsel) {
		return mapper.deleteCounsel(seq_counsel);
	}

	// 글수정
	public int modifyCounsel(CounselVO counsel) {
		return mapper.updateCounsel(counsel);
	}

	
	
	
}
