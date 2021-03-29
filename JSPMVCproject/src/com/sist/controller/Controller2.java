package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
import java.util.*;



@WebServlet("/Controller2")
public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsmap = new HashMap();
	private String[] strCls = {
		"com.sist.model.DeleteModel",	
		"com.sist.model.UpdateModel",	
		"com.sist.model.DetailModel",	
		"com.sist.model.ListModel",	
	};
	private String[] strCmd = {"delete","update","detail","list"};
	@Override
	public void init(ServletConfig config) throws ServletException {
		// Model model = new ListModel();
		//clsmap.put("list", new ListModel());
		//clsmap.put("update", new UpdateModel());
		//clsmap.put("delete", new DeleteModel());
		//clsmap.put("detail", new DetailModel());
		try {
			int i=0;
			for(String cls:strCls) {
				Class clsName = Class.forName(cls);
				Object obj = clsName.newInstance();
				clsmap.put(strCmd[i], obj);
				i++;
			}
		}catch (Exception e) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getRequestURI();
		cmd = cmd.substring(cmd.lastIndexOf("/")+1,cmd.lastIndexOf("."));
//		System.out.println(cmd);
		Model model = (Model)clsmap.get(cmd);
		model.handlerRequest(request);
		RequestDispatcher rd = request.getRequestDispatcher("view/"+model+".jsp");
		rd.forward(request, response);
	}

}
