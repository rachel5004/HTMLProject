<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    /*
         list.jsp?page=  => null(X) ""
         list.jsp        => null
         list.jsp?page=1 => 1
    */
    // page : 내장객체 ==> 자바(this) , 웹 (page)
    // page=this
    String strPage=request.getParameter("page");
    // 시작 => 페이지를 지정하지 않는다 
    if(strPage==null)
    	strPage="1";
    
    int curpage=Integer.parseInt(strPage);
    BoardDAO dao=new BoardDAO();
    List<BoardVO> list=dao.boardListData(curpage);
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>묻고답하기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
   width:800px;
   margin: 0px auto;
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
       <td>
        <a href="insert.jsp" class="btn btn-sm btn-primary">새글</a>
       </td>
      </tr>
    </table>
    <div style="height:400px">
	    <table class="table table-hover">
	      <tr class="info">
	        <th class="text-center" width=10%>번호</th>
	        <th class="text-center" width=45%>제목</th>
	        <th class="text-center" width=15%>이름</th>
	        <th class="text-center" width=20%>작성일</th>
	        <th class="text-center" width=10%>조회수</th>
	      </tr>
	      <% for(BoardVO vo:list) { %>
	              <tr>
			        <td class="text-center" width=10%><%=vo.getNo() %></td>
			        <td width=45%>
			        <%--답변 표시 --%>
			        <% if(vo.getGroup_tab()>0) {
			        	   for(int i=0;i<vo.getGroup_tab();i++) {
			        		   out.println("&nbsp;&nbsp;");
			        	   } %>
			              <img src="re.gif">
			        <% } %>
			        <%=vo.getSubject() %>
			        &nbsp;
			        <% String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			           String dbday=vo.getRegdate().toString();
			           if(today.equals(dbday)) { %>
			              <sup style="color:red">new</sup>
			        <% }  %>
			        </td>
			        <td class="text-center" width=15%><%=vo.getName() %></td>
			        <td class="text-center" width=20%><%=vo.getRegdate().toString() %></td>
			        <td class="text-center" width=10%><%=vo.getHit() %></td>
			      </tr>
	      <% } %>
	    </table>
    </div>
    <table class="table">
      <tr>
       <td class="text-right">
        <a href="#" class="btn btn-sm btn-danger">이전</a>
        1 page / 1 pages
        <a href="#" class="btn btn-sm btn-warning">다음</a>
       </td>
      </tr>
    </table>
   </div>
  </div>
</body>
</html>













