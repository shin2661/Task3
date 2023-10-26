package com.crizen.task3.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.crizen.task3.service.*;
import com.crizen.task3.vo.*;

import lombok.extern.slf4j.*;

@Slf4j
@Controller
public class SlangController {
	
	@Autowired
	private SlangService service;
	
	// 비속어 등록 폼으로 이동
	@RequestMapping("slangRegistForm.do")
	public String slangRegistForm() {
		log.info("slangRegistForm");
		
		return "slang_regist";
	}

	// 비속어 등록
	@RequestMapping("slangRegistPro.do")
	public String slangRegistPro(SlangVO slang, Model model) {
		log.info("slangRegistPro");
		
		boolean isExsit = service.isExistSlang(slang.getSlang());
		if(isExsit) {
			model.addAttribute("msg", "이미 등록된 비속어입니다.");
			return "fail_back";
		}
		
		int insertCount = service.registSlang(slang);
		
		if(insertCount == 0) {
			model.addAttribute("msg", "등록 실패");
			return "fail_back";
		}
		
		return "redirect:/counselList.do";
	}
}
