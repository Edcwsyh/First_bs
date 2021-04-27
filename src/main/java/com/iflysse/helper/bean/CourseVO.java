package com.iflysse.helper.bean;

import java.sql.Date;

import com.iflysse.helper.tools.Constant;

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
	
	public String getDayOfWeek() throws Exception {
		switch ( timeQuantum / 10 ) {
			case 1 : return "一";
			case 2 : return "二";
			case 3 : return "三";
			case 4 : return "四";
			case 5 : return "五";
			case 6 : return "六";
			case 7 : return "日";
			default : throw new Exception("数据错误");
		}
	}
	
	public Byte getHowTime() {
		return (byte) (timeQuantum % 10);
	}
	
	/**
	 * 将整数时间段转换为字符形式
	 * @return
	 * @throws Exception 
	 */
	public String getTimeQuamtumString() throws Exception {
		switch ( getHowTime() ) {
			case 1 : case 2 : return "上午";
			case 3 : case 4 : return "下午";
			case 5 : case 6 : return "晚上";
			default : throw new Exception("传入参数不正确(合法范围为1-6)");
		}
	}
	
	/**
	 * 将整数时间段转换节次(小节)
	 * @return
	 * @throws Exception 
	 */
	public String getTimeCourseIndex() throws Exception {
		switch ( getHowTime() ) {
			case 1 : return "1,2";
			case 2 : return "3,4";
			case 3 : return "5,6";
			case 4 : return "7,8";
			case 5 : return "9,10";
			case 6 : return "11,12";
			default : throw new Exception("传入参数不正确(合法范围为1-6)");
		}
	}
	
}
