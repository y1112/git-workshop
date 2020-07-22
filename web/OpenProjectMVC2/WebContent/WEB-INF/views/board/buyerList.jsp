
<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<Board> listAll = (List) request.getAttribute("listAll");
request.setAttribute("listAll", listAll);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>


<style>
td>img {
	width: 50px;
	height: 50px;
}
</style>
</head>
<body>

	리스트 ${listAll}
	<hr>
	<c:if test="${not empty listAll}">
		<h1>전체 게시글 수: ${cntAll} 개</h1>
		<hr>
		<hr>
	</c:if>
	<%-- <form id="boardForm" action="<%=request.getContextPath() %>/buyContent.jsp" method="post" enctype="multipart/form-data">
 --%>
	<form id="boardForm" action="buyerBoardContent.do" method="post"
		enctype="multipart/form-data">

		<table>
			<tr>
				<th><h3>idx</h3></th>
				<th><h3>userId</h3></th>
				<th><h3>title</h3></th>
				<!-- <th><h3>content</h3></th>
 -->
			</tr>
			<c:if test="${not empty listAll}">
				<c:forEach items="${listAll}" var="board">
					<tr>
						<td><h3>${board.idx}</h3>
							<input type="hidden" name="idx" value="${board.idx}"></td>
						<td><h3>${board.userId}</h3></td>
						<td><h3>
								<a href="<%=request.getContextPath()%>/buyContent.jsp">${board.title}</a>
							</h3>
							<input type="submit" value="${board.title}"></td>
						<td><h3>
								<a href="editBoardContent.do">수정</a>
							</h3>
							<input type="submit" value="${board.title}"></td>
						<td><h3>
								<a href="deletBoardContent.do">삭제</a>
							</h3>
							<input type="submit" value="${board.title}"></td>
						<td><a href="javascript:memberDel(${board.idx})">삭제</a></td>
						</td>
						<%-- <td><h3>${board.content}</h3></td>
/board/buyerBoardContent.do
 --%>
					</tr>

				</c:forEach>
			</c:if>
		</table>
	</form>
	<c:if test="${empty listAll}">
작성된 게시글이 없습니다
</c:if>

	<script>
		function memberDel(idx) {
			if (confirm('선택하신 회원 정보를 삭제하시겠습니까?')) {
				location.href = 'deletBoardContent.do?idx=' + idx;
			}

		}
	</script>
	<%-- 
	<div>
		<h1 class="subtitle"> 리스트</h1>

		<c:if test="${not empty listView  }">

		<div>전체회원 ${listView.memberTotalCount} 명</div>
		<hr>
	
		<table class="table">
			<tr>
				<th>no</th>
				<th>아이디</th>
				<th>제목</th>
				<th>분류</th>
				<th>내용</th>
				<th>조회수</th>
			</tr>
			
		<c:if test="${not empty listView.memberList }">
		<c:forEach items="${listView.memberList}" var="member">
			<tr>
				<td>${member.idx}</td>
				<td>${member.userId}</td>
				<td>${member.userName}</td>
				<td>${member.userPw}</td>
				<td><img alt="프사 " src="<c:url value="${member.photo}"/>">  </td>
				<td>
				<a href="memberEditForm.do?idx=${member.idx}">수정</a> 
				| 
				<a href="javascript:memberDel(${member.idx})">삭제</a></td>
			</tr>
		</c:forEach>
		</c:if>
		
		<c:if test="${empty listView.memberList }">
			<tr>
				<th>조회된 회원이 없습니다.</th>
			</tr>
		</c:if>


		</table>
		
		<div class="paging">
			<c:forEach begin="1" end="${listView.pageTotalCount}" var="i">
			
			<a class="paging_num ${i == listView.currentPageNumber ? 'now_page' : ''}" href="memberList.do?page=${i}" >${i}</a>
			
			</c:forEach>
		</div>



		</c:if>
	</div>

</body>
</html>


<script>
	function memberDel(idx){
		if(confirm('선택하신 회원 정보를 삭제하시겠습니까?')){
			location.href = 'memberDelete.do?idx='+idx;
		}
		
	}
</script> --%>
<%-- 
				<c:if test="${not empty listView.memberList }">
					<c:forEach items="${listView.memberList}" var="member">
						<tr>
							<td>${member.idx}</td>
							<td>${member.uid}</td>
							<td>${member.uname}</td>
							<td>${member.upw}</td>
							<td><img alt="프사 " src="<c:url value="${member.uphoto}"/>">
							</td>
							<td><a href="memberEditForm.do?idx=${member.idx}">수정</a> | <a
								href="javascript:memberDel(${member.idx})">삭제</a></td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if test="${empty listView.memberList }">
					<tr>
						<th>조회된 회원이 없습니다.</th>
					</tr>
				</c:if>


			</table>

			<div class="paging">
				<c:forEach begin="1" end="${listView.pageTotalCount}" var="i">

					<a
						class="paging_num ${i == listView.currentPageNumber ? 'now_page' : ''}"
						href="memberList.do?page=${i}">${i}</a>

				</c:forEach>
			</div>



		</c:if>
	</div>

</body>
</html>
 --%>







