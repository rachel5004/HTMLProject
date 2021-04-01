package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
/*
 *   MVC => Model,View,Controller
 *          =====
 *          요청처리 = Spring(Controller) , Struts(Action)
 *          public void display(@Parameter B b)
 */
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import java.util.*;
import com.sist.vo.*;
/*
 *    1) ../food/food_category.do?cno=1 => 사용자 요청 (jsp파일에 있다)
 *    =========================
 *    2) DispatcherServlet 
 *       => @RequestMapping("food/food_category.do")
 *    3) FoodModel 
 *    4) FoodDAO 
 *    5) DispatcherServlet
 *    6) food_category.jsp 에서 출력 
 */
@Controller
public class FoodModel {
  @RequestMapping("food/food_category.do")
  public String food_category(HttpServletRequest request,HttpServletResponse response)
  {
	  String cno=request.getParameter("cno");
	  // DAO연결 {no:1,name:"",}
	  FoodDAO dao=FoodDAO.newInstance();// 오라클연동 
	  List<FoodVO> list=dao.foodCategoryHouseData(Integer.parseInt(cno));
	  for(FoodVO vo:list)
	  {
		  String poster=vo.getPoster();
		  poster=poster.substring(0,poster.indexOf("^"));
		  vo.setPoster(poster);
	  }
	  FoodCategoryVO vo=dao.foodCategoryInfoData(Integer.parseInt(cno));
	  request.setAttribute("list", list);
	  request.setAttribute("vo", vo);
	  request.setAttribute("main_jsp", "../food/food_category.jsp");
	  return "../main/main.jsp";
  }
  
  
}










