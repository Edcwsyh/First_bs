package com.iflysse.helper.bean;

public class TimeVO extends TimeBase{
	private String weeks;
	private Byte starWeek;
	private Byte endWeek;
	private Byte interval;
	private String addWeek;
	private String deleteWeek;

	public TimeVO(Integer id, Integer subject, Integer timeQuantum, String classroom, String weeks, Byte starWeek,
			Byte endWeek, Byte interval, String addWeek, String deleteWeek) {
		super(id, subject, timeQuantum, classroom);
		this.weeks = weeks;
		this.starWeek = starWeek;
		this.endWeek = endWeek;
		this.interval = interval;
		this.addWeek = addWeek;
		this.deleteWeek = deleteWeek;
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

	
	
}
