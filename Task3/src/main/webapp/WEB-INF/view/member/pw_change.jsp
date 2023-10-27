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
	
		<div class="px-3" id="checkArea">
			<div class="row g-3 mb-3">
				<div class="col-3">
					아이디 확인
				</div>
				<div class="col-7" id="inputIdArea">
					<input type="text" class="form-control" id="inputId" placeholder="사용자의 아이디를 입력해주세요">
				</div>
				<div class="col-2" id="idCheckBtnArea">
					<a href="javascript:idCheck()" class="btn btn-warning">확인</a>
				</div>
			</div>
			<div class="row g-3 mb-3">
				<div class="col-3">
					기존 비밀번호
				</div>
				<div class="col-7"  id="inputPasswdArea">
					<input type="password" class="form-control" id="inputExistPasswd" placeholder="기존 비밀번호를 입력해주세요">
				</div>
				<div class="col-2" id="passwdCheckBtnArea">
					<button onclick="passwdCheck()" class="btn btn-warning" id="passwdCheckBtn" disabled>확인</button>
				</div>
			</div>
			<div class="row g-3 mb-3">
				<div class="col-3">
					새 비밀번호
				</div>
				<div class="col-7"  id="inputNewPasswdArea">
					<input type="password" class="form-control" id="inputNewPasswd" placeholder="변경할 비밀번호를 입력해주세요">
				</div>
				<div class="col-2">
					<button onclick="passwdChange()" class="btn btn-warning" id="passwdChangekBtn" disabled>변경</button>
				</div>
			</div>
		</div>
	
		<div class="mt-auto text-white-50">
			<p>Cover template for <a href="https://getbootstrap.com/" class="text-white">Bootstrap</a>, by <a href="https://twitter.com/mdo" class="text-white">@mdo</a>.</p>
		</div>
	</div>
	
	<script type="text/javascript">
		
		// 아이디 확인
		function idCheck(){
			let id = $("#inputId").val();
			
			$.ajax({
				type : "GET", 
				url : "idCheckPro.do", 
				data : {id : id}, 
				dataType : "text", 
				success : function(result) {
					if(result == "true"){
						$("#inputIdArea").html(id);
						$("#passwdCheckBtn").prop("disabled", false);
						$("#idCheckBtnArea").remove();
						alert("아이디 확인이 완료되었습니다.");
						
					}else{
						alert("존재하지 않는 아이디입니다.");
					}
				}, 
				error : function() {
					alert("요청실패");
				}
			});
		}

		// 비밀번호 확인
		function passwdCheck(){
			let id = $("#inputIdArea").text();
			let passwd = $("#inputExistPasswd").val();
			
			$.ajax({
				type : "GET", 
				url : "passwdCheckPro.do", 
				data : {id : id, passwd : passwd}, 
				dataType : "text", 
				success : function(result) {
					if(result == "true"){
						$("#inputPasswdArea").html("****");
						$("#passwdChangekBtn").prop("disabled", false);
						$("#passwdCheckBtnArea").remove();
						alert("비밀번호 확인이 완료되었습니다.");
						
					}else{
						alert("잘못된 비밀번호입니다.");
					}
				}, 
				error : function() {
					alert("요청실패");
				}
			});
		}
			
		// 비밀번호 변경
		function passwdChange(){
			let id = $("#inputIdArea").text();
			let passwd = $("#inputNewPasswd").val();
			
			$.ajax({
				type : "GET", 
				url : "passwdChangePro.do", 
				data : {id : id, passwd : passwd}, 
				dataType : "text", 
				success : function(result) {
					if(result == "true"){
						alert("비밀번호 변경이 완료되었습니다.");
						location.href="${pageContext.request.contextPath }/loginForm.do";
					}else{
						alert("잘못된 비밀번호입니다.");
					}
				}, 
				error : function() {
					alert("요청실패");
				}
			});
		}
		
	</script>
</body>
</html>