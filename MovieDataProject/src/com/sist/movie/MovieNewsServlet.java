package com.sist.movie;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.news.*;
@WebServlet("/MovieNewsServlet")
public class MovieNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 클라이언트 요청 => doGet()/doPost() => 화면 출력 (서비스)
	// JSP => 통합 (_jspService())
	// JSP => .jsp (_jspService()에 첨부되는 내용)
	// JSP가 실행이 되면 => 서블릿파일로 변경 => 컴파일=>인터프리터 => 브라우저에 출력
	// 서블릿(자바형식) , JSP (스크립트 => 간단한 프로그램)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 화면 출력 
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();// 요청한 클라이언트의 브러우저로 HTML을 전송
		// 출력하기 전에 출력할 데이터를 받는다 
		NewsManager nm=new NewsManager();
		List<Item> list=nm.newsListData();//네이버에서 읽어온 뉴스 (50개)
		// 브라우저에 출력 
		/*out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style>");
		out.println(".row{width:800px;margin:0px auto}");
		out.println("h3{text-align:center}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");*/
		out.println("<div class=container>");
		out.println("<h3>영화 뉴스</h3>");
		out.println("<div class=row>");
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		for(Item item:list) {
			out.println("<table class=\"table table-striped\">");
			out.println("<tr>");
			out.println("<td style=\"color:orange;font-size:12pt\"><a href="+item.getLink()+">"+item.getTitle()+"</a></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>"+item.getDescription()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td class=text-right>"+item.getAuthor()+"</td>");
			out.println("</tr>");
			out.println("</table>");
		}
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		/*out.println("</body>");
		out.println("</html>");*/
	}

}









