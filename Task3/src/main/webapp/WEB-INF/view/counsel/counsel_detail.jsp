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
		<!-- 게시글 상세보기 영역 -->
		<div class="text-center mb-4">
			<h1 class="h3 mb-3 font-weight-normal">상세보기</h1>
		</div>
		<div class="card p-3 m-3">
			<table class="table table-striped">
				<tbody>
					<tr>
						<th scope="row">작성자</th>
						<td>${counsel.counsel_writer }</td>
					</tr>
					<tr>
						<th scope="row">작성일</th>
						<td><fmt:formatDate value="${counsel.counsel_date }" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td>${counsel.counsel_subject }</td>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td>${counsel.counsel_content }</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!-- 댓글 영역 -->
		<div class="text-center mb-4">
			<h1 class="h3 mb-3 font-weight-normal">댓글</h1>
		</div>
		<div class="card p-3 m-3">
			<form action="replyWritePro.do" method="post">
				<input type="hidden" name="seq_counsel" value="${counsel.seq_counsel }">
				<div class="row">
					<div class="col text-center">
						<label for="reply_writer" class="form-label">작성자</label>
						<input type="text" name="reply_writer" id="reply_writer" class="form-control" placeholder="작성자" required="required">
					</div>
					<div class="col-8 text-center">
						<label for="reply_content" class="form-label">내용</label>
						<textarea class="form-control" rows="1" name="reply_content" id="reply_content" placeholder="내용" required="required"></textarea>
					</div>
					<div class="col text-center">
						<label for="reply_submit" class="form-label text-center">등록</label>
						<button type="submit" id="reply_submit" class="btn btn-outline-dark">등록</button>
					</div>
				</div>
			</form>
		</div>
		<div class="card p-3 m-3">
			<table class="table table-sm">
				<thead>
					<tr>
						<th scope="col" class="text-nowrap">NO.</th>
						<th scope="col" class="text-nowrap">작성자</th>
						<th scope="col" class="text-nowrap">내용</th>
						<th scope="col" class="text-nowrap">수정/삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reply" items="${replyList }">
						<tr id="replyTr${reply.seq_reply }">
							<td class="text-nowrap">${reply.seq_reply }</td>
							<td class="text-nowrap">${reply.reply_writer }</td>
							<td id="replyContentArea">${reply.reply_content }</td>
							<td class="text-nowrap">
								<span id="replyModifyBtnArea"><a href="javascript:replyModifyForm(${reply.seq_reply })" class="btn btn-sm btn-outline-dark" id="replyModifyFormBtn">수정</a></span>
								<a class="btn btn-sm btn-outline-dark" href="replyDelete.do?seq_reply=${reply.seq_reply }&seq_counsel=${reply.seq_counsel }">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="text-center m-3">
			<a class="btn btn-dark" href="counselDelete.do?seq_counsel=${counsel.seq_counsel }">삭제</a>
			<a class="btn btn-outline-dark" href="counselList.do">목록</a>
		</div>
	</div>
	
	<script type="text/javascript">
		function replyModifyForm(seq_reply){
			let replyContent = $("#replyTr" + seq_reply + " > #replyContentArea").text();
			$("#replyTr" + seq_reply + " > #replyContentArea").html('<input type="text" name="reply_content" id="modifiedReplyContent" class="form-control" value="' + replyContent + '" required="required">')
			$("#replyTr" + seq_reply + " #replyModifyBtnArea").html('<button onclick="replyModifySubmit(' + seq_reply + ')" class="btn btn-sm btn-outline-dark" id="replyModifySubmitBtn">수정</button>')
			
		}
		
		function replyModifySubmit(seq_reply){
			let reply_content = $("#modifiedReplyContent").val();
			
			$.ajax({
				type : "GET", 
				url : "replyModifyPro.do", 
				data : {seq_reply : seq_reply, reply_content : reply_content}, 
				dataType : "text", 
				success : function(result) {
					if(result == "true"){
						location.reload();
					}else{
						alert("댓글 수정 실패");
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