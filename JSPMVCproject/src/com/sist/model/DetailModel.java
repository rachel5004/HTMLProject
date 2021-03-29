package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DetailModel {
	public void handlerRequest(HttpServletRequest request) {
		String msg = "이름:홍길동,부서:개발부,급여:3000,근무지:서울";
		request.setAttribute("msg", msg);
	}
}
