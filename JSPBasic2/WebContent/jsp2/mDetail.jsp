<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
    //1.사용자가 보낸 데이터 받기 
    String no=request.getParameter("no");
    //2.오라클 연결 => 데이터 읽기
    MusicDAO dao=new MusicDAO();
    MusicVO vo=dao.musicDetailData(Integer.parseInt(no));    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
  <table class="table">
    <tr>
      <td width=30% class="text-center" rowspan="5">
       <img src="<%=vo.getPoster() %>" width=150 height=150>
      </td>
      <td colspan="2">
        <span style="color:orange;font-size: 11pt"><%=vo.getNo() %>위</span>
      </td>
    </tr>
    <tr>
      <td width=20% class="text-right">제목</td>
      <td width=50%><%=vo.getTitle() %></td>
    </tr>
    <tr>
      <td width=20% class="text-right">가수</td>
      <td width=50%><%=vo.getSinger() %></td>
    </tr>
    <tr>
      <td width=20% class="text-right">앨범</td>
      <td width=50%><%=vo.getAlbum() %></td>
    </tr>
    <tr>
      <td width=20% class="text-right">등락</td>
      <td width=50%><%=vo.getState()%><%=vo.getIdcrement() %></td>
    </tr>    
  </table>
</body>
</html>