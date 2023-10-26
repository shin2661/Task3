package com.crizen.task3.security;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.stereotype.*;

import com.crizen.task3.service.MemberService;
import com.crizen.task3.vo.MemberVO;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private MemberService service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
		// IP, 세션 ID
		WebAuthenticationDetails web = (WebAuthenticationDetails) auth.getDetails();
		System.out.println("IP: " + web.getRemoteAddress());
		System.out.println("Session ID: " + web.getSessionId());
		
		// 인증 ID
		System.out.println("ID: " + auth.getName());
		
		// 권한 리스트
		List<GrantedAuthority> authList = (List<GrantedAuthority>) auth.getAuthorities();
		System.out.println("권한: ");
		for(int i=0; i<authList.size(); i++){
			System.out.println(authList.get(i).getAuthority() + " ");
		}
		
		System.out.println("-------------------------------------------------------------------");
		log.info(auth.getName());
		log.info(auth.getAuthorities().toString());
		log.info(auth.getDetails().toString());
		log.info(auth.getPrincipal().toString());
		for(GrantedAuthority a : auth.getAuthorities()){
			log.info(a.getAuthority());
		}
		   
		UserDetails u = (UserDetails) auth.getPrincipal();
		 
		log.info(String.valueOf(u.isAccountNonExpired()));
		log.info(String.valueOf(u.isAccountNonLocked()));
		log.info(String.valueOf(u.isCredentialsNonExpired()));
		log.info(String.valueOf(u.isEnabled()));
		   
//		res.sendRedirect(req.getContextPath()+"/");
		System.out.println("-------------------------------------------------------------------");
		
		// 로그인 5회 실패 전 로그인 성공할경우 실패 횟수 리셋을 위해 엔티티 조회
		MemberVO member = service.getMember(auth.getName()); // 사용자의 ID를 기반으로 멤버 정보를 가져옴
		if (member != null && member.getFail_count() < 5) {
		    member.setFail_count(0); // 실패 횟수 리셋
		    service.resetFailCount(member.getFail_count()); // 멤버 정보 업데이트
		}

		
		
		// Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
		RequestCache requestCache = new HttpSessionRequestCache();
		RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
		SavedRequest savedRequest = requestCache.getRequest(req, res);
		
		// 1. 인증권한이 필요한 페이지에 접근했을 때 => 이전페이지로 이동
		if(savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(req, res, targetUrl);
			
        // 2. 직접 로그인 페이지로 접근했을 때 => default-target-url로 이동
		} else {
			redirectStratgy.sendRedirect(req, res, "/main.do");
		}
	}

}
