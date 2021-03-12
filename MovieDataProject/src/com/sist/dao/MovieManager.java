package com.sist.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class MovieManager {
   public void movieData() {
	   MovieDAO dao= new MovieDAO();
	   try {
		   String url="https://movie.daum.net/ranking/reservation";
		   int cno=1;
		   Document doc=Jsoup.connect(url).get();
		   Elements link=doc.select("div.thumb_cont strong.tit_item a.link_txt");
		   Elements regdate=doc.select("span.txt_info span.txt_num");
		   Elements grade=doc.select("span.txt_tag span.ico_movie");
		   Elements score=doc.select("span.txt_append span.txt_grade");
		   Elements poster=doc.select("div.poster_movie img.img_thumb");
		   Elements story=doc.select("div.poster_info a.link_story");
		   
		   for(int i=0;i<link.size();i++) {
			   try {
				   // HTML => 태그와 태그사이 <a>(값)</a> => text()
				   // <a 속성="값"> => attr(속성) ==> img , a 
				   
				   MovieVO vo = new MovieVO();
				   
				   if(link.get(i).text().equals("타락천사"))
					   continue;
				   
				   System.out.println("제목:"+link.get(i).text());
				   System.out.println("링크:"+link.get(i).attr("href"));
				   System.out.println("개봉일:"+regdate.get(i).text());
				   System.out.println("등급:"+grade.get(i).text());
				   System.out.println("평점:"+score.get(i).text());
				   System.out.println("포스터:"+poster.get(i).attr("src"));
				   System.out.println("줄거리:"+story.get(i).text());
				   
				   vo.setCno(cno);
				   vo.setTitle(link.get(i).text());
				   vo.setRegdate(regdate.get(i).text());
				   vo.setGrade(grade.get(i).text());
				   vo.setScore(Double.parseDouble(score.get(i).text()));
				   vo.setPoster(poster.get(i).attr("src"));
				   vo.setStory(story.get(i).text());
				   
				   Document doc2 = Jsoup.connect("https://movie.daum.net"
						   	+link.get(i).attr("href")).get();
				   Elements info1 = doc2.select("div.inner_cont dl.list_cont dt");
				   Elements info2 = doc2.select("div.inner_cont dl.list_cont dd");
				   for(int j=0;j<info1.size();j++) {
					   try {
						  String str = info1.get(j).text();
						   if(str.equals("장르")) {
							   System.out.println("장르:"+info2.get(j).text());
							   vo.setGenre(info2.get(j).text());
						   }else if(str.equals("국가")) {
							   System.out.println("국가:"+info2.get(j).text());
							   vo.setNation(info2.get(j).text());
						   }else if(str.equals("러닝타임")) {
							   System.out.println("러닝타임:"+info2.get(j).text());
							   vo.setTime(info2.get(j).text());
						   }else if(str.equals("누적관객")) {
							   System.out.println("누적관객:"+info2.get(j).text());
							   vo.setShowUser(info2.get(j).text());
						   }else if(str.equals("박스오피스")) {
							   System.out.println("박스오피스:"+info2.get(j).text());
							   vo.setBoxoffice(info2.get(j).text());
						   } 
					   }catch(Exception e) {}
					   
				   }
//				   System.out.println("동영상:"+youtubeGetKey(vo.getTitle()));
				   vo.setKey(youtubeGetKey(vo.getTitle()));
				   dao.movieInsert(vo);
				   
				   Thread.sleep(100);
				   System.out.println("========================================================");
			   }catch(Exception ex){}
		   }
	   }catch(Exception ex){}
   }
   public String youtubeGetKey(String title){
	   String key="";
	   try{
		   String url="https://www.youtube.com/results?search_query="+title;
		   Document doc=Jsoup.connect(url).get();
		   // /watch?v=bdcIC8d4nW0
		   Pattern p=Pattern.compile("/watch\\?v=[^가-힣]+");
		   /*
		    *  /watch?v=bdcIC8d4nW0","webPageType":"WEB_PAGE_TYPE_WATCH","rootVe":3832}
		    */
		   Matcher m=p.matcher(doc.toString());
		   while(m.find()) {
			   String str=m.group();// 찾은 문자열을 읽어 온다 
			   str=str.substring(str.indexOf("=")+1,str.indexOf("\""));
			   key=str;
			   break;
		   }
	   }catch(Exception ex){}
	   return key;
   }
   public static void main(String[] args) {
	    MovieManager m=new MovieManager();
	    m.movieData();
   }
}

