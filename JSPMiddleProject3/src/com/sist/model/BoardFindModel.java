package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class BoardFindModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String msg = "게시판 검색";
		request.setAttribute("msg", msg);
		return "board/find.jsp";
	}

}
