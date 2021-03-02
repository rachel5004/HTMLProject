package com.sist.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/BoardListServlet")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		BoardDAO dao=new BoardDAO();
		int cnt=dao.boardRowCount(); 
		int totalpage = (int)(Math.ceil(cnt/10.0));
		
		ArrayList<BoardVO> list=dao.boardListData(curpage);
		
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
		out.write("IP: "+request.getRemoteAddr());
		
		out.write("<h1>게시판</h1>");
		
		out.write("<table border=0 width=700>");
		out.write("<tr>");
		out.write("<td><a href=BoardInsertServlet>새 글</a></td>");
		out.write("</tr>");
		out.write("</table>");
		
		out.write("<table class=table_main width=700>");
		out.write("<tr>");
		out.write("<th width=10%>번호</th>");
		out.write("<th width=45%>제목</th>");
		out.write("<th width=15%>이름</th>");
		out.write("<th width=20%>작성일</th>");
		out.write("<th width=10%>조회수</th>");
		out.write("</tr>");
		// data 출력
		for(BoardVO vo:list) {
			out.write("<tr class=dataTr>");
			out.write("<td width=10% align=center>"+vo.getNo()+"</th>");
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String dbday = vo.getRegdate().toString();
			out.write("<td width=45%><a href=BoardDetailServlet?no="+vo.getNo()+">"+vo.getSubject()+"</th>");
			if(today.equals(dbday)) {
				out.println("<sup><font color=red>new</font></sup>");
			}
			out.write("<td width=15% align=center>"+vo.getName()+"</th>");
			out.write("<td width=20% align=center>"+vo.getRegdate().toString()+"</th>");
			out.write("<td width=10% align=center>"+vo.getHit()+"</th>");
			out.write("</tr>");
		}
		out.write("<table width=700>");
		out.write("<tr>");
		out.write("<td align=left>");
		out.write("Search: ");
		out.write("<select name=fs>");
		out.write("<option value=name>이름</option>");
		out.write("<option value=subject>제목</option>");
		out.write("<option value=content>내용</option>");
		out.write("</select>");
		out.write("<input type=text name=ss size=10>");
		out.write("<input type=submit value=검색>");
		out.write("</td>");
		
		out.write("<td align=right>");
		out.write("<a href=BoardListServlet?page="+(curpage>1?curpage-1:curpage)+" class=\"btn btn-sm btn-primary\">이전</a>&nbsp;");
		out.write(curpage+" page / "+totalpage+" pages&nbsp;");
		out.write("<a href=BoardListServlet?page="+(curpage<totalpage?curpage+1:curpage)+" class=\"btn btn-sm btn-primary\">다음</a>");
		out.write("</td>");
		out.write("</tr>");
		out.write("</table>");
		
		out.write("</center>");
		out.write("</body>");
		out.write("</html>");
		
	}

}
