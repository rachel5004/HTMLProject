package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 * 이름      널?       유형            
------- -------- ------------- 
NO      NOT NULL NUMBER        
TITLE   NOT NULL VARCHAR2(100) 
SUBJECT NOT NULL VARCHAR2(100) 
POSTER  NOT NULL VARCHAR2(260) 
LINK             VARCHAR2(100) 
 * 
 */
@Getter
@Setter
public class FoodCategoryVO {
	private int no;
	private String title, subject, poster, link;
}
