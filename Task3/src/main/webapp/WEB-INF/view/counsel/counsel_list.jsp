<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
</style>
</head>
<body>
	<jsp:include page="../inc/header_light.jsp"></jsp:include>
	<%-- pageNum 파라미터 가져와서 저장(없을 경우 기본값 1로 설정) --%>
	<c:set var="pageNum" value="1" />
	<c:if test="${not empty param.pageNum }">
		<c:set var="pageNum" value="${param.pageNum }" />
	</c:if>
	
	<div class="container">
		<div class="text-center mb-4">
			<h1 class="h3 mb-3 font-weight-normal">글목록</h1>
		</div>
		<div class="row g-2 align-items-center mb-4">
		<form action="counselList.do" class="row g-2 align-items-center mb-4">
			<div class="col-auto me-auto">
				<input type="date" class="form-control" name="searchDate" id="searchDate">
			</div>
			<div class="col-auto text-end">
				<%-- 검색타입목록, 검색어입력창 추가 --%>
				<select name="searchType" id="searchType" class="form-select-sm">
					<option value="subject" <c:if test="${param.searchType eq 'subject' }">selected</c:if>>제목</option>			
					<option value="content" <c:if test="${param.searchType eq 'content' }">selected</c:if>>내용</option>			
					<option value="subject_content" <c:if test="${param.searchType eq 'subject_content' }">selected</c:if>>제목&내용</option>			
					<option value="writer" <c:if test="${param.searchType eq 'name' }">selected</c:if>>작성자</option>			
				</select>
				<input type="text" name="searchKeyword" value="${param.searchKeyword }" id="searchKeyword" class="form-control form-control-sm" style="width: 200px; display: inline;">
				<input type="submit" value="검색" class="btn btn-sm btn-outline-success" id="searchBtn">
			</div>
		</form>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="row" class="text-nowrap">글번호</th>
					<th scope="row" class="text-nowrap">작성자</th>
					<th scope="row" class="text-nowrap">제목</th>
					<th scope="row" class="text-nowrap">작성일시</th>
					<th scope="row" class="text-nowrap">수정자</th>
					<th scope="row" class="text-nowrap">수정일시</th>
					<th scope="row" class="text-nowrap"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="counsel" items="${counselList }">
					<tr>
						<td class="text-nowrap">${counsel.seq_counsel }</td>
						<td class="text-nowrap">${counsel.counsel_writer }</td>
						<td class="text-truncate">${counsel.counsel_subject }</td>
						<td class="text-nowrap"><fmt:formatDate value="${counsel.counsel_date }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td class="text-truncate">${counsel.counsel_update_writer }</td>
						<td class="text-nowrap"><fmt:formatDate value="${counsel.counsel_update_date }" pattern="yyyy-MM-dd HH:mm"/></td>
						<td class="text-nowrap">
							<a href="counselDetail.do?seq_counsel=${counsel.seq_counsel }" class="btn btn-sm btn-outline-dark text-nowrap">상세보기</a>
							<a class="btn btn-sm btn-outline-dark" href="user/counselModifyForm.do?seq_counsel=${counsel.seq_counsel }">수정</a>
							<!-- 로그인한 회원의 경우 -->
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="principal.username" var="username" /> <!-- 로그인한 사용자의 이름 저장 -->
								<sec:authentication property="principal.authorities" var="authorities" /> <!-- 로그인한 사용자의 권한 저장 -->
									<c:forEach var="authority" items="${authorities}">
										<!-- 작성자이거나 admin인 경우만 보여짐 -->
			     						<c:if test="${username eq counsel.counsel_writer or authority.authority eq 'ROLE_ADMIN'}">
											<a class="btn btn-sm btn-outline-dark" href="counselDelete.do?seq_counsel=${counsel.seq_counsel }&counsel_writer=${counsel.counsel_writer }">삭제</a>
										</c:if>
									</c:forEach>
							</sec:authorize>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table><!-- 게시글 목록 끝 -->
		
		<div class="row g-2 align-items-center mb-4">
			<!-- 페이징 처리 -->
			<div id="pageList" class="col-auto me-auto text-center">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<c:choose>
							<c:when test="${pageNum > 1 }">
								<li class="page-item">
									<a class="page-link" href="location.href='counselList.do?pageNum=${pageNum - 1}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled">
									<a class="page-link" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
				
						<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
							<c:choose>
								<c:when test="${pageNum eq i }">
									<li class="page-item"><a class="page-link"><b>${i }</b></a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a href="counselList.do?pageNum=${i }" class="page-link">${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>		
						<c:choose>
							<c:when test="${pageNum < pageInfo.maxPage }">
								<li class="page-item">
									<a class="page-link" href="counselList.do?pageNum=${pageNum + 1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled">
									<a class="page-link" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</div>
			<!-- 글쓰기 버튼 -->
			<div class="col-auto text-end">
				<a class="btn btn-sm btn-outline-dark" href="counselWriteForm.do">글쓰기</a>
			</div>
		</div><!-- 페이징 끝 -->
	</div><!-- container 끝 -->
	
</body>
</html>