package com.iflysse.helper.bean;

import java.sql.Date;

public class Term {

	private Integer id;
	/**
	 * 学期名称-非空
	 */
	private String name;
	/**
	 * 学期开始时间-非空
	 */
	private Date StartTime;	
	/**
	 * 学期总周数-非空
	 */
	private Byte weeks;		
	/**
	 * 是否为当前学期-非空
	 */
	Boolean isCurrent;		
	
	Term(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Byte getWeeks() {
		return weeks;
	}

	public void setWeeks(Byte weeks) {
		this.weeks = weeks;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	
}
