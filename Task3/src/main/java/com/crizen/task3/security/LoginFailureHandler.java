package com.crizen.task3.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException accessException) throws IOException, ServletException {
		
		log.error("LoginFailureHandler");
		log.error("Redirect....");
		
		if (accessException instanceof AuthenticationServiceException) {
			req.setAttribute("error", "존재하지 않는 사용자입니다.");
		
		} else if(accessException instanceof BadCredentialsException) {
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
