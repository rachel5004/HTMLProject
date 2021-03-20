<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*" %>

<%
	String no = request.getParameter("no");
	String strpage = request.getParameter("page");
	
	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.boardDetailData(Integer.parseInt(no), 1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
   width:800px;
   margin: 0px auto;
}
td{
  font-size: 9pt;
  font-family: 맑은 고딕;
}
</style>
</head>
<body>
<div style="height:30px"></div>
  <div class="container">
   <div class="row">
    <img src="board.png" style="width:800px;height:150px">
    <table class="table">
      <tr>
      	<th class="text-center danger" width=20%>번호</th>
       	<td class="text-center"><%=vo.getNo() %></td>
       	<th class="text-center danger" width=20%>작성일</th>
       	<td class="text-center"><%=vo.getRegdate()%></td>
      </tr>
      <tr>
      	<th class="text-center danger" width=20%>이름</th>
       	<td class="text-center"><%=vo.getName() %></td>
       	<th class="text-center danger" width=20%>조회수</th>
       	<td class="text-center"><%=vo.getHit() %></td>
      </tr>
      <tr>
      	<th class="text-center danger" width=20%>제목</th>
       	<td colspan="3"><%=vo.getSubject() %></td>
      </tr>
      <tr>
      <td class="text-left" valign="top" height=200 colspan="4">
      <pre style="white-space: pre-wrap; border: none; background-color:white"></pre>
      <%=vo.getContent() %>
      </td>
      </tr>
      <tr>
      <td colspan="4" class="text-right">
      <a href="#" class="btn btn-sm btn-danger">답변</a>
      <a href="update.jsp?no=<%=no %>&page=<%=strpage %>" class="btn btn-sm btn-success">수정</a>
      <a href="#" class="btn btn-sm btn-warning">삭제</a>
      <a href="list.jsp?page=<%=strpage%>" class="btn btn-sm btn-info">목록</a>
      </td>
      </tr>
     </table>
   </div>
  </div>
</body>
</html>