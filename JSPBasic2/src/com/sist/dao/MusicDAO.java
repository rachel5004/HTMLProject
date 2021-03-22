package com.sist.dao;
import java.sql.*;
import java.util.*;

public class MusicDAO {
	  private Connection conn;
	  private PreparedStatement ps;
	  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	  public MusicDAO()
	  {
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch(Exception ex) {}
	  }
	  public void getConnection()
	  {
		  try
		  {
			  conn=DriverManager.getConnection(URL,"hr","happy");
		  }catch(Exception ex) {}
	  }
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) ps.close();
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
	  }
	
	// 기능
	public ArrayList<MusicVO> musicListData()
	{
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		try
		{
			getConnection();
			String sql="SELECT no,title,singer,poster,rownum "
					+ "FROM music_genre "
					+ "WHERE rownum<=20";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				MusicVO vo=new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setPoster(rs.getString(4));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
	// 상세보기
	public MusicVO musicDetailData(int no)
	{
		MusicVO vo=new MusicVO();
		try
		{
			getConnection();
			String sql="SELECT no,title,singer,poster,album,state,idcrement "
					+ "FROM music_genre "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setPoster(rs.getString(4));
				vo.setAlbum(rs.getString(5));
				vo.setState(rs.getString(6));
				vo.setIdcrement(rs.getInt(7));
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
}
