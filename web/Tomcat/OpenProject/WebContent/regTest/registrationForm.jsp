<%@page import="util.CookieBox"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CookieBox cookieBox = new CookieBox(request);
	
	String cookieUid = cookieBox.getValue("uid");
	String cookiePw = cookieBox.getValue("pw");

	String cookieEmail = cookieBox.getValue("email");
	String cookieImg = cookieBox.getValue("img");

	String uidValue = "";
	String pwValue = "";
	String emailValue = "";
	String imgValue = "";
	
	if((cookieUid!=null)&&(cookiePw!=null)&&(cookieEmail!=null)&&(cookieImg!=null)){
		uidValue = cookieUid;
		pwValue=cookiePw;
		emailValue=cookieEmail;
		imgValue=cookieImg;
	}
	
 
%>


	<div>
		<h1 class="subtitle">회원가입</h1>
		
		<hr>
		
		<form action="registration.jsp" method="post">
		
<%-- 			<input type="text" name="redirecUri" 
			
			value="<%= request.getHeader("referer")%>" style=" width : 50% ;" >	 --%>		
			
			<!--	public LoginInfo(String uid, String uname, String email, String photo) {  -->
			<table class="table">
				<tr>
					<td>아이디</td>
					<td> <input type="text" name="uid" value="<%= uidValue%>"> </td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td> <input type="password" name="pw" value="<%= pwValue%>"> </td>
				</tr>				
				<tr>
					<td>이메일</td>
					<td> <input type="text" name="email" value="<%= emailValue%>"> </td>
				</tr>
				<tr>
					<td>사진</td>
					<td> <input id="img" type="file" name="img" value="<%= imgValue%>"> </td>
				</tr>				
				<tr>
					<td colspan="2"> <input type="submit" value="회원가입"> <input type="reset" value="초기화"></td>
				</tr>
			</table>
		
		</form>
		
		
	</div>

