package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 *  MNO      NOT NULL NUMBER(4)     
	TITLE             VARCHAR2(100) 
	GENRE             VARCHAR2(100) 
	POSTER            VARCHAR2(200) 
	ACTOR             VARCHAR2(300) 
	REGDATE           VARCHAR2(100) 
	GRADE             VARCHAR2(50)  
	DIRECTOR          VARCHAR2(100)
 */
@Setter
@Getter
public class MovieVO {
   private int mno;
   private String title;
   private String genre;
   private String poster;
   private String actor;
   private String regdate;
   private String grade;
   private String director;
   private int hit;
}
