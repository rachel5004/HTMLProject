<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="model" class="com.sist.model.SawonModel"></jsp:useBean>
<%
	//Controller : client request => get request => call method
	model.sawonInit(request); // call by reference
	model.sawonDetailData(request); //vo	
	model.sawonTwoData(request);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름: ${name}<br>
	사번: ${vo.sabun}, ${vo.getSabun()} <br>
	이름: ${vo.name}, ${vo.getName()}<br>
	부서: ${vo.dept}, ${vo.getDept()}<br>
	이름: ${first+=last} <!-- 문자열 결합하려면 +가 아니라 +=를 입력해야한다. -->
</body>
</html>