<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>Insert title here</title>
<style type="text/css">
	.article{
		width: 100%;
		max-width: 650px;
		padding: 15px;
		margin: auto;
	}
</style>
</head>
<body>
	<jsp:include page="./WEB-INF/view/inc/header_light.jsp"></jsp:include>
	<div class="article">
		<div class="article">
			<div class="d-grid gap-2">
				<a href="counselWriteForm.do" class="btn btn-outline-dark" type="button">글쓰기</a>
				<a href="${pageContext.request.contextPath}/counselList.do" class="btn btn-outline-dark" type="button">글목록조회</a>
				<a href="slangRegistForm.do" class="btn btn-outline-dark">비속어등록</a>
			</div>
		</div>
	</div>
</body>
</html>