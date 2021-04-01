package com.sist.model;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeVO;

@Controller
public class RecipeModel {
  @RequestMapping("recipe/recipe_list.do")
  public String recipe_list(HttpServletRequest request,HttpServletResponse response) {
	  String page=request.getParameter("page");
	  if(page==null) page="1";
	  int curpage = Integer.parseInt(page);
	  RecipeDAO dao=RecipeDAO.newInstance();// 오라클연동 
	  List<RecipeVO> rlist=dao.recipeListData(curpage);
	  int count = dao.recipeCount();
	  int totalpage = (int)Math.ceil(count/12.0);
	  
	  final int BLOCK = 10;
	  int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
	  int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  
	  if(endPage>totalpage) endPage = totalpage;

	  request.setAttribute("count", count);
	  request.setAttribute("rlist", rlist);
	  request.setAttribute("BLOCK", BLOCK);
	  request.setAttribute("startpage", startPage);
	  request.setAttribute("endpage", endPage);
	  request.setAttribute("totalpage", totalpage);
	  request.setAttribute("curpage", curpage);
	  
	  request.setAttribute("main_jsp", "../recipe/recipe_list.jsp");
	  return "../main/main.jsp";
  }
  
}