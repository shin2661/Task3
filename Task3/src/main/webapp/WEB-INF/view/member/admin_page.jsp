<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
</head>
<body>
<jsp:include page="../inc/header_light.jsp"></jsp:include>
	<div class="container">
		<!-- 게시글 상세보기 영역 -->
		<div class="text-center mt-4">
			<h1 class="h3 mb-3 font-weight-normal">ROLE_ADMIN만 접근가능한 페이지 입니다.</h1>
		</div>
	</div>
</body>
</html>