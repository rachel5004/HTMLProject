package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BoardDAO {
	
	private Connection conn;
	private PreparedStatement ps;// PL/SQL CallableStatement(�Լ�)
   	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   	public BoardDAO() {
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
   // ��� : �ζ��κ�(paging) -> ArrayList<BoardVO>
   public ArrayList<BoardVO> boardListData(int page){
	   ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	   try {
		   getConnection();
		   String sql="SELECT no,subject,name,regdate,hit,num "
				     +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
				     +"FROM (SELECT no,subject,name,regdate,hit "
				     +"FROM webBoard ORDER BY no DESC)) "
				     +"WHERE num BETWEEN ? AND ?";
		   ps=conn.prepareStatement(sql);

		   int rowSize=10;
		   int start=(page*rowSize)-(rowSize-1);
		   int end = page*rowSize;
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
	
		   ResultSet rs=ps.executeQuery(); 
		   while(rs.next()) {
			   BoardVO vo=new BoardVO();
			   vo.setNo(rs.getInt(1));
			   vo.setSubject(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setRegdate(rs.getDate(4));
			   vo.setHit(rs.getInt(5));
			   list.add(vo); 
		   }
		   rs.close();
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }finally {
		   disConnection();
	   }
	   return list;
   }
   public int boardRowCount() {
	   int cnt=0;
	   try {
		   getConnection();
		   String sql="SELECT COUNT(*) FROM webBoard";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();  
		   cnt=rs.getInt(1);
		   rs.close();
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }finally {
		   disConnection();
	   }
	   return cnt;
   }
   // �߰� : insert(sequence)
   // ���� : update(SQL 1.��й�ȣ Ȯ�� 2.����)
   // ���� : delete -> ��й�ȣ Ȯ
   // ���뺸�� : select
   public BoardVO boardDetailData(int no) {
	   BoardVO vo = new BoardVO();
	   try {
		   // ��ȸ�� up
		   getConnection();
		   String sql="UPDATE webBoard SET hit=hit+1 WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ps.executeUpdate();
		   // ������ ��ȸ
		   sql="SELECT no,name,subject,content,regdate,hit FROM webBoard WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next();  
		   vo.setNo(rs.getInt(1));
		   vo.setName(rs.getString(2));
		   vo.setSubject(rs.getString(3));
		   vo.setContent(rs.getString(4));
		   vo.setRegdate(rs.getDate(5));
		   vo.setHit(rs.getInt(6));
		   rs.close();
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }finally {
		   disConnection();
	   }
	   return vo;
   }
   // ã�� : like
   public ArrayList<BoardVO> boardFindData(String option,String word){
	   ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	   try {
		   getConnection();
		   String sql="SELECT no,subject,name,regdate,hit,num "
				     +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
				     +"FROM (SELECT no,subject,name,regdate,hit "
				     +"FROM webBoard ORDER BY no DESC)) "
				     +"WHERE ? like '%?%'";
		   ps=conn.prepareStatement(sql);

		   ps.setString(1, option);
		   ps.setString(2, word);
	
		   ResultSet rs=ps.executeQuery(); 
		   while(rs.next()) {
			   BoardVO vo=new BoardVO();
			   vo.setNo(rs.getInt(1));
			   vo.setSubject(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setRegdate(rs.getDate(4));
			   vo.setHit(rs.getInt(5));
			   list.add(vo); 
		   }
		   rs.close();
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }finally {
		   disConnection();
	   }
	   return list;
   }

}
