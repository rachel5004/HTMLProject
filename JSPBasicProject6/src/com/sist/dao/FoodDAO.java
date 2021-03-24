package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.jdbc.*;
public class FoodDAO {
	  private DAOManager dm = new DAOManager();
	  private Connection conn;
	  private PreparedStatement ps;
	  public void foodCategoryInsert(FoodCategoryVO vo) {
		  try {
			  conn = dm.getConnection();
			  String sql = "INSERT INTO food_category VALUES("
			  		+ "(SELECT NVL(MAX(no)+1,1) FROM food_category),?,?,?,?)";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getTitle());
			  ps.setString(2, vo.getSubject());
			  ps.setString(3, vo.getPoster());
			  ps.setString(4, vo.getLink());
			  
			  ps.executeUpdate();
		  }catch (Exception e) {
			  e.printStackTrace();
		  }finally {
			  dm.disConnection(conn,ps);
		  }
	  }
	  public List<FoodCategoryVO> foodCategoryData(int index){
		  List<FoodCategoryVO> list = new ArrayList<FoodCategoryVO>();
		  try {
			  conn = dm.getConnection();
			  int start = 0;
			  int end = 0;
			  if (index==1) {
				  start=1;
				  end=12;
			  }else if(index==2) {
				  start=13;
				  end=18;
			  }else {
				  start=19;
				  end=30;
			  }
			  String sql = "SELECT * FROM food_category WHERE no BETWEEN ? AND ?";
			  
			  ps = conn.prepareStatement(sql);
			  ps.setInt(1, start);
			  ps.setInt(2, end);
			  
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  FoodCategoryVO vo = new FoodCategoryVO();
				  vo.setNo(rs.getInt(1));
				  vo.setTitle(rs.getString(2));
				  vo.setSubject(rs.getString(3));
				  vo.setPoster(rs.getString(4));
				  vo.setLink(rs.getString(5));
				  list.add(vo);
			  }
			  rs.close();
		  }catch (Exception e) {
			  e.printStackTrace();
		  }finally {
			dm.disConnection(conn, ps);  
		  }
		  return list;
	  }
}
