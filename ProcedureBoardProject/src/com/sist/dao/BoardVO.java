package com.sist.dao;
// => 게시물 한개에 대한 전체 => 오라클에 있는 컬럼과 동일 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
// 오라클 => %ROWTYPE
@Setter
@Getter
/*
 *    오라클         자바
 *    CHAR
 *    VARCHAR2
 *    CLOB         String
 *    ====================
 *    Number       (정수 : int   ,  실수 : double)   4.5
 *    =================================================
 *    DATE         java.util.Date
 *    =================================================
 *    BLOB
 *    BFILE        java.io.InputStream
 *    =================================================
 */
public class BoardVO {
  private int no;
  private String name;
  private String subject;
  private String content;
  private String pwd;
  private Date regdate;
  private int hit;
  private int like1;
}










