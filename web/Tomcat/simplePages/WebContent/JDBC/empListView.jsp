<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>��� ����Ʈ</h1>
	<hr>
	
	
	
	
	<table border=1>
	
		<tr>
			<th>��� ��ȣ</th>
			<th>��� �̸�</th>
			<th>��� �޿�</th>
			<th>��� ����</th>
		</tr>
		
		<c:forEach items="${empList}" var="emp">
		<tr>
			<td>${emp.empno}</td>
			<td>${emp.ename}</td>
			<td>${emp.sal}</td>
			<td>${emp.job}</td>
		</tr>		
		</c:forEach>
	
	
	</table>
	
</body>
</html>