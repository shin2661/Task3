<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	.article {
		width: 100%;
		max-width: 420px;
		padding: 15px;
		margin: auto;
	}
</style>
</head>
<body>
	<jsp:include page="../inc/header_light.jsp"></jsp:include>
	<div class="article">
		<form class="form-signin" action="counselWritePro.do">
			<div class="text-center mb-4">
				<h1 class="h3 mb-3 font-weight-normal">글쓰기</h1>
			</div>
			<table class="table table-borderless">
				<tbody>
					<tr>
						<th scope="row">작성자</th>
						<td><input type="text" name="counsel_writer" class="form-control"  required="required" readonly="readonly"></td>
<%-- 						<td><input type="text" name="counsel_writer" class="form-control" required="required" readonly="readonly" value='<sec:authentication property="principal.id"/>'></td> --%>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td><input type="text" name="counsel_subject" class="form-control" required="required" autofocus=""></td>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td><textarea class="form-control" rows="3" name="counsel_content" required="required"></textarea></td>
					</tr>
				</tbody>
			</table>
		  <div class="text-center">
			  <button onclick="history.back()" class="btn btn-outline-dark" type="button">뒤로가기</button>
			  <button class="btn btn-dark" type="submit">글등록</button>
		  </div>
		</form>
	</div>
</body>
</html>