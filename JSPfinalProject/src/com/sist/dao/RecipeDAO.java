package com.sist.dao;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sist.vo.FoodVO;
import com.sist.vo.RecipeVO;

public class RecipeDAO {
	// 연결 객체
	   private Connection conn;
	   // SQL문장 전송 
	   private PreparedStatement ps;
	   // 미리 생성된 Connection객체 읽기
	   private static FoodDAO dao;
	   public void getConnection() {
		   try {
			   // JNDI초기화 : 탐색기를 연다
			   Context init=new InitialContext();
			   Context c=(Context)init.lookup("java://comp/env");
			   DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			   conn=ds.getConnection();
		   }catch(Exception ex) {}
	   }
	   // 사용후에 반환 
	   public void disConnection() {
		   try {
			   if(ps!=null) ps.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex){}
	   }
	   /* 	recipe list
	    	JSP(요청: ~.do) ==> DispatcherServlet => Model
	    					  	(controller) 
	    					  	=> 요청을 처리하는 자바클래스 찾기
	    					  	=> 처리한 결과값 받기
	    					  	=> 클라로 jsp전송
	   */						
	   public List<RecipeVO> recipeListData(int page){
		   List<RecipeVO> list = new ArrayList<RecipeVO>();
		   try {
			   getConnection();
			   String sql="SELECT no,poster,title,chef,hit,num "
					     +"FROM (SELECT no,poster,title,chef,hit,rownum as num "
					     +"FROM (SELECT no,poster,title,chef,hit "
					     +"FROM recipe ORDER BY no ASC))"
					     +"WHERE num BETWEEN ? AND ?";
			   ps=conn.prepareStatement(sql);
			   int rowSize = 12;
			   int start = (rowSize*page)-(rowSize-1);
			   int end = rowSize*page;
			   ps.setInt(1, start);
			   ps.setInt(2, end);
			   ResultSet rs=ps.executeQuery();
			   while(rs.next()) {
				   RecipeVO vo=new RecipeVO();
				   vo.setNo(rs.getInt(1));
				   vo.setPoster(rs.getString(2));
				   vo.setTitle(rs.getString(3));
				   vo.setChef(rs.getString(4));
				   vo.setHit(rs.getString(5));
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
}
