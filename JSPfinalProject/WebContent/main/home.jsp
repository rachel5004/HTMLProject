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
<div class="wrapper row3">
  <div id="slider" class="clear"> 
    <!-- ################################################################################################ -->
    <div class="flexslider basicslider">
      <ul class="slides">
        <li><a href="#"><img class="radius-10" src="../images/1.jpeg" alt="" style="width:987px;height:400px"></a></li>
        <li><a href="#"><img class="radius-10" src="../images/2.jpeg" alt="" style="width:987px;height:400px"></a></li>
        <li><a href="#"><img class="radius-10" src="../images/33.jpg" alt="" style="width:987px;height:400px"></a></li>
      </ul>
    </div>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <!-- ################################################################################################ -->
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="#">인기 맛집 소개</a></h6>
          <p>날씨가 따듯해진 요즘, 부산으로 떠나볼까요? 오늘은 부산의 핫플 서면에서 주목해야 할 맛집을 소개합니다.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="#">인기 레시피 소개</a></h6>
          <p>초간단 조리과정과 집에 있는 착한 양념에 비해
             미친 비주얼을 자랑하는 닭봉조림~ 언제 어디에서 누구에게 해줘도 인기만점 레시피예요</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">인기 재료 소개</a></h6>
          <p>재택근무가 많아지면서 그동안 먹지 못했던 아침식사를 여유롭게 챙겨먹는 이들이 많아지고 있다. 아침식사를 위한 건강하고 편리한...</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
    <!-- ################################################################################################ -->
    <h2 class="sectiontitle">믿고 보는 맛집 리스트</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <%--
           for(FoodCategoryVO vo:cList)
           varStatus: List의 index번호
        --%>
       <c:forEach var="vo" items="${cList }" varStatus="s">
        <c:if test="${s.index<12 }">
	        <li>
	          <a href="../food/food_category.do?cno=${vo.no }">
		          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" alt="">
		            <figcaption>${vo.title }</figcaption>
		            <figcaption>${vo.subject }</figcaption>
		          </figure>
	          </a>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    
    <!-- 두번째 -->
    <h2 class="sectiontitle">지역별 인기 맛집</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${cList }" varStatus="s">
         <c:if test="${s.index>11 and s.index<18 }">
	        <li>
	         <a href="../food/food_category.do?cno=${vo.no }">
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" alt="">
	            <figcaption>${vo.title }</figcaption>
	            <figcaption>${vo.subject }</figcaption>
	           </figure>
	          </a>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <!-- 세번째 -->
    <h2 class="sectiontitle">메뉴별 인기 맛집</h2>
    <!-- ################################################################################################ -->
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${cList }" varStatus="s">
         <c:if test="${s.index>17 }">
	        <li>
	         <a href="../food/food_category.do?cno=${vo.no }">
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" alt="">
	            <figcaption>${vo.title }</figcaption>
	            <figcaption>${vo.subject }</figcaption>
	          </figure>
	          </a>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <!-- ################################################################################################ -->
    <h2 class="sectiontitle">최근 방문 맛집</h2>
    <!-- ################################################################################################ -->
      <%--
            for(int i=0;i<list.size();i++)
            {
                MovieVO vo=list.get(i);
            }
       --%>
      <c:forEach var="fvo" items="${fList }" varStatus="s">
        <c:if test="${s.index<9 }">
         <a href="../food/food_detail.do?no=${fvo.no }">
         <img class="radius-10" src="${fvo.poster }" title="${fvo.title }" style="width:100px;height:100px"></a>
        </c:if>
      </c:forEach>
        
        
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>







