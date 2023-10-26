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
			<form action="joinPro.do" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="mb-3">
					<label for="inputName">이름</label>
					<input class="form-control" id="inputName" type="text" name="name" placeholder="이름">
				</div>
				<div class="mb-3">
					<label for="inputId">아이디</label>
					<input class="form-control" id="inputId" type="text" name="id" placeholder="아이디">
				</div>
				<div class="mb-3">
					<label for="inputPassword1">비밀번호(숫자, 문자, 특수문자 포함 8 ~ 16자리)</label>
					<input class="form-control" id="inputPassword1" type="password" name="passwd" minlength="8" maxlength="16" onkeyup="checkPasswd1(this.value);" placeholder="숫자, 문자, 특수문자 포함 8 ~ 16자리">
					<span id="passwdCheckArea1"></span>
				</div>
				<div class="mb-3">
					<label for="inputPassword2">비밀번호확인</label>
					<input class="form-control" id="inputPassword2" type="password" minlength="8" maxlength="16" onkeyup="checkPasswd2(this.value);" placeholder="숫자, 문자, 특수문자 포함 8 ~ 16자리">
					<span id="passwdCheckArea2"></span>
				</div>
				<div class="text-center">
					<a class="btn btn-outline-warning" href="main.do">&nbsp;&nbsp;홈으로&nbsp;&nbsp;</a>
					<button class="btn btn-warning" type="submit" id="joinBtn" disabled>회원가입</button>
				</div>
			</form>
		</div>
	
		<div class="mt-auto text-white-50">
			<p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
		</div>
	</div>
	
	<script type="text/javascript">
	
		//[비밀번호확인] 버튼의 상태확인 및 변경을 수행하는 함수 ===================================================================
		function checkButtonStatus(){
			let inputPassword1 =  $("#inputPassword1").val();
			let inputPassword2 =  $("#inputPassword2").val();
			let joinButton = $("#joinBtn");
			let regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;
			
			if (regex.test(inputPassword1) && regex.test(inputPassword2) && inputPassword1 === inputPassword2) {
				$("#joinBtn").prop("disabled", false);
			} else {
				$("#joinBtn").prop("disabled", true);
			}
		}
		
		
		$(function(){		
			// 비밀번호가 입력될 경우 [회원가입] 버튼 활성화 ====================================================================
			$(function() {
				$("#inputPassword1, #inputPassword2").on("input", function() {
					checkButtonStatus();
				});
			});
		});
		
		// 비밀번호가 숫자, 문자, 특수문자 포함 8자리~16자리인지 확인하는 함수
		function checkPasswd1(passwd1) {
			let regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;
			
			if(regex.test(passwd1)){
				$("#passwdCheckArea1").html("사용 가능한 비밀번호입니다");
				$("#passwdCheckArea1").css("color", "#09aa5c");
				checkButtonStatus();
			}else {
				$("#passwdCheckArea1").html("사용 불가능한 비밀번호입니다");			
				$("#passwdCheckArea1").css("color", "red");
				checkButtonStatus();
			}
		}
		
		// 재입력한 비빌번호가 처음 입력한 비밀번호와 일치하는지 확인하는 함수
		function checkPasswd2(passwd2) {
			let regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;
			
			if(regex.test(passwd2) && passwd2 == $("#inputPassword1").val()){
				$("#passwdCheckArea2").html("비밀번호가 일치합니다");
				$("#passwdCheckArea2").css("color", "#09aa5c");
				checkButtonStatus();
			}else {
				$("#passwdCheckArea2").html("비밀번호가 일치하지 않습니다");			
				$("#passwdCheckArea2").css("color", "red");
				checkButtonStatus();
			}
		}
		
	</script>

</body>
</html>