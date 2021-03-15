package com.sist.movie;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;

@WebServlet("/MovieBoxofficeServlet")
public class MovieBoxofficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		// 데이터 받기
		String cno=request.getParameter("cno");
		if(cno==null)
			cno="2";
		int i=Integer.parseInt(cno);
		MovieDAO dao=new MovieDAO();
		ArrayList<MovieVO> list=dao.movieListData(i);
		
		out.println("<div class=container>");
		out.println("<h1 class=text-center>박스오피스</h1>");
		out.println("<div class=row>");
		out.println("<div class=text-right>");
		out.println("<a href=MovieMainServlet?mode=2 class=\"btn btn-lg btn-success\">주간</a>");
		out.println("<a href=MovieMainServlet?mode=2&cno=3 class=\"btn btn-lg btn-info\">월간</a>");
		out.println("<a href=MovieMainServlet?mode=2&cno=4 class=\"btn btn-lg btn-warning\">년간</a>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=row>");
		for(MovieVO vo:list)
		{
			out.println("<div class=\"col-md-3\">\r\n"
					+ "    <div class=\"thumbnail\">\r\n"
					+ "      <a href=\"#\">\r\n"
					+ "        <img src=\""+vo.getPoster()+"\" alt=\"Lights\" style=\"width:100%\">\r\n"
					+ "        <div class=\"caption\">\r\n"
					+ "          <p style=\"font-size:9pt\">"+vo.getTitle()+"</p>\r\n"
					+ "        </div>\r\n"
					+ "      </a>\r\n"
					+ "    </div>\r\n"
					+ "  </div>");
		}
		out.println("</div>");
		out.println("</div>");
	}

}
