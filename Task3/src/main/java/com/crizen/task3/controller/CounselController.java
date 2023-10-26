package com.crizen.task3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crizen.task3.service.CounselService;
import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.ReplyVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CounselController {
	
	@Autowired
	private CounselService service;
	
	@RequestMapping("main.do")
	public String main() {
		log.info("main()");
		
		return "redirect:/";
	}

	// 글등록 폼으로 이동
	@RequestMapping("counselWriteForm.do")
	public String counselWriteForm() {
		log.info("counselWrite()");
		
		return "counsel/counsel_write";
	}

	// 글등록
	@RequestMapping("counselWritePro.do")
	public String counselWritePro(CounselVO counsel, Model model) {
		log.info("counselWritePro()");
		System.out.println(counsel);
		
		int insertCount = service.registCounsel(counsel);
		
		if(insertCount == 0) {
			model.addAttribute("msg", "등록 실패");
			return "fail_back";
		}
	
		return "redirect:/counselList.do";
	}

	
	// 글목록
	@RequestMapping("counselList.do")
	public String counselList(Model model) {
		log.info("counselList()");
		
		List<CounselVO> counselList = service.getCounselList();
		
		model.addAttribute("counselList", counselList);
		
		return "counsel/counsel_list";
	}

	// 글상세보기
	@RequestMapping("counselDetail.do")
	public String counselDetail(@RequestParam int seq_counsel, Model model) {
		log.info("counselDetail()");
		
		CounselVO counsel = service.getCounsel(seq_counsel);
		List<ReplyVO> replyList = service.getReplyList(seq_counsel);
		model.addAttribute("counsel", counsel);
		model.addAttribute("replyList", replyList);
		
		System.out.println(replyList);
		
		return "counsel/counsel_detail";
	}

	// 글삭제
	@RequestMapping("counselDelete.do")
	public String counselDelete(@RequestParam int seq_counsel, Model model) {
		log.info("counselDelete()");
		
		int deleteCount = service.removeCounsel(seq_counsel);
		
		if(deleteCount == 0) {
			model.addAttribute("msg", "삭제 실패");
			return "fail_back";
		}
		
		return "redirect:/counselList.do";
	}
	
	// 글수정 폼으로 이동
	@RequestMapping("counselModifyForm.do")
	public String counselModifyForm(@RequestParam int seq_counsel, Model model) {
		log.info("counselModifyForm()");
		
		CounselVO counsel = service.getCounsel(seq_counsel);
		model.addAttribute("counsel", counsel);
		
		return "counsel/counsel_modify";
	}

	// 글수정
	@RequestMapping("counselModifyPro.do")
	public String counselModifyPro(CounselVO counsel, Model model) {
		log.info("counselModifyPro()");
		System.out.println(counsel);
		
		int updateCount = service.modifyCounsel(counsel);
		
		if(updateCount == 0) {
			model.addAttribute("msg", "수정 실패");
			return "fail_back";
		}
		
		return "redirect:/counselList.do";
	}
		
	
	
	
}
