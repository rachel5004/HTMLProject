package com.sist.dao;
import java.util.*;//목록 출력(영화) => ArrayList=>브라우저에 전송 
import java.sql.*;//오라클 연동 
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
   // 2-1. 기능 설정 
   // 경우의 수 (ID가 틀린경우 , 비밀번호가 틀린 경우 , 로그인)
   public String isLogin(String id,String pwd){
	   String result="";
	   try {
		   getConnection();
		   String sql="SELECT COUNT(*) FROM webMember "
				     +"WHERE id=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, id);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   int count=rs.getInt(1);
		   rs.close();
		   
		   if(count==0) {
			   result="NOID"; // alert()
		   }else {
			   sql="SELECT pwd,name FROM webMember "
				  +"WHERE id=?";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, id);
			   rs=ps.executeQuery();
			   rs.next();
			   String db_pwd=rs.getString(1);
			   String name=rs.getString(2);
			   rs.close();
			   
			   if(db_pwd.equals(pwd)){
				   result=name; 
			   }else {
				   result="NOPWD";
			   }
		   }
		   
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }finally {
		   disConnection();
	   }
	   return result;
   }
   // 2) 영화 목록 
   public ArrayList<MovieVO> movieListData(int page) {
	   ArrayList<MovieVO> list=new ArrayList<MovieVO>();
	   try {
		   getConnection();
		   String sql="SELECT mno,title,poster,num "
				     +"FROM (SELECT mno,title,poster,rownum as num "
				     +"FROM (SELECT mno,title,poster "
				     +"FROM movie ORDER BY 1)) "
				     +"WHERE num BETWEEN ? AND ?";
		   int rowSize=20;
		   int start=(rowSize*page)-(rowSize-1);
		   int end =rowSize*page;
		   
		   ps=conn.prepareStatement(sql);
		   
		   ps.setInt(1, start);
		   ps.setInt(2, end);
		   
		   // 실행요청 => 결과값을 받는다 
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()) {
			  MovieVO vo=new MovieVO();
			  vo.setMno(rs.getInt(1));
			  vo.setTitle(rs.getString(2));
			  vo.setPoster(rs.getString(3));
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
   // 2-1) 총페이지 
   public int movieTotalPage() {
	   int total=0;
	   try {
		   getConnection();
		   String sql="SELECT CEIL(COUNT(*)/20.0) FROM movie";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   total=rs.getInt(1);
		   rs.close();
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }finally {
		   disConnection();
	   }
	   return total;
   }
   // 3) 영화 상세보기 (mno=> 사용자가 포스터를 클릭할때 넘겨준 번호) => 영화 1개에 대한 정보
   /*
    *   MNO      NOT NULL NUMBER(4)     
	TITLE             VARCHAR2(100) 
	GENRE             VARCHAR2(100) 
	POSTER            VARCHAR2(200) 
	ACTOR             VARCHAR2(300) 
	REGDATE           VARCHAR2(100) 
	GRADE             VARCHAR2(50)  
	DIRECTOR          VARCHAR2(100)
    */
   public MovieVO movieDetailData(int mno) {
	   MovieVO vo=new MovieVO();
	   try {
		   getConnection();
		   String sql="SELECT mno,title,poster,genre,actor,regdate,grade,director "
				     +"FROM movie WHERE mno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, mno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   vo.setMno(rs.getInt(1));
		   vo.setTitle(rs.getString(2));
		   vo.setPoster(rs.getString(3));
		   vo.setGenre(rs.getString(4));
		   vo.setActor(rs.getString(5));
		   vo.setRegdate(rs.getString(6));
		   vo.setGrade(rs.getString(7));
		   vo.setDirector(rs.getString(8));
		   rs.close();
	   }catch(Exception ex) {
		   System.out.println(ex.getMessage());
	   }finally {
		   disConnection();
	   }
	   return vo; 
   }
   // 3-1) 댓글이 많은 영화 
   public ArrayList<MovieVO> replyTop10() {
	   ArrayList<MovieVO> list=new ArrayList<MovieVO>();
	   try {
		   getConnection();
		   // 2. SQL문장 => 공지사항 , 이벤트 , 인기순위 .... rownum
		   String sql="SELECT title,poster,hit,rownum "
				     +"FROM (SELECT title,poster,hit "
				     +"FROM movie ORDER BY hit DESC) "
				     +"WHERE rownum<=10";
		   ps=conn.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()) {
			   MovieVO vo=new MovieVO();
			   vo.setTitle(rs.getString(1));
			   vo.setPoster(rs.getString(2));
			   vo.setHit(rs.getInt(3));
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
   // 4) 댓글 쓰기
   public void replyInsert(ReplyVO vo) {
	   try {
		   getConnection();
		   String sql="UPDATE movie SET hit=hit+1 WHERE mno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getMno());
		   ps.executeUpdate();
		   
		   sql="INSERT INTO webReply VALUES(wr_no_seq.nextval,?,?,?,?,SYSDATE)";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, vo.getMno());
		   ps.setString(2, vo.getId());
		   ps.setString(3, vo.getName());
		   ps.setString(4, vo.getMsg());
		   ps.executeUpdate();
		   
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }finally{
		   disConnection();
	   }
   }
   // 5) 댓글 삭제 
   public void replyDelete(int no){
	   try {
		   getConnection();
		   String sql="DELETE FROM webReply WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, no);
		   ps.executeUpdate();

	   }catch(Exception ex){
		   ex.printStackTrace();
	   }finally{
		   disConnection();
	   }
   }
   // 6) 댓글 수정 
   public void replyUpdate(int no,String msg){
	   try {
		   getConnection();
		   String sql="UPDATE webReply SET msg=? WHERE no=?";
		   ps=conn.prepareStatement(sql);
		   ps.setString(1, msg);
		   ps.setInt(2, no);
		   ps.executeUpdate();

	   }catch(Exception ex){
		   ex.printStackTrace();
	   }finally{
		   disConnection();
	   }
   }
   // 7) 댓글 읽기
   public ArrayList<ReplyVO> replyListData(int mno) {
	   ArrayList<ReplyVO> list=new ArrayList<ReplyVO>();
	   try {
		   getConnection();
		   String sql="SELECT no,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
				     +"FROM webReply WHERE mno=? ORDER BY no DESC";//최신 댓글
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, mno);
		   
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()){
			   ReplyVO vo=new ReplyVO();
			   vo.setNo(rs.getInt(1));
			   vo.setId(rs.getString(2));
			   vo.setName(rs.getString(3));
			   vo.setMsg(rs.getString(4));
			   vo.setDbday(rs.getString(5));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }finally{
		   disConnection();
	   }
	   return list;
   }
   // 8) 댓글 몇개인지 확인 
   public int replyCount(int mno) {
	   int cnt=0;
	   try {
		   getConnection();
		   String sql = "SELECT COUNT(*) FROM webReply WHERE mno=?";
		   ps=conn.prepareStatement(sql);
		   ps.setInt(1, mno);
		   ResultSet rs=ps.executeQuery();
		   rs.next();
		   cnt= rs.getInt(1);
		   rs.close();

	   }catch(Exception e){
		   e.printStackTrace();
	   }finally {
		   disConnection();
	   }
	return cnt;
   }
}
















