<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*" %>
<%
	String no = request.getParameter("no");
	String strpage = request.getParameter("page");
	
	BoardDAO dao = new BoardDAO();
	BoardVO vo = dao.boardDetailData(Integer.parseInt(no), 2);
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
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(()=>{
	$('.sendBtn').click(()=>{
		let name = $('input[name=name]').val();
		if(name.trim()==""){
			 $('input[name=name]').focus();
			return;
		}
		let subject = $('input[name=subject]').val();
		if(subject.trim()==""){
			 $('input[name=subject]').focus();
			return;
		}
		let content= $('textarea').val();
		if(content.trim()==""){
			 $('textarea').focus();
			return;
		}
		let pwd = $('input[name=pwd]').val();
		if(pwd.trim()==""){
			 $('input[name=pwd]').focus();
			return;
		}
		
		$('#frm').submit();
	})
})

</script>
</head>
<body>
  <div style="height:30px"></div>
  <div class="container">
   <div class="row">
    <img src="board.png" style="width:800px;height:150px">
    <form method=post action="update_ok.jsp" id="frm" autocomplete="off">
    <table class="table">
    <tr>
    <th class="text-right danger" width=15%>이름</th>
    <td width=85%>
    	<input type=text name=name class="input-sm" size=15 value="<%=vo.getName() %>">
    </td>
    </tr>
    <tr>
    <th class="text-right danger" width=15%>제목</th>
    <td width=85%>
    	<input type=text name=subject class="input-sm" size=55 value="<%=vo.getSubject() %>">
    </td>
    </tr>
    <tr>
    <th class="text-right danger" width=15% valign="top">내용</th>
    <td width=85%>
    	<textarea rows="10" cols="62" name=content><%=vo.getContent() %></textarea>
    </td>
    </tr>
    <tr>
    <th class="text-right danger" width=15%>비밀번호</th>
    <td width=85%>
    	<input type=password name=pwd class="input-sm" size=15>
    </td>
    </tr>
    <tr>
    <td colspan="2" class="text-center">
    	<input type="button" value="수정하기" class="btn btn-sm btn-primary sendBtn">
    	<input type="button" value="취소" class="btn btn-sm btn-success" onclick="javascript:history.back()">
    </td>
    </tr>
    </table>
    </form>
  </div>
  </div>
</body>
</html>