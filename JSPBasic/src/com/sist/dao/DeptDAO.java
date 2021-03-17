package com.sist.dao;
import java.util.*;
import java.sql.*;
public class DeptDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String USERNAME = "hr";
	private final String PASSWORD = "happy";
	public DeptDAO() {
		try {
			Class.forName(DRIVER);
		}catch(Exception e){}
	}
	public void getConnection(){
		try {
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch(Exception e) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {}
	}
	public ArrayList<DeptVO> deptListData(){
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		try {
			getConnection();
			String sql = "SELECT * FROM dept";
			ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				DeptVO vo = new DeptVO();
				vo.setDeptno(rs.getInt(1));
				vo.setDname(rs.getString(2));
				vo.setLoc(rs.getString(3));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}

}
