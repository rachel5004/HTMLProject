package com.sist.controller;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;

// 생성자: 메모리 할당 => init(): web.xml의 데이터 => service(): 사용자요청 처리 =>destroy(): 메모리 해제



@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap = new HashMap();
	public void init(ServletConfig config) throws ServletException {
		String path = config.getInitParameter("contextConfigLocation");
//		System.out.println(path);
		try {
			// read XML
			DocumentBuilderFactory dbf  = DocumentBuilderFactory.newDefaultInstance();
			// factory pattern -> 한번만 생성(싱글톤);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(path));
			// read table(beans)
			Element beans = doc.getDocumentElement();
//			System.out.println("root: "+beans.getTagName());
			NodeList list = beans.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++) {
				Element bean = (Element) list.item(i);
				String id = bean.getAttribute("id");
				String cls = bean.getAttribute("class");
//				System.out.println("id:"+id+" class:"+ cls);
				
				Class clsName = Class.forName(cls);  //클래스 이름 읽기 -> 전체 제어
				Object obj = clsName.getDeclaredConstructor().newInstance();
				// 클래스 이름으로 메모리 할당, 메소드 호출, 변수값 설정, 생성자 제어
				System.out.println("id:"+id+",메모리:"+obj);
				// 리플렉션
				clsMap.put(id, obj);
			}
		}catch (Exception e) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		cmd = cmd.substring(cmd.lastIndexOf("/")+1,cmd.lastIndexOf("."));
		Model model = (Model)clsMap.get(cmd);  // 모델의 클래스 찾기
		//method 호출
		String jsp = model.handlerRequest(request);
		// 지정된 jsp에 request 전송
		RequestDispatcher rd = request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}
}
