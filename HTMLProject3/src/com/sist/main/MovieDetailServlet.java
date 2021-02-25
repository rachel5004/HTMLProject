package com.sist.main;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
@WebServlet("/MovieDetailServlet")
public class MovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // ȭ�� ��� (����� ��û)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter(); // ����� ������
		// http://localhost/MovieDetailServlet?mno=1 => 1�� �޾Ƽ� => DAO�� ���� =>
		// DAO���� mno=1�� ���� ��� ������ �����ش� 
		// ����ڰ� ������ �����͸� �޴´� (String���� �޴´�)
		String mno=request.getParameter("mno");// DAO��û => MovieVO�� ���� ä���޶�
		MovieDAO dao=new MovieDAO();
		MovieVO vo=dao.movieDetailData(Integer.parseInt(mno));
		out.write("<html>");
		out.write("<head>");
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.write("</head>");
		out.write("<body>");
		out.write("<div class=container>");
		out.write("<div class=row>");
		out.write("<center>");
		out.write("<h1>��ȭ �󼼺���</h1>");
		out.write("<hr width=600>");
		out.write("<table width=600 class=table>");
		out.write("<tr>");
		out.write("<td width=30% align=center rowspan=6>");
		out.write("<img src="+vo.getPoster()+" width=200 height=280>");
		out.write("</td>");
		out.write("<td colspan=2><font size=10px>"+vo.getTitle()+"</font></td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td width=20% align=center>����</td>");
		out.write("<td width=50%>"+vo.getDirector()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td width=20% align=center>�⿬</td>");
		out.write("<td width=50%>"+vo.getActor()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td width=20% align=center>�帣</td>");
		out.write("<td width=50%>"+vo.getGenre()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td width=20% align=center>���</td>");
		out.write("<td width=50%>"+vo.getGrade()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td width=20% align=center>������</td>");
		out.write("<td width=50%>"+vo.getRegdate()+"</td>");
		out.write("</tr>");
		
		out.write("</table>");
		out.write("<hr width=600>");
		out.write("<table width=600 class=table>");
		out.write("<tr>");
		out.write("<td align=right>");
		out.write("<a href=#>����</a>&nbsp;");// &nbsp; => ���� 
		out.write("<a href=MovieListServlet>���</a>");
		out.write("</td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("<center>");
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
	}

}









