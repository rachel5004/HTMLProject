<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.mypage_row{
  margin: 0px auto;
  width:960px;
}
</style>
</head>
<body>
  <div class="wrapper row3 mypage_row">
    <h2 class="sectiontitle">맛집 목록</h2>
    <div style="height: 600px;width:800px;margin:0px auto;overflow-y:auto">
      <c:forEach var="item" items="${list }">
        <table class="table">
         <tr>
           <td width=30% class="text-center"  rowspan="3">
             <img src="${item.url }" style="width:220px;height:150px">
           </td>
           <td width=70%>${item.title }</td>
         </tr>
         <tr>
           <td width=70%>${item.description }</td>
         </tr>
         <tr>
           <td class="text-right" width=70%>${item.author }</td>
         </tr>
        </table>
      </c:forEach>
    </div>
  </div>

</body>
</html>








