package com.sist.dao;
// 오라클 연결 
import java.sql.*; 
import java.util.*;
import oracle.jdbc.*;
public class BoardDAO {
   // 1. 오라클 연결 객체
	private Connection conn;
   // 2. SQL문장 전송 , 함수 호출 
	private CallableStatement cs;// 프로시저 호출시 사용되는 클래스
   // 3. 오라클 URL주소 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
   // 4. 오라클 연결 드라이버 설치 
	public BoardDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	// 5. 오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	// 6. 오라클 닫기
	public void disConnection()
	{
		try
		{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	// 7. 기능 
	// 7-1 . 목록 출력 
	/*
	 *      CREATE OR REPLACE PROCEDURE board_list(
			    pStart freeboard.no%TYPE,
			    pEnd freeboard.no%TYPE,
			    pResult OUT SYS_REFCURSOR
			)
	 */
	 // BoardVO(한개의 게시물) => 게시물 여러개를 모아서 브라우저로 전송 
	 public ArrayList<BoardVO> board_list(int page)
	 {
		 ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		 try
		 {
			 // 1. 연결
			 getConnection();
			 // 2. SQL문장을 만든다 
			 String sql="{CALL board_list(?,?,?)}";
			 cs=conn.prepareCall(sql);
			 // ?에 값을 채운후에 실행 
			 int rowSize=10;
			 int start=(rowSize*page)-(rowSize-1);
			 int end=rowSize*page;
			 /*
			  *   1pgae 1 - 10
			  *   2page 11 - 20
			  */
			 cs.setInt(1, start);
			 cs.setInt(2, end);
			 cs.registerOutParameter(3, OracleTypes.CURSOR);
			 //   => 오라클의 데이터형을 설정 
			 /*
			  *   SYS_REFCURSOR => OracleTypes.CURSOR
			  *   VARCHAR2      => OracleTypes.VARCHAR
			  *   NUMBER        => OracleTypes.INTEGER
			  */
			 // 실행 요청 
			 cs.executeUpdate(); // procedure => INSERT,UPDATE,DELETE,SELECT 
			                     // cs.executeUpdate() : 함수 호출 
			 ResultSet rs=(ResultSet)cs.getObject(3);// 저장된 데이터를 받는다 
			 /*
			  *     cs.getString(index)
			  *     cs.getInt()
			  *     cs.getDate()
			  *     CURSOR => cs.getObject() => 자바에서 존재하는 데이터형이 아니다 
			  */
			 while(rs.next())
			 {
				 // no,subject,name,regdate,hit
				 BoardVO vo=new BoardVO();
				 vo.setNo(rs.getInt(1));
				 vo.setSubject(rs.getString(2));
				 vo.setName(rs.getString(3));
				 vo.setRegdate(rs.getDate(4));
				 vo.setHit(rs.getInt(5));
				 
				 list.add(vo);
			 }
			 rs.close();
			 
		 }catch(Exception ex)
		 {
			 // 오류 처리
			 ex.printStackTrace();
		 }
		 finally
		 {
			 disConnection();
		 }
		 return list;
	 }
	 // 7-2. 총페이지 가지고 오기 
	 /*
	  *   CREATE OR REPLACE PROCEDURE board_totalpage(
              pTotal OUT NUMBER
          )
	  */
	 public int board_totalpage()
	 {
		  int total=0;
		  try
		  {
			  // 1. 연결
			  getConnection();
			  // 2. SQL문장 
			  String sql="{CALL board_totalpage(?)}";
			  cs=conn.prepareCall(sql);
			  // ?에 값을 채운다 
			  cs.registerOutParameter(1, OracleTypes.INTEGER);
			  // 실행
			  cs.executeUpdate();
			  total=cs.getInt(1);
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return total;
	 }
	
}










