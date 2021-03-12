package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MovieDAO {
	
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";

	public MovieDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex){}
	}
	public void getConnection(){
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
	
	public void movieInsert(MovieVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO daum_movie VALUES("
					+ "dm_mno_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, vo.getCno());
			ps.setString(2, vo.getPoster());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getRegdate());
			ps.setString(5, vo.getGenre());
			ps.setString(6, vo.getNation());
			ps.setString(7, vo.getGrade());
			ps.setString(8, vo.getTime());
			ps.setDouble(9, vo.getScore());
			ps.setString(10, vo.getShowUser());
			ps.setString(11, vo.getBoxoffice());
			ps.setString(12, vo.getStory());
			ps.setString(13, vo.getKey());
			
			ps.executeQuery();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			disConnection();
		}
	}
	   

}
