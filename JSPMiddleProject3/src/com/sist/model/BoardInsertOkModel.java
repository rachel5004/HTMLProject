package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class BoardInsertOkModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String msg = "게시판 데이터 추가 완료";
		request.setAttribute("msg", msg);
		return "board/insert_ok.jsp";
	}

}
