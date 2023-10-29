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
		// 회원가입
		int insertCount = service.joinMember(member);
		
		// ROLE_USER 역할 설정
		AuthVO userAuth = new AuthVO();
	    userAuth.setId(member.getId());
	    userAuth.setAuth("ROLE_USER");
	    int insertAuthCnt = service.insertAuth(userAuth);
		
		if(insertCount == 0) {
			model.addAttribute("msg", "회원가입 실패");
			return "fail_back";
		}else {
			model.addAttribute("msg", "회원가입 성공");
			model.addAttribute("targetURL", "loginForm.do");
			return "success_forward";
		}
	}
	
	// 비밀번호 변경 폼으로 이동
	@RequestMapping("passwdChangeForm.do")
	public String passwdChangeForm() {
		log.info("passwdChangeForm");
		
		return "member/pw_change";
	}
	
	// 사용자 아이디 확인
	@RequestMapping("idCheckPro.do")
	@ResponseBody
	public String idCheckPro(@RequestParam String id) {
		log.info("idCheck.do");
		
		MemberVO member = service.getMember(id);
		
		if(member != null) {
			return "true";
		}else {
			return "false";
		}
	}

	// 사용자 비밀번호 확인
	@RequestMapping("passwdCheckPro.do")
	@ResponseBody
	public String passwdCheckPro(@RequestParam String id, @RequestParam String passwd) {
		log.info("idCheck.do");
		
		String securePasswd = service.getPasswd(id);
		
		if (passwd ==  null || !passwordEncoder.matches(passwd, securePasswd)) {
			// 패스워드가 다를 때(비밀번호가 틀림)
			return "false";
		} else {
			return "true";
		}
	}

	// 사용자 비밀번호 및 fail_count 변경
	@RequestMapping("passwdChangePro.do")
	@ResponseBody
	public String passwdChangePro(@RequestParam String id, @RequestParam String passwd) {
		log.info("idCheck.do");
		
		MemberVO member = new MemberVO();
		member.setId(id);
		
		// 비밀번호 암호화
		member.setPasswd(passwordEncoder.encode(passwd));
		member.setFail_count(0);
		member.setLocked(0);
		member.setIsEnabled(0);
		int updateCount = service.changePasswd(member);
		
		if(updateCount > 0) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@RequestMapping("/secured/counselList.do")
	public String securedList() {
		log.info("securedList");
		
		return "counsel/counsel_list";
	}
	
	@RequestMapping("/admin/adminPage.do")
	public String adminPage() {
		log.info("adminPage");
		
		return "/member/admin_page";
	}
	
	
}
