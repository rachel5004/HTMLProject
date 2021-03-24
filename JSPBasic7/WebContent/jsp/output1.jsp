<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.bean.*" %>
<%--
     public void display(MemberBean bean)
     {
        dao.insert(bean)
     }
     
     jsp에서 지원하는 액션태그  <jsp:
     1. 클래스 메모리 할당 => 싱글턴  <jsp:useBean id="객체명" class="패키지명.클래스명">
        Class.forName("패키지명.클래스명") => 클래스를 등록 메모리할당 요청 => 패키지명.클래스명
     2. 클라이언트가 전송한 데이터를 받아서 setXxx()에 저장하는 태그 
        <jsp:setProperty name="객체명" property="변수명"/> 전체는 *
     3. 빈에 저장된 데이터를 읽어서 출력 : <jsp:getProperty name="객체명" property="변수명">
     4. request를 공유하면서 JSP여러개를 조립 : <jsp:include page="파일명">
     5. request를 공유하면 화면을 이동 : <jsp:forward page="">
     
     Bean을 만들 경우 : 데이터를 모아서 한번에 전송할 목적 
     =============
      변수:private => 읽기(getter)/쓰기(setter)
      메소드 : public 
        boolean => isXxx()
      
 --%>
<%
	request.setCharacterEncoding("UTF-8");
%> 
<jsp:useBean id="bean" class="com.sist.bean.MemberBean">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>    
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
	<%
		try{
			Class clsname = Class.forName("com.sist.bean.MemberBean");
			Object obj = clsname.newInstance();
		}catch(Exception e){}
	%>
</body>
</html>