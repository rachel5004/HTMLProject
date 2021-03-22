package com.sist.dao;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

/*
 * 	NO           NUMBER(3)     
	TITLE        VARCHAR2(300) 
	SINGER       VARCHAR2(200) 
	ALBUM        VARCHAR2(200) 
	POSTER       VARCHAR2(260) 
	STATE        CHAR(6)       
	IDCREMENT    NUMBER(3)   
 */
@Setter
@Getter
public class MusicVO {
	private int no;
	private String title;
	private String singer;
	private String album;
	private String poster;
	private String state;
	private int idcrement;
}
