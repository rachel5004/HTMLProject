package com.sist.dao;
import java.util.*;// 데이터를 모아서 => 브라우저로 전송
import java.sql.*;//JDBC => 데이터베이스 연결
public class MovieDAO {

	private Connection conn;
	private PreparedStatement ps;// PL/SQL CallableStatement(함수)
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
   
   // 1. 영화 목록 출력 => 1페이지 20개를 출력  1개당 MovieVO에 저장 => ArrayList<MovieVO>
   public ArrayList<MovieVO> movieListData(int page) {
	   ArrayList<MovieVO> list=new ArrayList<MovieVO>();//list=>MovieVO를 20개
	   try {
		   getConnection();
		   String sql="SELECT mno,title,poster,director,num "
				     +"FROM (SELECT mno,title,poster,director,rownum as num "
				     +"FROM (SELECT mno,title,poster,director "
				     +"FROM movie ORDER BY mno ASC)) "
				     +"WHERE num BETWEEN ? AND ?"; // 인라인뷰 => 페이징 기법
		   ps=conn.prepareStatement(sql);

		   int rowSize=20;
		   int start=(page*rowSize)-(rowSize-1);
		   int end = page*rowSize;
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   /*
		    *    1page => 1~20     1*20-19 => 1
		    *             = ==
		    *    2page => 21~40     2*20-19 => 21  => rownum은 1번부터 시작한다 
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
		   // 에러 출력 
		   System.out.println(ex.getMessage());
	   }finally {
		   // 종료
		   disConnection();
	   }
	   return list;
   }
   // 1-1. 총페이지 구한다 
   public int movieTotalPage() {
	   int total=0;
	   try {
		   getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM movie";// 올림함수 1938/20.0
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
   // 2. 상세보기 
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
		   // 오류 점검
		   System.out.println(ex.getMessage());
	   }
	   finally {
		   disConnection();
	   }
	   return vo;
   }
}













