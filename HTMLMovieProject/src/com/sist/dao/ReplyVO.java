package com.sist.dao;
/*
 *      NO      NOT NULL NUMBER       
		MNO              NUMBER       
		ID               VARCHAR2(20) 
		NAME             VARCHAR2(30) 
		MSG     NOT NULL CLOB         
		REGDATE          DATE  
 */

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ReplyVO {
   private int no;
   private int mno;
   private String id;
   private String name;
   private String msg;
   private Date regdate;// 시간을 가지고 올 수 없다  => TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS')
   private String dbday;
}











