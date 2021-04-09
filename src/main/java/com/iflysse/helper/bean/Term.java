package com.iflysse.helper.bean;

import java.sql.Date;

import com.iflysse.helper.tools.Constant;

public class Term {

	private Integer id;
	/**
	 * 学期名称-非空
	 */
	private String name;
	/**
	 * 学期开始时间-非空
	 */
	private Date startTime;	
	/**
	 * 学期总周数-非空
	 */
	private Byte weeks;		
	/**
	 * 是否为当前学期-非空
	 */
	Boolean isCurrent;		
	
	public Term() {
	}

	public Term(String name, Date startTime, Byte weeks, Boolean isCurrent) {
		this.name = name;
		this.startTime = startTime;
		this.weeks = weeks;
		this.isCurrent = isCurrent;
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
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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
	
	/**
	 * 用于检测对象中的某些属性是否为空, 检测参数应使用Constant中提供的check常量(CHECK_*)	
	 * 若有多个检测字段则应使用"按位或(异或)运算"来传递参数, 列入检测id和状态是否为空 : Constant.CHECK_ID | Constant.CHECK_STATE	
	 * 若所检测的字段为空则返回的结果对应参数, 例如id和状态都为空, 则返回值为 : Constant.CHECK_ID | Constant.CHECK_STATE		
	 * 该函数可检测属性 : CHECK_ID, CHECK_USERNAME, CHECK_PASSWORD, CHECK_REALNAME, CHECK_PHONE, CHECK_MAIL, CHECK_ALL(所有属性)		
	 * @param checkField
	 * @return result
	 */
	public int check(int checkField) {
		int result = 0;
		if( (checkField & Constant.CHECK_ID) != 0 && id == null ) {
			System.out.println("id为空");
			result |= Constant.CHECK_ID;
		}
		if( (checkField & Constant.CHECK_NAME) != 0 && name == null ) {
			System.out.println("名称为空");
			result |= Constant.CHECK_NAME;
		}
		if( (checkField & Constant.CHECK_TIME) != 0 && startTime == null ) {
			System.out.println("时间为空");
			result |= Constant.CHECK_TIME;
		}
		if( (checkField & Constant.CHECK_CURRENT) != 0 && isCurrent == null ) {
			System.out.println("当前学期为空");
			result |= Constant.CHECK_CURRENT;
		}
		if( (checkField & Constant.CHECK_WEEKS) != 0 && weeks == null ) {
			System.out.println("周数为空");
			result |= Constant.CHECK_WEEKS;
		}
		return result;
	}
}
