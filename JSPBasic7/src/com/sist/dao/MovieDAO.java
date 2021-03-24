package com.sist.dao;
import java.util.*;
import javax.sql.*;

import com.sun.net.httpserver.Authenticator.Result;

import java.sql.*;
import javax.naming.*;
public class MovieDAO {
	private Connection conn;
	private PreparedStatement ps;
	public void getConnection() {
		try {
		Context init = new InitialContext();
		Context c = (Context)init.lookup("java://comp/env");
		DataSource ds = (DataSource) c.lookup("jdbc/oracle");
		conn = ds.getConnection();
		}catch (Exception e) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public List<MovieBean> movieListData(int cno){
		List<MovieBean> list = new ArrayList<MovieBean>();
		try {
			getConnection();
			String sql = "SELECT mno,poster,title FROM daum_movie "
					+ "WHERE cno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setMno(rs.getInt(1));
				bean.setPoster(rs.getString(2));
				bean.setTitle(rs.getString(3));
				list.add(bean);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			disConnection();
		}
		return list;
	}
	public MovieBean movieDetailData(int mno) {
		MovieBean bean = new MovieBean();
		try {
			getConnection();
			String sql = "SELECT mno,poster,title,regdate,nation,grade,time,showUser,"
					+ "boxoffice,story,key,score FROM daum_movie WHERE cno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			bean.setMno(rs.getInt(1));
			bean.setPoster(rs.getString(2));
			bean.setTitle(rs.getString(3));
			bean.setRegdate(rs.getString(4));
		    bean.setGenre(rs.getString(5));
		    bean.setNation(rs.getString(6));
		    bean.setGrade(rs.getString(7));
		    bean.setTime(rs.getString(8));
		    bean.setScore(rs.getDouble(9));
		    bean.setShowUser(rs.getString(10));
		    bean.setBoxoffice(rs.getString(11));
		    bean.setStory(rs.getString(12));
		    bean.setKey(rs.getString(13));
		    rs.close();
		    
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			disConnection();
		}
		return bean;
	}
}
