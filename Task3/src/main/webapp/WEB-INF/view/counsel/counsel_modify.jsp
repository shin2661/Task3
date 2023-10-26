<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>글쓰기</title>
<style type="text/css">
	.form-signin {
		width: 100%;
		max-width: 500px;
		padding: 15px;
		margin: auto;
	}
</style>
</head>
<body>
	<jsp:include page="../inc/header_light.jsp"></jsp:include>
	<form class="form-signin" action="counselModifyPro.do">
		<input type="hidden" name="seq_counsel" value="${counsel.seq_counsel }">
		<div class="text-center mb-4">
			<h1 class="h3 mb-3 font-weight-normal">글쓰기</h1>
		</div>
		<table class="table table-borderless">
			<tbody>
				<tr>
					<th scope="row">작성자</th>
					<td><input type="text" name="counsel_writer" class="form-control" value="${counsel.counsel_writer }" readonly="readonly"></td>
				</tr>
				<tr>
					<th scope="row">수정자</th>
					<td><input type="text" name="counsel_writer" class="form-control" value="" readonly="readonly"></td>
				</tr>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" name="counsel_subject" class="form-control" required="required" autofocus="" value="${counsel.counsel_subject }"></td>
				</tr>
				<tr>
					<th scope="row">내용</th>
					<td><textarea class="form-control" rows="3" name="counsel_content">${counsel.counsel_content }</textarea></td>
				</tr>
			</tbody>
		</table>
	  <div class="text-center"><button class="btn btn-dark" type="submit">글수정</button></div>
	</form>
</body>
</html>