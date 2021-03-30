package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class BoardListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String msg = "게시판 목록";
		request.setAttribute("msg", msg);
		return "board/list.jsp";
	}

}
