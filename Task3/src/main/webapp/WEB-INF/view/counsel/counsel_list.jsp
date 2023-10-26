<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<jsp:include page="../inc/header_light.jsp"></jsp:include>
	<div class="article">
		<div class="text-center mb-4">
				<h1 class="h3 mb-3 font-weight-normal">글목록</h1>
			</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="row" class="text-nowrap">글번호</th>
					<th scope="row" class="text-nowrap">작성자</th>
					<th scope="row" class="text-nowrap">제목</th>
					<th scope="row" class="text-nowrap">작성일시</th>
					<th scope="row" class="text-nowrap">수정일시</th>
					<th scope="row" class="text-nowrap">상세보기</th>
					<th scope="row" class="text-nowrap">수정</th>
					<th scope="row" class="text-nowrap">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="counsel" items="${counselList }">
					<tr>
						<td class="text-nowrap">${counsel.seq_counsel }</td>
						<td class="text-nowrap">${counsel.counsel_writer }</td>
						<td class="text-truncate">${counsel.counsel_subject }</td>
						<td class="text-nowrap"><fmt:formatDate value="${counsel.counsel_date }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td class="text-nowrap"><fmt:formatDate value="${counsel.counsel_update_date }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td><a href="counselDetail.do?seq_counsel=${counsel.seq_counsel }" class="btn btn-sm btn-outline-dark text-nowrap">상세보기</a></td>
						<td class="text-nowrap"><a class="btn btn-sm btn-outline-dark" href="counselModifyForm.do?seq_counsel=${counsel.seq_counsel }">수정</a></td>
						<td class="text-nowrap"><a class="btn btn-sm btn-outline-dark" href="counselDelete.do?seq_counsel=${counsel.seq_counsel }">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-sm btn-outline-dark" href="counselWriteForm.do">글쓰기</a>
	</div>
</body>
</html>