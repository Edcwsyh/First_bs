package com.iflysse.helper.bean;

import com.iflysse.helper.tools.Constant;

public class TimeVO extends TimeBase{
	private String weeks;
	/**
	 * 必填, 开始周
	 */
	private Byte starWeek;
	/**
	 * 必填,结束周
	 */
	private Byte endWeek;
	/**
	 * 必填,上课间隔(周),默认为1
	 */
	private Byte interval;
	/**
	 * 选填,额外上课周
	 */
	private String addWeek;
	/**
	 * 选填,开始周和结束周之间不用上课的周
	 */
	private String deleteWeek;
	/**
	 * 必填, 上课时间(星期几)
	 */
	private Byte week;
	/**
	 * 必填,上课时间(一天中的第几大节课(1-6))
	 */
	private Byte howTime;

	public TimeVO(Integer id, Integer subject, String classroom, String weeks, Byte starWeek,
			Byte endWeek, Byte interval, String addWeek, String deleteWeek, Byte week, Byte howTime) {
		super(id, subject, classroom);
		this.weeks = weeks;
		this.starWeek = starWeek;
		this.endWeek = endWeek;
		this.interval = interval;
		this.addWeek = addWeek;
		this.deleteWeek = deleteWeek;
		this.week = week;
		this.howTime = howTime;
	}
	
	public TimeVO(Time time) {
		super(time.getId(), time.getSubject(), time.getClassroom() );
		weeks = time.getWeekString();
	}

	public Byte getStarWeek() {
		return starWeek;
	}

	public void setStarWeek(Byte starWeek) {
		this.starWeek = starWeek;
	}

	public Byte getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(Byte endWeek) {
		this.endWeek = endWeek;
	}

	public Byte getInterval() {
		return interval;
	}

	public void setInterval(Byte interval) {
		this.interval = interval;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getAddWeek() {
		return addWeek;
	}

	public void setAddWeek(String addWeek) {
		this.addWeek = addWeek;
	}

	public String getDeleteWeek() {
		return deleteWeek;
	}

	public void setDeleteWeek(String deleteWeek) {
		this.deleteWeek = deleteWeek;
	}

	public Byte getWeek() {
		return week;
	}

	public void setWeek(Byte week) {
		this.week = week;
	}

	public Byte getHowTime() {
		return howTime;
	}

	public void setHowTime(Byte howTime) {
		this.howTime = howTime;
	}
	
	/**
	 * 用于检测对象中的某些属性是否为空, 检测参数应使用Constant中提供的check常量(CHECK_*)
	 * 若有多个检测字段则应使用"按位或(异或)运算"来传递参数, 列入检测id和状态是否为空 : Constant.CHECK_ID | Constant.CHECK_STATE
	 * 若所检测的字段为空则返回的结果对应参数, 例如id和状态都为空, 则返回值为 : Constant.CHECK_ID | Constant.CHECK_STATE
	 * 该函数可检测属性 : CHECK_ID, CHECK_WEEKS, CHECK_WEEKS, CHECK_SUBMIT, CHECK_ALL(所有属性)
	 * @param checkField
	 * @return
	 */
	public int check(int checkField) {
		int result = 0;
		if( (checkField & Constant.CHECK_ID) != 0 && id == null ) {
			result |= Constant.CHECK_ID;
		}
		if( (checkField & Constant.CHECK_WEEKS) != 0 && (starWeek == null || endWeek == null) ) {
			result |= Constant.CHECK_WEEKS;
		}
		if( (checkField & Constant.CHECK_TIME_QUAUTUM) != 0 && ( week == null || howTime == null ) ) {
			result |= Constant.CHECK_TIME_QUAUTUM;
		}
		if( (checkField & Constant.CHECK_INTERVAL) != 0 && interval == null ) {
			result |= Constant.CHECK_INTERVAL;
		}
		return result;
	}
	
}
