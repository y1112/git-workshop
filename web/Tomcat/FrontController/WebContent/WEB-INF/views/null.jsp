<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>null</h1><hr>
<h1>잘못된 요청입니다</h1>
<a href="<%=request.getContextPath() %>/index">홈으로 가기</a>
<h1>${result }</h1>
</body>
</html>