package com.sist.news;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.*;
public class NewsManager {
	public List<Item> newsAllData(String fd)
	{
		List<Item> list1=new ArrayList<Item>();
        try
        {
        	/*URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="+URLEncoder.encode(data, "UTF-8"));*/
            String ssurl="http://newssearch.naver.com/search.naver?where=rss&query="+URLEncoder.encode(fd, "UTF-8");
        	/*JAXBContext jc=JAXBContext.newInstance(Rss.class);
            Unmarshaller un=jc.createUnmarshaller();
            Rss rss=(Rss)un.unmarshal(url);
            list1=rss.getChannel().getItem();*/
            /*for(Item i:list1)
            {
            	System.out.println("============");
            	System.out.println(i.getTitle());
                //System.out.println(i.getUrl());
            }*/
        	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        	DocumentBuilder db=dbf.newDocumentBuilder();
        	Document doc=db.parse(ssurl);
        	Element root=doc.getDocumentElement();
        	//NodeList kk=root.getElementsByTagName("channel");
        	//Element aaa=(Element)kk.item(0);
        	NodeList list=root.getElementsByTagName("item");
        	//List<String> sList=new ArrayList<String>();
        	String surl="";
        	String t="",l="",a="",d="",m="";
        	int k=0;
        	for(int i=2;i<list.getLength();i++)
        	{
        		//Element node=(Element)list.item(i);
        		try {
        		NodeList n=root.getElementsByTagName("title");
        		t=n.item(i).getFirstChild().getNodeValue();
        	    System.out.println(t);
        	    
        	    n=root.getElementsByTagName("link");
        		l=n.item(i).getFirstChild().getNodeValue();
        	    System.out.println(l);
        	    
        	    n=root.getElementsByTagName("author");
        		a=n.item(i).getFirstChild().getNodeValue();
        	    System.out.println(a);
        	    
        	    n=root.getElementsByTagName("description");
        		d=n.item(i).getFirstChild().getNodeValue();
        	    System.out.println(d);
       
        	    Item ii=new Item();
        	    ii.setTitle(t);
        	    ii.setAuthor(a);
        	    ii.setDescription(d);
        	    ii.setUrl(m);
        	    ii.setLink(l);
        	   try
        	   {
        	    n=root.getElementsByTagName("media:thumbnail");
        	    Element ee=(Element)n.item(i);
        		m=ee.getAttribute("url");
        	    System.out.println(m);
        	   }catch(Exception ex)
        	   {
        		   ii.setUrl("../images/bg.png");
        	   }
        	    list1.add(ii);
        		}catch(Exception ex){}
        		k++;
        		/*try
        		{
        		  Element elem=(Element)list.item(i);
        		
        		  surl=elem.getAttribute("url");
        		  
        		  list1.get(i).setUrl(surl);
        		}catch(Exception ex)
        		{
        			list1.get(i).setUrl("null");
        		}*/
        	}
        	
        	
        }catch(Exception ex){
        	System.out.println(ex.getMessage());
        	ex.printStackTrace();
        }
        return list1;
	}

}






