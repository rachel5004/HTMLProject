package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
 	NO        NUMBER         
	POSTER    VARCHAR2(260)  
	TITLE     VARCHAR2(1000) 
	CHEF      VARCHAR2(200)  
	HIT       VARCHAR2(100)  
	LINK      VARCHAR2(260)  
 */
@Getter
@Setter
public class RecipeVO {
	private int no;
	private String poster,title,chef,hit,link;

}
