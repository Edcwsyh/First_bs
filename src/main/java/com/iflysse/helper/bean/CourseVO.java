package com.iflysse.helper.bean;

import java.sql.Date;

public class CourseVO extends Course {
	private Byte timeQuantum;
	private String classroom;
	
	public CourseVO(Integer id, Integer subject, Integer time, String goal, String content, Byte mode,
			Boolean isHomework, Date specificTime, Byte timeQuantum, String classroom) {
		super(id, subject, time, goal, content, mode, isHomework, specificTime);
		this.timeQuantum = timeQuantum;
		this.classroom = classroom;
	}

	public Byte getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(Byte timeQuantum) {
		this.timeQuantum = timeQuantum;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	public Byte getWeek() {
		return (byte) (timeQuantum / 10);
	}
	
	public Byte getHowTime() {
		return (byte) (timeQuantum % 10);
	}
	
}
