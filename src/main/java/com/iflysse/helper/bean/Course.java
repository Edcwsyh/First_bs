package com.iflysse.helper.bean;

import java.sql.Date;

import com.iflysse.helper.tools.Constant;

public class Course {
	private Integer id;
	/**
	 * 科目表的外键
	 */
	private Integer subject;
	/**
	 * 时间表的外键
	 */
	private Integer time;
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
	private Date specificTime;
	
	

	public Course(Integer id, Integer subject, Integer time, String goal, String content, Byte mode, Boolean isHomework,
			Date specificTime) {
		this.id = id;
		this.subject = subject;
		this.time = time;
		this.goal = goal;
		this.content = content;
		this.mode = mode;
		this.isHomework = isHomework;
		this.specificTime = specificTime;
	}
	
	public Course(Integer subject, Integer time, String goal, String content, Byte mode, Boolean isHomework,
			Date specificTime) {
		this.subject = subject;
		this.time = time;
		this.goal = goal;
		this.content = content;
		this.mode = mode;
		this.isHomework = isHomework;
		this.specificTime = specificTime;
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

	public Date getSpecificTime() {
		return specificTime;
	}

	public void setSpecificTime(Date specificTime) {
		this.specificTime = specificTime;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
	/**
	 * 用于检测对象中的某些属性是否为空, 检测参数应使用Constant中提供的check常量(CHECK_*)
	 * 若有多个检测字段则应使用"按位或(异或)运算"来传递参数, 列入检测id和状态是否为空 : Constant.CHECK_ID | Constant.CHECK_STATE
	 * 若所检测的字段为空则返回的结果对应参数, 例如id和状态都为空, 则返回值为 : Constant.CHECK_ID | Constant.CHECK_STATE
	 * 该函数可检测属性 : CHECK_ID, CHECK_SUBJECT, CHECK_TIME(时间表外键), CHECK_SPECIFIC_TIME(具体上课时间(年月日)), 
	 * 					 CHECK_MODE, CHECK_HOMEWORK, CHECK_ALL(所有属性)
	 * @param checkField
	 * @return
	 */
	public int check(int checkField) {
		int result = 0;
		if( (checkField & Constant.CHECK_ID) != 0 && id == null ) {
			result |= Constant.CHECK_ID;
		}
		if( (checkField & Constant.CHECK_SUBJECT) != 0 && subject == null ) {
			result |= Constant.CHECK_SUBJECT;
		}
		if( (checkField & Constant.CHECK_TIME) != 0 && time == null ) {
			result |= Constant.CHECK_TIME;
		}
		if( (checkField & Constant.CHECK_MODE) != 0 && mode == null ) {
			result |= Constant.CHECK_MODE;
		}
		if( (checkField & Constant.CHECK_HOMEWORK) != 0 && isHomework == null ) {
			result |= Constant.CHECK_HOMEWORK;
		}
		if( (checkField & Constant.CHECK_SPECIFIC_TIME) != 0 && specificTime == null ) {
			result |= Constant.CHECK_SPECIFIC_TIME;
		}
		return result;
	}
	
}
