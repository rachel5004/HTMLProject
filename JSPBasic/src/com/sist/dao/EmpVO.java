package com.sist.dao;
import java.util.*;
import lombok.Setter;
import lombok.Getter;
@Getter
@Setter
public class EmpVO {
	private int empno,mgr,sal,comm,deptno;
	private String ename,job;
	private Date hiredate;
}
