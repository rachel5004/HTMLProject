package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class SawonModel {
	public void sawonInit(HttpServletRequest request) {
		String name = "심청이";
		request.setAttribute("name", name);
	}
	public void sawonDetailData(HttpServletRequest request) {
		SawonVO vo = new SawonVO(1, "홍길동", "개발부");
		// vo => jsp 전송
		request.setAttribute("vo",vo);
	}
	public void sawonTwoData(HttpServletRequest request) {
		String firstName = "박";
		String lastName = "문수";
		
		request.setAttribute("first",firstName);
		request.setAttribute("last",lastName);
	}
}
