<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<form action="buyOk">
			<tr>
				<td>유저 아이디:</td>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr>
				<td>티켓 매수:</td>
				<td><input type="text" name="amount" size="2" /> 매</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="카드결제" /></td>
			</tr>
		</form>
	</table>
</body>
</html>