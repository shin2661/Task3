<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>Insert title here</title>
<style type="text/css">
	.cover-container {
		max-width: 42em;
	}
</style>
</head>
<body class="d-flex h-100 text-bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<div class="mb-auto">
			<h1 class="float-md-start mb-0">CRIZEN SOLUTION</h1>
		</div>
	
		<div class="px-3">
			<form:form action="${pageContext.request.contextPath}/login" method="POST">
				<c:if test="${not empty error}">
					<font color="red">
						<p>Your login attempt was not successful due to <br/>
						${error }</p>
					</font>
				</c:if>
				<div class="mb-3">
					<label for="inputId">아이디</label>
					<input class="form-control" id="inputId" type="text" name="id" placeholder="아이디">
				</div>
				<div class="mb-3">
					<label for="inputPassword">비밀번호</label>
					<input class="form-control" id="inputPassword" type="password" name="passwd" placeholder="8자리 이상 비밀번호">
					<span id="passwdCheckArea">숫자, 문자, 특수문자 포함 8자리 이상</span>
				</div>
				<div class="d-flex align-items-center justify-content-between">
					<div>
						<a class="small text-white" href="joinForm.do">회원가입</a>
						<a class="small text-white" href="passwdChangeForm.do">비밀번호 변경</a>
					</div>
					<div>
						<input class="form-check-input" id="inputRememberPassword" name="remember-me" type="checkbox" value="true">
						<label class="form-check-label" for="inputRememberPassword">Remember me</label>
					</div>
				</div>
				<div class="text-center mt-4">
					<button class="btn btn-warning" type="submit">로그인</button>
				</div>
			</form:form>
		</div>
	
		<div class="mt-auto text-white-50">
			<p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
		</div>
	</div>
</body>
</html>