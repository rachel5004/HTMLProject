package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BoardDAO {
	
	private Connection conn;
	private PreparedStatement ps;// PL/SQL CallableStatement(함수)
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
   // 목록 : 인라인뷰(paging) -> ArrayList<BoardVO>
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
   // 추가 : insert(sequence)
   public void boardInsert(BoardVO vo) {
	   try {
		   getConnection();
		   String sql="INSERT INTO webBoard VALUES "
		   		+ "(wb_no_seq.nextval,?,?,?,?,sysdate,0)";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, vo.getName());
		   ps.setString(2, vo.getSubject());
		   ps.setString(3, vo.getContent());
		   ps.setString(4, vo.getPwd());
		   ps.executeUpdate();
		   
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }finally {
		   disConnection();
	   }
   }
   // 수정 : update(SQL 1.비밀번호 확인 2.수정)
   public BoardVO boardUpdateBoardData(int no) {
	   BoardVO vo = new BoardVO();
	   try {
		   getConnection();
		   String sql="SELECT name,subject,content FROM webBoard WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   
		   ps.setInt(1,no);
		   ps.executeQuery();
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setName(rs.getString(1));
		   vo.setSubject(rs.getString(2));
		   vo.setContent(rs.getString(3));
		   rs.close();
		   
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }finally {
		   disConnection();
	   }
	   return vo;
   }
   public boolean boardUpdate(BoardVO vo) {
	   boolean bCheck=false;
	   try {
		   getConnection();
		   String sql="SELECT pwd FROM webBoard WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getNo());
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   if(rs.getString(1).equals(vo.getPwd())) {
			   sql="UPDATE webBoard SET name=?,subject=?,content=? WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, vo.getName());
			   ps.setString(2, vo.getSubject());
			   ps.setString(3, vo.getContent());
			   ps.setInt(4, vo.getNo());
			   ps.executeUpdate();
		   };
		   rs.close();
		   
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }finally {
		   disConnection();
	   }
	   return bCheck;
   }
   // 삭제 : delete -> 비밀번호 확인
   public boolean boardDelete(int no, String pwd) {
	   boolean bCheck = false;
	   try {
		   getConnection();
		   String sql="SELECT pwd FROM webBoard WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   if(rs.getString(1).equals(pwd)) {
			   bCheck=true;
			   sql="DELETE FROM webBoard WHERE no=?";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, no);
			   ps.executeUpdate();
		   };
		   rs.close();
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }finally {
		   disConnection();
	   }
	   return bCheck;
   }
   // 내용보기 : select
   public BoardVO boardDetailData(int no) {
	   BoardVO vo = new BoardVO();
	   try {
		   // 조회수 up
		   getConnection();
		   String sql="UPDATE webBoard SET hit=hit+1 WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ps.executeUpdate();
		   // 데이터 조회
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
   // 찾기 : like
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
