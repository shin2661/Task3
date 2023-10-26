package com.crizen.task3.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.encoding.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import com.crizen.task3.service.*;
import com.crizen.task3.vo.*;

import lombok.extern.slf4j.*;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	// 로그인 폼으로 이동
	@RequestMapping("loginForm.do")
	public String loginForm() {
		log.info("loginForm");
		return "member/login_form";
	}
	
	// 로그인 폼으로 이동
	@RequestMapping("accessDenied.do")
	public String accessDenied() {
		log.info("accessDenied");
		return "member/access_denied";
	}
	
	// 회원가입 폼으로 이동
	@RequestMapping("joinForm.do")
	public String jointForm() {
		log.info("joinForm");
		return "member/join_form";
	}
	
	// 회원가입 비즈니스 로직
	@RequestMapping("joinPro.do")
	public String joinPro(MemberVO member, Model model) {
		log.info("joinPro");
		System.out.println(member);
		
		// 비밀번호 암호화
		member.setPasswd(passwordEncoder.encode(member.getPasswd()));
		
		int insertCount = service.joinMember(member);
		if(insertCount == 0) {
			model.addAttribute("msg", "회원가입 실패");
			return "fail_back";
		}
		
		return "member/login_form";
	}
	
	
}
