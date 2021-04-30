package com.iflysse.helper.bean;

import com.iflysse.helper.controller.TermController;

public class TimeBase {
	protected Integer id;
	protected Integer subject;
	protected String classroom;
	
	public TimeBase() {
		id = null;
		subject = null;
		classroom = null;
	}
	
	public TimeBase(Integer id, Integer subject, String classroom) {
		super();
		this.id = id;
		this.subject = subject;
		this.classroom = classroom;
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

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	
	
}
