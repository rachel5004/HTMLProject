<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    DeptDAO dao=new DeptDAO();
    ArrayList<DeptVO> list=dao.deptListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   width:800px;
   margin: 0px auto;
}
h1 {
  text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// window.onload=function(){}
$(function(){
	$('#keyword').keyup(function(){
		let k=$(this).val();
		$('#user-table > tbody > tr').hide();
		let temp=$('#user-table > tbody > tr > td:nth-child(8n+2):contains("'+k+'")');
		$(temp).parent().show();
	});
});
</script>
</head>
<body>
   <div class="container">
    <h1>부서 목록</h1>
    <div class="row">
      <table class="table">
        <tr>
         <td>
           Search:<input type=text id=keyword class="input-sm">
         </td>
        </tr>
      </table>
      <table class="table table-striped" id="user-table">
        <tr class="info">
          <th class="text-center">부서번호</th>
          <th class="text-center">부서명</th>
          <th class="text-center">근무지</th>

        </tr>
        <tbody>
        <% for(DeptVO vo:list) { %>
                <tr>
                  <td class="text-center"><%=vo.getDeptno() %></td>
                  <td class="text-center"><%=vo.getDname() %></td>
                  <td class="text-center"><%=vo.getLoc() %></td>
                </tr>
        <% } %>
        </tbody>
      </table>
    </div>
   </div>
</body>
</html>










