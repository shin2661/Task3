package com.crizen.task3.controller;

import java.util.List;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.*;
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
//	@PreAuthorize("isAuthenticated()")
	@RequestMapping("counselWriteForm.do")
	public String counselWriteForm() {
		log.info("counselWrite()");
		
		return "counsel/counsel_write";
	}

	// 글등록
//	@PreAuthorize("isAuthenticated()")
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
	public String counselList(
			@RequestParam(defaultValue = "") String searchType, 
			@RequestParam(defaultValue = "") String searchKeyword, 
			@RequestParam(defaultValue = "1") int pageNum, 
			Model model, 
			HttpServletResponse response) {
		log.info("counselList()");
		
		// -------------------------------------------------------------------------
		// 페이징 처리를 위해 조회 목록 갯수 조절 시 사용될 변수 선언
		int listLimit = 3; // 한 페이지에서 표시할 목록 갯수 지정
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행(레코드) 번호
		// -------------------------------------------------------------------------
		// BoardService - getBoardList() 메서드 호출하여 게시물 목록 조회 요청
		// => 파라미터 : 검색타입, 검색어, 시작행번호, 목록갯수
		// => 리턴타입 : List<BoardVO>(boardList)
		List<CounselVO> counselList = service.getCounselListForSearch(searchType, searchKeyword, startRow, listLimit);
		
		// 페이징 처리를 위한 계산 작업
		// 한 페이지에서 표시할 페이지 목록(번호) 계산
		// 1. 전체 게시물 수 조회 요청(페이지 목록 계산에 활용)
		int listCount = service.getCounselListCount(searchType, searchKeyword);
		
		// 2. 한 페이지에서 표시할 목록 갯수 설정(페이지 번호의 갯수)
		int pageListLimit = 3;
		
		// 3. 전체 페이지 목록 갯수 계산
		int maxPage = listCount / listLimit + (listCount % listLimit > 0 ? 1 : 0);
		
		// 4. 시작 페이지 번호 계산
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		// 5. 끝 페이지 번호 계산
		int endPage = startPage + pageListLimit - 1;
		
		// 6. 만약, 끝 페이지 번호(endPage)가 전체(최대) 페이지 번호(maxPage) 보다
		//    클 경우 끝 페이지 번호를 최대 페이지 번호로 교체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
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
