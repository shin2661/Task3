package com.crizen.task3.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.crizen.task3.service.MemberService;
import com.crizen.task3.vo.MemberVO;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	private MemberService service;

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException accessException) throws IOException, ServletException {
		
		log.error("LoginFailureHandler");
		log.error("Redirect....");
		
		if (accessException instanceof AuthenticationServiceException) {
			req.setAttribute("error", "존재하지 않는 사용자입니다.");
		
		} else if(accessException instanceof BadCredentialsException) {
			// 실패한 계정을 기반으로 멤버 정보 가져오기
	        String id = req.getParameter("id");
	        MemberVO member = service.getMember(id);

	        // 5회 이상 실패하면 계정을 잠그고 업데이트
	        if (member != null && member.getFail_count() >= 4) { // 여기서 4는 이미 실패한 상태이므로 총 5회를 의미합니다.
	            member.setFail_count(member.getFail_count() + 1); // 실패 횟수 증가
	            member.setLocked(1); // 계정 잠금 상태로 설정
	            service.modifyMember(member); // 멤버 정보 업데이트
	        }
			
			req.setAttribute("error", "아이디 또는 비밀번호가 틀립니다.");
			
		} else if(accessException instanceof LockedException) {
			req.setAttribute("error", "잠긴 계정입니다..");
			
		} else if(accessException instanceof DisabledException) {
			req.setAttribute("error", "비활성화된 계정입니다..");
			
		} else if(accessException instanceof AccountExpiredException) {
			req.setAttribute("error", "만료된 계정입니다..");
			
		} else if(accessException instanceof CredentialsExpiredException) {
			req.setAttribute("error", "비밀번호가 만료되었습니다.");
		}
		
		// 로그인 페이지로 다시 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/loginForm.do?error");
		dispatcher.forward(req, res);
	}

}
