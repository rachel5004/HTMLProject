package com.sist.news;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
/*
 *   HTML = Jsoup (AJax => 소스보기에서 데이터를 출력하지 않는 경우:데이터를 수집할 수 없다) 
 *   XML = JAXB (클래스 <==> 태그) : 태그(클래스)
 *   JSON = getJSON() => Jquery(자바스크립스 라이브러리)를 이용한다
 *   ===== NODE.JS , Java 
 */
public class Channel {
    private List<Item> item=new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}
	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}
   
}
