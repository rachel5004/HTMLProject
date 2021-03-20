package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;
import java.sql.*;

/*
 *                요청 내용이 request
 *     클라이언트 ==========> 톰캣  =====> list.jsp(list_jsp.java)
 *            요청(파일)                  컴파일 => 인터프리터 실행 (메모리:HTML)
 *  http://서버주소/board/list.jsp 
 * 
 * 
 */
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public BoardDAO(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	public void disConnection(){
		try{
			if(ps!=null) ps.close();
			if(conn!=null) ps.close();
		}catch(Exception ex) {}
	}
	
	  // 게시판 기능 
	  // 목록
	  public List<BoardVO> boardListData(int page)
	  {
		  // => ArrayList VS Vector : 예상문제 
		  List<BoardVO> list=new ArrayList<BoardVO>();
		  /*
		   *        List : 데이터의 중복허용 , 순서가 있다 (인덱스:0)
		   *         |
		   *       ===================
		   *       ArrayList , Vector , LinkedList
		   *       
		   *       1. 글올리기
		   *       3.  => ㄴㄴㄴ
		   *       5.  => ㅔㅔㅔ   
		   *            =>   
		   *       2. ㄴㄴㄴ
		   *       4.    => ㄴㄴㄴ
		   */
		  try {
			  getConnection();
			  String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
					    +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
					    +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
					    +"FROM jsReplyBoard ORDER BY group_id DESC,group_step ASC)) "
					    +"WHERE num BETWEEN ? AND ?";
			            // 페이징기법 => 인라인뷰 
			  ps=conn.prepareStatement(sql);
			  // ?에 값을 채운다 
			  int rowSize=10;
			  int start=(rowSize*page)-(rowSize-1); 
			  int end=rowSize*page;
			  /*
			   *    1page  1
			   *    2page  11
			   */
			  ps.setInt(1, start);
			  ps.setInt(2, end);
			  
			  // 실행
			  ResultSet rs=ps.executeQuery();
			  while(rs.next()) {
				  BoardVO vo=new BoardVO();
				  vo.setNo(rs.getInt(1));
				  vo.setSubject(rs.getString(2));
				  vo.setName(rs.getString(3));
				  vo.setRegdate(rs.getDate(4));
				  vo.setHit(rs.getInt(5));
				  vo.setGroup_tab(rs.getInt(6));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  } finally {
			  disConnection();
		  }
		  return list;
	  }
	  public int boardRowCount() {
		  int cnt=0;
		  try {
			  getConnection();
			  String sql = "SELECT COUNT(*) FROM jsReplyBoard";
			  
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  cnt = rs.getInt(1);
			  
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  } finally {
			  disConnection();
		  }
		  return cnt;
	  }
	  // 글쓰기
	  public void boardInsert(BoardVO vo) {
		  try {
			  getConnection();
			  String sql = "INSERT INTO jsReplyBoard(no,name,subject,content,pwd,group_id) "
			  		+ "VALUES(job_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) FROM jsReplyBoard))";
			  
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getName());
			  ps.setString(2, vo.getSubject());
			  ps.setString(3, vo.getContent());
			  ps.setString(4, vo.getPwd());
			  ps.executeUpdate();
			  
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  } finally {
			  disConnection();
		  }
	  }
	  // 내용보기 => SQL:2개
	  public BoardVO boardDetailData(int no,int type) {
		  BoardVO vo = new BoardVO();
		  try {
			  getConnection();
			  String sql = "";
			  if(type==1) {     // 조회
				  sql="UPDATE jsReplyBoard SET hit=hit+1 WHERE no=?";
				  ps=conn.prepareStatement(sql);
				  ps.setInt(1, no);
				  ps.executeUpdate();
			  }
			  // 수정
			  sql="SELECT no,name,subject,content,regdate,hit FROM jsReplyBoard WHERE no=?";
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
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  } finally {
			  disConnection();
		  }
		  
		  return vo;
	  }
	  // 답변 => SQL:4개 
	  // 수정
	  // 삭제 => SQL:4개
	  // 찾기
	  
}












