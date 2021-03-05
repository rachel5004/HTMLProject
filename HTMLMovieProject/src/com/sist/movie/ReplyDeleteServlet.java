package com.sist.movie;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.MovieDAO;


@WebServlet("/ReplyDeleteServlet")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String no = request.getParameter("no");
	    String mno = request.getParameter("mno");
	    
	    MovieDAO dao = new MovieDAO();
	    dao.replyDelete(Integer.parseInt(no));
	    
	    response.sendRedirect("MovieDetailServlet?mno="+mno);
	    
	}
}
