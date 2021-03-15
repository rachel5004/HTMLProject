package com.sist.movie;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MovieHomeServlet")
public class MovieHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<embed src=\"http://youtube.com/embed/bdcIC8d4nW0\\u0026t=4s\" width=960px height=500>");
		out.println("</div>");
		out.println("</div>");
	}

}
