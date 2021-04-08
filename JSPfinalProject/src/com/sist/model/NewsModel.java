package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.news.*;

@Controller
public class NewsModel {
  @RequestMapping("news/news_food.do")
  public String news_food(HttpServletRequest request,HttpServletResponse response)
  {
	  NewsManager m=new NewsManager();
	  List<Item> list=m.newsAllData("맛집"); 
	  request.setAttribute("list", list);
		
	  request.setAttribute("main_jsp", "../news/news_food.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("news/news_recipe.do")
  public String news_recipe(HttpServletRequest request,HttpServletResponse response)
  {
	  NewsManager m=new NewsManager();
	  List<Item> list=m.newsAllData("레시피"); 
	  request.setAttribute("list", list);
		
	  request.setAttribute("main_jsp", "../news/news_food.jsp");
	  return "../main/main.jsp";
  }
  @RequestMapping("news/news_total.do")
  public String news_total(HttpServletRequest request,HttpServletResponse response)
  {
	  NewsManager m=new NewsManager();
	  List<Item> list=m.newsAllData("뉴스"); 
	  request.setAttribute("list", list);
		
	  request.setAttribute("main_jsp", "../news/news_food.jsp");
	  return "../main/main.jsp";
  }
}
