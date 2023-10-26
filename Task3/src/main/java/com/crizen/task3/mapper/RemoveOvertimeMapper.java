package com.crizen.task3.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.*;

import com.crizen.task3.vo.CounselVO;

@Mapper
public interface RemoveOvertimeMapper {

	// 삭제할 게시물 리스트
	List<CounselVO> selectRemoveList() throws DataAccessException;

	// 게시물삭제
	void removeOvertime(int seq_counsel) throws DataAccessException;

}
