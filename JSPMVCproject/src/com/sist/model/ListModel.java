package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
public class ListModel {
	public void handlerRequest(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		list.add("이순신");
		list.add("강감찬");
		
		request.setAttribute("list", list);
	}
}
