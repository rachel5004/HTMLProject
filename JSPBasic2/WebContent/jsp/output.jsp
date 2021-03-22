<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	사용자가 보내준 데이터를 받아서 처리하는 JSP
	1. 인코딩 확인(한글 여부)
	2. 데이터 name 속성
		getParameter()
		getParameterValues()
--%>
<%
	try{
		request.setCharacterEncoding("UTF-8"); 
	}catch(Exception e){}
	String name=request.getParameter("name");
	String pwd=request.getParameter("pwd");
	String sex=request.getParameter("sex");
	String loc=request.getParameter("loc");
	String content=request.getParameter("content");
	String[] hobby=request.getParameterValues("hobby");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
접속자의 IP:<%= request.getRemoteAddr() %><br>
접속자의 요청경로:<%= request.getRequestURI()%><br>
전체경로(서버정보 포함):<%= request.getRequestURL()%><br>
서버주소: <%= request.getServerName() %><br>
서버포트: <%= request.getServerPort() %><br>
서버프로토콜: <%= request.getProtocol() %><br>
데이터 전송방식: <%= request.getMethod() %><br>
<hr>
<h3>접속자가 보낸 데이터</h3>
이름: <%= name %>
비밀번호: <%= pwd %>
성별: <%= sex %>
지역:<%= loc %>
소개:<%= content %>
취미:<% try{ %>
	<ul>
	<% for(String h:hobby){ %>
		<li><%= h %></li>
	<% } %>
	</ul>
	<% }catch(Exception e){ %>
		<font color=red>취미가 없습니다!</font>
	<% } %>
</body>
</html>