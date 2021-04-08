<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="gallery">
        <figure>
          <ul class="nospace clear">
          <c:forEach var="vo" items="${fList }" varStatus="s">
            <c:if test="${s.index%4==0 }">
             <li class="one_quarter first"><a href="../food/food_detail.do?no=${vo.no }"><img src="${vo.poster }" title="${vo.title }" class="img-rounded"></a></li>
            </c:if>
            <c:if test="${s.index%4!=0 }">
             <li class="one_quarter"><a href="../food/food_detail.do?no=${vo.no }"><img src="${vo.poster }" title="${vo.title }" class="img-rounded"></a></li>
            </c:if>
          </c:forEach>
          </ul>
          <figcaption></figcaption>
        </figure>
      </div>
</body>
</html>