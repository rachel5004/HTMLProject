<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.bean.*" %>
<%--
     public void jspService(HttpServletRequest request,HttpServletResponse response)
     throws Exception
     {
 --%>
<%
	try{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception e){}
	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String sex = request.getParameter("sex");
	String addr = request.getParameter("addr");
	String tel = request.getParameter("tel");
	
	MemberBean bean = new MemberBean();
	bean.setName(name);
	bean.setId(id);
	bean.setSex(sex);
	bean.setAddr(addr);
	bean.setTel(tel);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	아이디:<%=bean.getId()%>
	이름:<%=bean.getName()%>
	성별:<%=bean.getSex()%>
	주소:<%=bean.getAddr()%>
	전화:<%=bean.getTel()%>
</body>
</html>