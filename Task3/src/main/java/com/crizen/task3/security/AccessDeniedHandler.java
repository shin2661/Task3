package com.crizen.task3.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.security.access.*;
import org.springframework.stereotype.*;

import lombok.extern.slf4j.*;

@Slf4j
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDenied) throws IOException, ServletException {
//		res.sendRedirect("/accessDenied.do");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/member/access_denied.jsp");
		dispatcher.forward(req, res);
	}

}
