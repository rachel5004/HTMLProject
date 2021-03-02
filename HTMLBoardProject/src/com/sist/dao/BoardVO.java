package com.sist.dao;
/*
NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(34)   
SUBJECT NOT NULL VARCHAR2(2000) 
CONTENT NOT NULL CLOB           
PWD     NOT NULL VARCHAR2(10)   
REGDATE          DATE           
HIT              NUMBER  
 */
import java.util.*;

import lombok.*;
@Getter
@Setter
public class BoardVO {
	private int no,hit;
	private String name,subject,content,pwd;
	private Date regdate;
	
	

}
