package com.crizen.task3.service;

import com.crizen.task3.vo.*;

public interface SlangService {

	// 비속어중복확인
	boolean isExistSlang(String slang);

	// 비속어등록
	int registSlang(SlangVO slang);


}
