package com.crizen.task3.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crizen.task3.service.CounselService;
import com.crizen.task3.vo.CounselVO;
import com.crizen.task3.vo.PageInfoVO;
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("counselWriteForm.do")
	public String counselWriteForm(Model model) {
		log.info("counselWriteForm()");
		
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println(authentication);
		 System.out.println(authentication.isAuthenticated());

        // 사용자가 로그인한 경우에만 글쓰기 페이지로 이동
        if (authentication instanceof AnonymousAuthenticationToken) {
        	// 로그인하지 않은 사용자일 경우 다른 페이지로 리다이렉트 또는 에러 메시지 반환
        	model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
        	model.addAttribute("targetURL", "loginForm.do");
        	return "fail_forward"; // 로그인 페이지로 리다이렉트하는 예시
        } else {
        	return "counsel/counsel_write";
        }
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
	public String counselList(
			@RequestParam(defaultValue = "") String searchType, 
			@RequestParam(defaultValue = "") String searchKeyword, 
			@RequestParam(defaultValue = "") String searchDate, 
			@RequestParam(defaultValue = "1") int pageNum, 
			Model model) {
		log.info("counselList()");
		
		int listLimit = 5; // 한 페이지에서 표시할 목록 갯수 지정
		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행(레코드) 번호
		List<CounselVO> counselList = service.getCounselListForSearch(searchType, searchKeyword, searchDate, startRow, listLimit);
		
		int listCount = service.getCounselListCount(searchType, searchKeyword, searchDate);
		int pageListLimit = 3;
		int maxPage = listCount / listLimit + (listCount % listLimit > 0 ? 1 : 0);
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		int endPage = startPage + pageListLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfoVO pageInfo = new PageInfoVO(listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("counselList", counselList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "counsel/counsel_list";
	}

//	@ResponseBody
//	@RequestMapping("counselListJson.do")
//	public String counselListJson(
//			@RequestParam(defaultValue = "") String searchType, 
//			@RequestParam(defaultValue = "") String searchKeyword, 
//			@RequestParam(defaultValue = "") String searchDate, 
//			@RequestParam(defaultValue = "1") int pageNum, 
//			Model model) {
//		log.info("counselListJson()");
//		System.out.println(searchDate);
//		
//		int listLimit = 5; // 한 페이지에서 표시할 목록 갯수 지정
//		int startRow = (pageNum - 1) * listLimit; // 조회 시작 행(레코드) 번호
//		List<CounselVO> counselList = service.getCounselListForSearch(searchType, searchKeyword, searchDate, startRow, listLimit);
//		
//		int listCount = service.getCounselListCount(searchType, searchKeyword, searchDate);
//		int pageListLimit = 3;
//		int maxPage = listCount / listLimit + (listCount % listLimit > 0 ? 1 : 0);
//		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
//		int endPage = startPage + pageListLimit - 1;
//		if(endPage > maxPage) {
//			endPage = maxPage;
//		}
//		
//		PageInfoVO pageInfo = new PageInfoVO(listCount, pageListLimit, maxPage, startPage, endPage);
//		
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("counselList", counselList);
//		jsonObject.put("pageInfo", pageInfo);
//		jsonObject.put("startPage", startPage);
//		jsonObject.put("endPage", endPage);
//		jsonObject.put("maxPage", maxPage);
//		System.out.println(jsonObject.toString());
//		
//		return jsonObject.toString();
//	}

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
	public String counselDelete(@RequestParam int seq_counsel, Model model, Authentication auth) {
		log.info("counselDelete()");
		
		CounselVO counsel = service.getCounsel(seq_counsel);
		
		// 글 작성자와 로그인한 사용자를 비교합니다.
	    if (!counsel.getCounsel_writer().equals(auth.getName()) && !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
	        model.addAttribute("msg", "삭제 권한이 없습니다.");
	        return "fail_back";
	    }
		
		int deleteCount = service.removeCounsel(seq_counsel);
		
		if(deleteCount == 0) {
			model.addAttribute("msg", "삭제 실패");
			return "fail_back";
		}
		
		return "redirect:/counselList.do";
	}
	
	// 글수정 폼으로 이동
	@RequestMapping("/user/counselModifyForm.do")
	public String counselModifyForm(@RequestParam int seq_counsel, Model model) {
		log.info("counselModifyForm()");
		
		CounselVO counsel = service.getCounsel(seq_counsel);
		model.addAttribute("counsel", counsel);
		
		return "counsel/counsel_modify";
	}

	// 글수정
	@RequestMapping("/user/counselModifyPro.do")
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
