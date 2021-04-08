package com.sist.vo;
/*
 *  no NUMBER,
  id VARCHAR2(20),
  title VARCHAR(200),
  day VARCHAR2(30),
  time VARCHAR2(10),
  inwon VARCHAR2(10),
  state NUMBER
 */

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ReserveVO {
   private int no;
   private String id;
   private String title;
   private String day;
   private String time;
   private String inwon;
   private int state;
   private Date regdate;
   private String dbday;
public int getNo() {
	return no;
}
public void setNo(int no) {
	this.no = no;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getInwon() {
	return inwon;
}
public void setInwon(String inwon) {
	this.inwon = inwon;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
public String getDbday() {
	return dbday;
}
public void setDbday(String dbday) {
	this.dbday = dbday;
}
   
}
