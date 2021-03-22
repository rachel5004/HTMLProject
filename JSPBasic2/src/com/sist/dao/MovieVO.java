package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	private int mno,cno,replyCount;
	private String poster,title,director,actor,regdate,genre,
			nation,grade,time,showUser,boxoffice,story,key;
	private double score;
	private String story2;

}