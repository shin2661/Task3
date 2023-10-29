package com.crizen.task3.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.crizen.task3.mapper.*;
import com.crizen.task3.service.MemberService;
import com.crizen.task3.vo.MemberVO;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	private MemberMapper mapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException accessException) throws IOException, ServletException {
		
		log.error("LoginFailureHandler");
		log.error("Redirect....");
		
		if (accessException instanceof AuthenticationServiceException) {
			req.setAttribute("error", "존재하지 않는 사용자입니다.");
		
		} else if(accessException instanceof BadCredentialsException) {
			// 실패한 계정을 기반으로 멤버 정보 가져오기
	        String id = req.getParameter("id");
	        MemberVO member = mapper.selectMember(id);
	        
	        // 회원정보가 존재할 경우
	        if (member != null) {
		        // 5회 이상 실패하면 계정을 잠그고 업데이트
		        if (member.getFail_count() >= 4) {
		        	member.setFail_count(member.getFail_count() + 1);
		            member.setLocked(1); // 계정 잠금 상태로 설정
		            member.setIsEnabled(1); // 계정 비활성 상태로 설정
		            mapper.updateLocked(member); // 멤버 정보 업데이트
		            req.setAttribute("error", "비밀번호 5회 오류로 계정이 비활성화 되었습니다. 비밀번호를 변경해 주세요.");
		            
		        }else {
		        	// 실패횟수 증가
		        	member.setFail_count(member.getFail_count() + 1);
		        	mapper.updateFailCount(member);
		        	req.setAttribute("error", "비밀번호 " + member.getFail_count() + "회 오류. 5회 이상 오류 시 계정이 비활성화 됩니다.");
		        }
	        }else {
	        	req.setAttribute("error", "아이디 또는 비밀번호가 틀립니다.");
	        	
	        }
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
