package com.sist.dao;
import java.util.*;// �����͸� ��Ƽ� => �������� ����
import java.sql.*;//JDBC => �����ͺ��̽� ����
public class MovieDAO {

	private Connection conn;
	private PreparedStatement ps;// PL/SQL CallableStatement(�Լ�)
   	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   	public MovieDAO() {
   		try {
   			Class.forName("oracle.jdbc.driver.OracleDriver");
   		}catch(Exception ex){}
   	}
   	public void getConnection() {
   		try {
   			conn=DriverManager.getConnection(URL,"hr","happy");
   		}catch(Exception ex) {}
   	}
   public void disConnection() {
	   try {
		   if(ps!=null) ps.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex) {}
   }
   
   // 1. ��ȭ ��� ��� => 1������ 20���� ���  1���� MovieVO�� ���� => ArrayList<MovieVO>
   public ArrayList<MovieVO> movieListData(int page) {
	   ArrayList<MovieVO> list=new ArrayList<MovieVO>();//list=>MovieVO�� 20��
	   try {
		   getConnection();
		   String sql="SELECT mno,title,poster,director,num "
				     +"FROM (SELECT mno,title,poster,director,rownum as num "
				     +"FROM (SELECT mno,title,poster,director "
				     +"FROM movie ORDER BY mno ASC)) "
				     +"WHERE num BETWEEN ? AND ?"; // �ζ��κ� => ����¡ ���
		   ps=conn.prepareStatement(sql);

		   int rowSize=20;
		   int start=(page*rowSize)-(rowSize-1);
		   int end = page*rowSize;
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   /*
		    *    1page => 1~20     1*20-19 => 1
		    *             = ==
		    *    2page => 21~40     2*20-19 => 21  => rownum�� 1������ �����Ѵ� 
		    */
		   ResultSet rs=ps.executeQuery(); 
		   while(rs.next()) {
			   MovieVO vo=new MovieVO(); 

			   vo.setMno(rs.getInt(1));
			   vo.setTitle(rs.getString(2));
			   vo.setPoster(rs.getString(3));
			   vo.setDirector(rs.getString(4));
			   list.add(vo); 
		   }
		   rs.close();
	   }catch(Exception ex) {
		   // ���� ��� 
		   System.out.println(ex.getMessage());
	   }finally {
		   // ����
		   disConnection();
	   }
	   return list;
   }
   // 1-1. �������� ���Ѵ� 
   public int movieTotalPage() {
	   int total=0;
	   try {
		   getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM movie";// �ø��Լ� 1938/20.0
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();  
		   total=rs.getInt(1);
		   rs.close();
		   
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }
	   finally {
		   disConnection();
	   }
	   return total;
   }
   // 2. �󼼺��� 
   public MovieVO movieDetailData(int mno) {
	   MovieVO vo=new MovieVO();
	   try {
		   getConnection();
		   String sql="SELECT mno,poster,title,director,grade,genre,actor,regdate "
				     +"FROM movie "
				     +"WHERE mno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, mno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setMno(rs.getInt(1));
		   vo.setPoster(rs.getString(2));
		   vo.setTitle(rs.getString(3));
		   vo.setDirector(rs.getString(4));
		   vo.setGrade(rs.getString(5));
		   vo.setGenre(rs.getString(6));
		   vo.setActor(rs.getString(7));
		   vo.setRegdate(rs.getString(8));
		   rs.close();
	   }catch(Exception ex) {
		   // ���� ����
		   System.out.println(ex.getMessage());
	   }
	   finally {
		   disConnection();
	   }
	   return vo;
   }
}













