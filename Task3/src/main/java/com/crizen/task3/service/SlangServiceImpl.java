package com.crizen.task3.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.crizen.task3.mapper.*;
import com.crizen.task3.vo.*;

@Service("slangService")
public class SlangServiceImpl implements SlangService {
	
	@Autowired
	private SlangMapper mapper;

	// 비속어중복확인
	@Override
	public boolean isExistSlang(String slang) {
		
		boolean isExist = false;
		if(mapper.selectExistSlang(slang) > 0) {
			isExist = true;
		}
		return isExist;
	}

	// 비속어등록
	@Override
	public int registSlang(SlangVO slang) {
		return mapper.insertSlang(slang);
	}

	
	
}
