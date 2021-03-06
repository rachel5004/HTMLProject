package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/BoardInsertServlet")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		out.write("<html>");
		out.write("<head>");
		out.println("<style type=text/css>");
		out.println("td,th{font-family:맑은 고딕;font-size:9pt;height:35px}");
		out.println(".table_main{border-collapse:collapse;");
		out.println("border-top-width: 2px;"
				+ "border-bottom-width: 1px;"
				+ "border-top-style: solid;"
				+ "border-bottom-style: solid;"
				+ "border-top-color: #333;"
				+ "border-bottom-color: #333;}");
		out.println(".table_main th{");
		out.println("background-color: #999;"
				+ "color: #fff;"
				+ "border-bottom-width: 1px;"
				+ "border-bottom-color: #333;}");
		out.println(".table_main td{");
		out.println("border-bottom-width: 1px;"
				+ "border-bottom-color: #666;"
				+ "border-bottom-style: dotted;}");
		out.println("a{color:black;text-decoration:none}");
		out.println("a:hover{color:green;text-decoration:underline}");
		out.println(".dataTr:HOVER {"
				+ "background-color: #FC6;"
				+ "cursor: pointer;"
				+ "cursor: hand;"
				+ "}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<center>");
		out.write("<h1>글쓰기</h1>");
		
		out.write("<form method=post action=BoardInsertServlet autocomplete=off>");
		
		out.write("<table class=table_main width=600>");
		out.write("<tr>");
		out.write("<th width=20% align=right>이름</th>");
		out.write("<td width=80%>");
		out.write("<input type=text name=name size=15>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=20% align=right>제목</th>");
		out.write("<td width=80%>");
		out.write("<input type=text name=subject size=50>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=20% align=right>내용</th>");
		out.write("<td width=80%>");
		out.write("<textarea rows=10 cols=55 name=content></textarea>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=20% align=right>비밀번호</th>");
		out.write("<td width=80%>");
		out.write("<input type=password name=pwd size=15>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<td colspan=2 align=center>");
		out.write("<input type=submit value=글쓰기>");
		out.write("<input type=button value=취소 onclick=\"javascript:history.back()\">");
		out.write("</td>");
		out.write("</tr>");
//		out.write("<>");
		
		out.write("</table>");
		out.write("</form>");
		out.write("</center>");
		out.write("</body>");
		out.write("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("EUC-KR");  // decode
		}catch(Exception e) {}
		
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		System.out.println("이름: "+name);
		System.out.println("제목: "+subject);
		System.out.println("내용: "+content);
		System.out.println("비밀번호: "+pwd);
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		dao.boardInsert(vo);
		response.sendRedirect("BoardListServlet");
		
	}

}
