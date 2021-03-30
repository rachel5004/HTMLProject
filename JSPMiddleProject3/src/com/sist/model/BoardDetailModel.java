package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class BoardDetailModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String msg = "게시판 데이터 상세보기";
		request.setAttribute("msg", msg);
		return "board/detail.jsp";
	}

}
