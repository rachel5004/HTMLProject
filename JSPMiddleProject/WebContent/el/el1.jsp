<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	EL: 화면 출력
		out.println("")
		<%= %>
		${}
		목적: 자바 / HTML 분리 => MV패턴
								Model => 자바
								View => HTML(JSP)
							=>	MVC패턴
								Model => 자바
								View => HTML(JSP)
								Controller => JAVA와 JSP를 연결
	EL
	연산자 처리
	메서드 처리
	처리 영역
	${일반 변수 출력이 아니다}
	예)
		String name="홍길동";
		${name} (X)
		request.getAttribute("name");
		session.getAttribute("");
 --%>
<%--
	연산자 out.println(1+1)
	산술연산자 9 - * / (div), %(mod)
		${10 + - * / % 10}, ${10 div 10}, ${10 mod 10}
		
		${10 / 3} => 3.3333     5/2 => 2.5 => 실수(Double)로 연산된다.
		${"10"+"10"} => 1010(X) => 20
			${"10" += "10"} => 1010 => 문자열 결합
			${10+null} => null(0) => 10
	비교연산자
		== (eq), != (ne), <(lt), >(gt), <=(le), >=(ge)
		${"aaa" == "aaa"}
		${"aaa" eq "aaa"}
		${10 != 20} => ${10 ne 20} => true / false
	논리연산자
	&& => and
	|| => or		=> true/ false
	
	empty 연산자
		=> 값이 없는 경우 ${empty 값}
	삼항연산자
		=> 조건 ? 값 : 값
	문자열결합
		=> +=
	
	
 --%> 
<%
	String name ="홍길동";
	request.setAttribute("name",name);
	session.setAttribute("nick", "심청이");
	// 우선순위 => request,session,application
	// 자바에서 데이터를 보낼 때 => request,session
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름: ${name}, ${requestScope.name}<br>
	<%= request.getAttribute("name") %><br>
	${sessionScope.name},${nick}
</body>
</html>