<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:out value="jstl 태그 출력"/>
<br>
<c:out value="${members[7].emial}" default="<i>이메일 없음</i>" escapeXml="false"></c:out>
<br>
<c:out value="${members[8].email}" escapeXml="false">
<span style="color:red">이메일 없음</span>
</c:out>

<c:forEach
	items="${applicationScope.members}"
	var="member"
	varStatus="status"
>
<h3>
${status.index} ${status.count}
이름 : ${member.name}, 이메일:
<c:out value="${member.email}" escapeXml="false">
<span style="color:red">이메일 없음</span>
</c:out>
</h3>
</c:forEach>


</body>
</html>