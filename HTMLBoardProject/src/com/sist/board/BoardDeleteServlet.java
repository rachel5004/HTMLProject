package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		
		String no=request.getParameter("no");
		
		out.write("<html>");
		out.write("<head>");
		out.println("<style type=text/css>");
		out.println("td,th{font-family:���� ���;font-size:9pt;height:35px}");
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
		out.write("<h1>�����ϱ�</h1>");
		
		out.write("<form method=post action=BoardDeleteServlet>");
		out.write("<table class=table_main width=350>");
		
		out.write("<tr>");
		out.write("<th width=30% align=right>��й�ȣ</th>");
		out.write("<td width=70%>");
		out.write("<input type=password name=pwd size=15>");
		out.write("<input type=hidden name=no value="+no+">");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td colspan=2 align=center>");
		out.write("<input type=submit value=����>");
		out.write("<input type=button value=���>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("</table>");
		out.write("</form>");		
		out.write("</center>");
		out.write("</body>");
		out.write("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out = response.getWriter();
		
		String no = request.getParameter("no");
		String pwd=request.getParameter("pwd");

		System.out.println("��ȣ : "+no);
		System.out.println("��й�ȣ : "+pwd);
		
		BoardDAO dao = new BoardDAO();
		boolean res = dao.boardDelete(Integer.parseInt(no),pwd);
		System.out.println(res);
		
		if(res==true) {
			response.sendRedirect("BoardListServlet");
		}else {
			out.println("<script type=text/javascript>");
			out.println("alert(\"��й�ȣ�� Ʋ���ϴ� �ٽ� �Է��ϼ���\");");
			out.println("history.back();");
			out.println("</script>");
		}
		
	}

}
