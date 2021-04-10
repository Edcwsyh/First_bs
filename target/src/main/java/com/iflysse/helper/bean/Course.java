package com.iflysse.helper.bean;

import java.sql.Date;

public class Course {
	private Integer id;
	/**
	 * 科目表的外键
	 */
	private Integer subject;
	/**
	 * 教学目标
	 */
	private String goal;
	/**
	 * 教学内容
	 */
	private String content;
	/**
	 * 教学模式
	 */
	private Byte mode;
	/**
	 * 家庭作业
	 */
	private Boolean isHomework;
	/**
	 * 上课的具体时间
	 */
	private Date time;
	
	public Course(Integer id, Integer subject, String goal, String content, Byte mode, Boolean isHomework, Date time) {
		this.id = id;
		this.subject = subject;
		this.goal = goal;
		this.content = content;
		this.mode = mode;
		this.isHomework = isHomework;
		this.time = time;
	}
	
	public Course(Integer subject, String goal, String content, Byte mode, Boolean isHomework, Date time) {
		this.subject = subject;
		this.goal = goal;
		this.content = content;
		this.mode = mode;
		this.isHomework = isHomework;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte getMode() {
		return mode;
	}

	public void setMode(Byte mode) {
		this.mode = mode;
	}

	public Boolean getIsHomework() {
		return isHomework;
	}

	public void setIsHomework(Boolean isHomework) {
		this.isHomework = isHomework;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
