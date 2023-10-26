<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="navbar navbar-expand-lg navbar-light">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="main.do">Home</a>
			<button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
		<div class="navbar-collapse collapse" id="navbarSupportedContent" style="">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="nav-item"><a class="nav-link" href="counselWriteForm.do">글쓰기</a></li>
				<li class="nav-item"><a class="nav-link" href="counselList.do">글목록</a></li>
				<li class="nav-item"><a class="nav-link" href="slangRegistForm.do">비속어등록</a></li>
			</ul>
			<div class="d-flex">
				<sec:authorize access="isAnonymous()">
					<a href="<c:url value="/loginForm.do" />" class="nav-link link-body-emphasis px-2">로그인</a>
					<a href="<c:url value="/joinForm.do" />" class="nav-link link-body-emphasis px-2">회원가입</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<form:form action="${pageContext.request.contextPath}/logout" id="form" method="POST">
						<a href="javascript:void(0);" onclick="getElementById('form').submit();" class="nav-link link-body-emphasis px-2">로그아웃</a>
					</form:form>
				</sec:authorize>
			</div>
		</div>
	</div>
</nav>
