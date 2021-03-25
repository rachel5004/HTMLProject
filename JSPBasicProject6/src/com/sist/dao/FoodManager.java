package com.sist.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;

/*
 *      NO     NOT NULL NUMBER           
		TITLE   NOT NULL VARCHAR2(300) 
		SUBJECT NOT NULL VARCHAR2(300) 
		POSTER  NOT NULL VARCHAR2(260) 
		LINK    NOT NULL VARCHAR2(260) 
 */
public class FoodManager {
	private FoodDAO dao=new FoodDAO();
    public void foodCategoryData()
    {
    	try
    	{
    		int k=1;
    		// 연결 => 소스읽기 
    		Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
    		//System.out.println(doc);
    		Elements title=doc.select("div.top_list_slide span.title"); // CSS 선택자
    		Elements poster=doc.select("div.top_list_slide img.center-croping");
    		Elements subject=doc.select("div.top_list_slide p.desc");
    		Elements link=doc.select("div.top_list_slide a");
    		for(int i=0;i<title.size();i++)
    		{
    			System.out.println("번호:"+k);
    			System.out.println("제목:"+title.get(i).text());
    			System.out.println("부제목:"+subject.get(i).text());
    			System.out.println("이미지:"+poster.get(i).attr("data-lazy"));
    			System.out.println("링크:https://www.mangoplate.com"+link.get(i).attr("href"));
    			FoodCategoryVO vo=new FoodCategoryVO();
    			vo.setTitle(title.get(i).text());
    			vo.setSubject(subject.get(i).text());
    			vo.setPoster(poster.get(i).attr("data-lazy"));
    			vo.setLink("https://www.mangoplate.com"+link.get(i).attr("href"));
    			dao.foodCategoryInsert(vo);
    			
    			k++;
    		}
    	}catch(Exception ex){}
    }
    /*
     *   <div class="info">
                        
                        <span class="title ">
                          <a href="/restaurants/9qHLgnJtje"
                             onclick="trackEvent('CLICK_RESTAURANT', {&quot;position&quot;:0,&quot;restaurant_key&quot;:&quot;9qHLgnJtje&quot;})">
                            1.<h3> 영양족발순대국</h3>
                          </a>
                        </span>
     */
    public void foodDetailData()
    {
    	List<FoodCategoryVO> list=dao.foodCategoryLinkData();
    	try
    	{
    		for(FoodCategoryVO vo:list)
    		{
    			Document doc=Jsoup.connect(vo.getLink()).get();
    			Elements link=doc.select("div.info span.title a");
    			for(int i=0;i<link.size();i++)
    			{
    				System.out.println("https://www.mangoplate.com"+link.get(i).attr("href"));
    				Document doc2=Jsoup.connect("https://www.mangoplate.com"+link.get(i).attr("href")).get();
    				Elements info=doc2.select("table.info tr th");
    				Elements data=doc2.select("table.info tr td");
    				for(int j=0;j<info.size();j++)
    				{
    					//System.out.println(info.get(j).text());
    					String label=info.get(j).text();
    					if(label.equals("주소"))
    					{
    						System.out.println("주소:"+data.get(j).text());
    					}
    					else if(label.equals("전화번호"))
    					{
    						System.out.println("전화번호:"+data.get(j).text());
    					}
    					else if(label.equals("음식 종류"))
    					{
    						System.out.println("음식 종류:"+data.get(j).text());
    					}
    					else if(label.equals("가격대"))
    					{
    						System.out.println("가격대:"+data.get(j).text());
    					}
    					else if(label.equals("주차"))
    					{
    						System.out.println("주차:"+data.get(j).text());
    					}
    					else if(label.equals("영업시간"))
    					{
    						System.out.println("영업시간:"+data.get(j).text());
    					}
    					else if(label.equals("메뉴"))
    					{
    						System.out.println("메뉴:"+data.get(j).text());
    					}
    				}
    			}
    			System.out.println("==========="+vo.getNo()+"번 end=================");
    		}
    	}catch(Exception ex){}
    }
    public static void main(String[] args) {
		FoodManager fm=new FoodManager();
		//fm.foodCategoryData();
		fm.foodDetailData();
	}
}








