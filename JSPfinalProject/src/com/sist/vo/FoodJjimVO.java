package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodJjimVO {
  private int no;
  private String id;
  private int cno;
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
public int getCno() {
	return cno;
}
public void setCno(int cno) {
	this.cno = cno;
}
  
}
