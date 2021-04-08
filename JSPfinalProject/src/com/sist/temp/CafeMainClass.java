package com.sist.temp;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.helper.HttpConnection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.net.*;
public class CafeMainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
        	String fd="맛집";
        	String strUrl="http://newssearch.naver.com/search.naver?where=rss&query="
        			     +URLEncoder.encode(fd, "UTF-8");
        	/*URL url=new URL(strUrl);
        	HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        	StringBuffer sb=new StringBuffer();
        	if(conn!=null)
        	{
        		BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        		while(true)
        		{
        			String data=in.readLine();
        			if(data==null)
        				break;
        			sb.append(data+"\n");
        			System.out.println(data);
        		}
        		in.close();
        	}*/
        	
        	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        	DocumentBuilder db=dbf.newDocumentBuilder();
        	Document doc=db.parse(strUrl);
        	Element rss=doc.getDocumentElement();
        	//System.out.println(root.getTagName());
        	NodeList list=rss.getElementsByTagName("channel");
        	Element channel=(Element)list.item(0);
        	//System.out.println(channel.getTagName());
        	NodeList list2=channel.getElementsByTagName("item");
        	for(int i=0;i<list2.getLength();i++)
        	{
        		
        		
        	}
        }catch(Exception ex) {}
	}

}










