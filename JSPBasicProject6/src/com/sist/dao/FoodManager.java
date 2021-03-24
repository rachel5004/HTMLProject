package com.sist.dao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FoodManager {
	public void foodCategoryData() {
		FoodDAO dao = new FoodDAO();
		try {
			int k=1;
			Document doc = Jsoup.connect("https://www.mangoplate.com/").get();
//			Document doc = Jsoup.connect("https://www.mangoplate.com/restaurants/9qHLgnJtje").get();
//			System.out.println(doc);
			
			Elements title = doc.select("div.top_list_slide span.title");
			Elements poster = doc.select("div.top_list_slide img.center-croping");
			Elements subject = doc.select("div.top_list_slide p.desc");
			Elements link = doc.select("div.top_list_slide a");
			
			for(int i=0;i<title.size();i++) {
				FoodCategoryVO vo = new FoodCategoryVO();
				System.out.println("number: "+k);
				System.out.println("title: "+title.get(i).text());
				System.out.println("sub: "+subject.get(i).text());
				System.out.println("image: "+poster.get(i).attr("data-lazy"));
				System.out.println("link: https://www.mangoplate.com"+link.get(i).attr("href"));
				System.out.println("===============================");
				
				vo.setTitle(title.get(i).text());
				vo.setSubject(subject.get(i).text());
				vo.setPoster(poster.get(i).attr("data-lazy"));
				vo.setLink("https://www.mangoplate.com"+link.get(i).attr("href"));
				dao.foodCategoryInsert(vo);
				
				k++;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		FoodManager fm = new FoodManager();
		fm.foodCategoryData();
	}
}
