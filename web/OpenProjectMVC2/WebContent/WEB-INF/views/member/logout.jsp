<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<meta charset="utf-8">
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert('로그아웃 되었습니다.');
	location.href='${pageContext.request.contextPath}';
</script>
