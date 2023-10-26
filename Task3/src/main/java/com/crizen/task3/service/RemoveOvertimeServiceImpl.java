package com.crizen.task3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.*;
import org.springframework.stereotype.Service;

import com.crizen.task3.mapper.RemoveOvertimeMapper;
import com.crizen.task3.vo.CounselVO;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("removeOvertimeService")
public class RemoveOvertimeServiceImpl implements RemoveOvertimeService {

	@Autowired
	private RemoveOvertimeMapper mapper;
	
	@Override
	public void removeOvertime() {
		List<CounselVO> removeList = mapper.selectRemoveList();
		
		System.out.println(removeList);
		
		for(CounselVO counsel : removeList) {
			mapper.removeOvertime(counsel.getSeq_counsel());
		}
		
		log.info("=============== 게시물 삭제 ===============");
		log.info("게시물 : " + removeList.size() + "개 삭제 완료");
		log.info("===========================================");
		
	}
	
	

}
