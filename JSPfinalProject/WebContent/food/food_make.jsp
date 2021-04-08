<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
	<style type="text/css">
	    .mypage_row{
		  margin: 0px auto;
		  width:1200px;
		}
		.products{
			list-style:none;
			
			padding:0px;
			height:100%;
		}
		.products li{
			display:inline;
			float:left;
			margin:10px;
		}
		.item{
			display:block;
			text-decoration:none;
		}
		.item img{
			border:1px solid #333;
		}
		.item p{
			margin:0;
			font-weight:bold;
			text-align:center;
			color:#c3c3c3;
		}
		.products2{
			list-style:none;
			
			padding:0px;
			height:100%;
		}
		.products2 li{
			display:inline;
			float:left;
			margin:10px;
		}
		.item2{
			display:block;
			text-decoration:none;
		}
		.item2 img{
			border:1px solid #333;
		}
		.item2 p{
			margin:0;
			font-weight:bold;
			text-align:center;
			color:#c3c3c3;
		}
	</style>
	<script type="text/javascript">
	 let arr=[];
     $(function(){
    	 $("input:checkbox").on('click', function()
    	  {  
    		 let no=$(this).attr("value");
    		 if ($(this).prop('checked')) 
    		 { 
    			 arr.push(no);
    			 $('.products2').append(
    					 '<li id=m'+no+'>'
    					+'<img src="../images/recipe/'+no+'.png"/>'
    					+'<div>'
    					+'<p></p>'
    					+'</div>'
    					+'</a>'
    					+'</li>'
    			 ) 
    		 } 
    		 else 
    		 {
    		     $('#m'+no).remove();
    		     for(var i=0;i<arr.length;i++)
    		     {
    		         if(arr[i]==no)
    		         {
    		        	 delete arr[i];
    		         }
    		     }
    		     $('#make_print').text("");
    		 }
    	  }); 
    	 $('#makeBtn').click(function(){
    		 var data="";
    		 for(var i=0;i<arr.length;i++)
    		 {
    			 if(arr[i]!=undefined)
    			 data+=arr[i]+",";
    		 }
    		 data=data.substring(0,data.lastIndexOf(","));
    		 //alert(data);
    		 $.ajax({
    			 type:'post',
    			 url:'../recipe/recipe_make_result.do',
    			 data:{"food":data},
    			 success:function(result)
    			 {
    				 $('#make_print').html(result);
    			 }
    		 })
    	 })
     });
	</script>
</head>
<body>
	<div class="wrapper row3 mypage_row">
	  <h2 class="sectiontitle">레시피 만들기</h2>
	  <div style="height: 500px;overflow-y:auto">
	  <div class="one_half first">
		  <ul class="products">
		    <c:forEach var="i" begin="2" end="57">
				<li>
					<input type=checkbox id="food" value="${i }">
						<img src="../images/recipe/${i }.png"/>
						<div>
							<p></p>
						</div>
					</a>
				</li>
			 </c:forEach>
		   </ul>
	   </div>
	   <div class="one_half">
	       <p><input type="button" value="만들기" id="makeBtn" class="btn btn-sm btn-danger"></p>
	       <ul class="products2">
	         
	       </ul>
	       <div id="make_print"></div>
       </div>
	  </div>
	</div>
</body>
</html>