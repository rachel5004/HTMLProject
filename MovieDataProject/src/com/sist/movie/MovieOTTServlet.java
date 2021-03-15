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

@WebServlet("/MovieOTTServlet")
public class MovieOTTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		// 데이터 받기
		MovieDAO dao=new MovieDAO();
		ArrayList<MovieVO> list=dao.movieListData(5);
		
		out.println("<div class=container>");
		out.println("<h1 class=text-center>OTT</h1>");
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
