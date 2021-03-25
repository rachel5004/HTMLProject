package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 *   	NO     NOT NULL NUMBER           
		TITLE   NOT NULL VARCHAR2(300) 
		SUBJECT NOT NULL VARCHAR2(300) 
		POSTER  NOT NULL VARCHAR2(260) 
		LINK    NOT NULL VARCHAR2(260) 
 */
@Setter
@Getter
public class FoodCategoryVO {
   private int no;
   private String title;
   private String subject;
   private String poster;
   private String link;
   
}
