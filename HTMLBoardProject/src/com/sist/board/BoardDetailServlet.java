package com.sist.board;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/BoardDetailServlet")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		String no = request.getParameter("no");
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.boardDetailData(Integer.parseInt(no));
		
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
		
		out.write("<h1>내용 보기</h1>");
		out.write("<table class=table_main width=600>");
		
		out.write("<tr>");
		out.write("<th width=20%>번호</th>");
		out.write("<td width=30% align=center>"+vo.getNo()+"</td>");
		out.write("<th width=20%>작성일</th>");
		out.write("<td width=30% align=center>"+vo.getRegdate().toString()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=20%>이름</th>");
		out.write("<td width=30% align=center>"+vo.getName()+"</td>");
		out.write("<th width=20%>조회수</th>");
		out.write("<td width=30% align=center>"+vo.getHit()+"</td>");
		out.write("</tr>");

		out.write("<tr>");
		out.write("<th width=20%>제목</th>");
		out.write("<td colspan=3>"+vo.getSubject()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td colspan=4 align=left valign=top style=\"height:200px\"><pre>"+vo.getContent()+"</pre></td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td colspan=4 align=right>");
		out.write("<a href=#>수정</a>&nbsp");
		out.write("<a href=#>삭제</a>&nbsp");
		out.write("<a href=BoardListServlet>목록</a>&nbsp");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("</table>");
		
		out.write("</center>");
		out.write("</body>");
		out.write("</html>");
	}


}
