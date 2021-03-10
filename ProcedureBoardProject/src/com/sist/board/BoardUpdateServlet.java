package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
@WebServlet("/BoardUpdateServlet")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // HTML실행 요청 
		response.setContentType("text/html;charset=UTF-8");
		// HTML을 작성 (메모리에 작성) => TCP
		PrintWriter out=response.getWriter();
		// 1. 사용자가 보내준 데이터 받기 
		String no=request.getParameter("no");
		// 2. 오라클에서 데이터를 가지고 온다 
		BoardDAO dao=new BoardDAO();
		BoardVO vo=dao.board_updateData(Integer.parseInt(no));
		// 3. HTML에 추가해서 데이터를 보여준다 
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=stylesheet href=\"css/table.css\">");
		out.println("<style type=text/css>");
		out.println("td,th{font-family:맑은 고딕;font-size:9pt;height:35px}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>수정하기</h1>");
		// 입력된 모든 데이터를 doPost로 전송 => <form>
		out.println("<form method=post action=BoardUpdateServlet autocomplete=off>");
		// 입력창을 이용해서 => 글쓰기가 가능하게 만들어 준다 
		out.println("<table class=table-content width=600>");
		// 이름입력
		out.println("<tr>");
		out.println("<th width=20% align=right>이름</th>");
		out.println("<td width=80%>");
		out.println("<input type=text name=name size=15>");
		out.println("</td>");
		out.println("</tr>");
		// 제목입력
		out.println("<tr>");
		out.println("<th width=20% align=right>제목</th>");
		out.println("<td width=80%>");
		out.println("<input type=text name=subject size=50>");
		out.println("</td>");
		out.println("</tr>");
		// 내용입력
		out.println("<tr>");
		out.println("<th width=20% align=right>내용</th>");
		out.println("<td width=80%>");
		out.println("<textarea rows=10 cols=55 name=content></textarea>");
		out.println("</td>");
		out.println("</tr>");
		
		// 비밀번호
		out.println("<tr>");
		out.println("<th width=20% align=right>비밀번호</th>");
		out.println("<td width=80%>");
		out.println("<input type=password name=pwd size=15>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit value=수정>");
		out.println("<input type=button value=취소 onclick=\"javascript:history.back()\">");
		// HTML / CSS / JavaScript
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}










