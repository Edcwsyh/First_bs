package com.iflysse.helper.bean;



import java.sql.Timestamp;

import com.iflysse.helper.tools.Constant;

public class Report {
	private Integer id;
	private Integer author;
	private String title;
	private String content;
	private Timestamp time;
	private Boolean isSubmit;
	
	public Report() {}
	
	public Report(Integer id, String content, Integer author, Timestamp time, Boolean isSubmit) {
		super();
		this.id = id;
		this.content = content;
		this.author = author;
		this.time = time;
		this.isSubmit = isSubmit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public String getTime() {
		return time.toLocaleString();
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	@Override
	public String toString() {
		return "Report [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content + ", time="
				+ time + ", isSubmit=" + isSubmit + "]";
	}

	/**
	 * 用于检测对象中的某些属性是否为空, 检测参数应使用Constant中提供的check常量(CHECK_*)
	 * 若有多个检测字段则应使用"按位或(异或)运算"来传递参数, 列入检测id和状态是否为空 : Constant.CHECK_ID | Constant.CHECK_STATE
	 * 若所检测的字段为空则返回的结果对应参数, 例如id和状态都为空, 则返回值为 : Constant.CHECK_ID | Constant.CHECK_STATE
	 * 该函数可检测属性 : CHECK_ID, CHECK_AUTHOR, CHECK_title, CHECK_SUBMIT, CHECK_ALL(所有属性)
	 * @param checkField
	 * @return
	 */
	public int check(int checkField) {
		int result = 0;
		if( (checkField & Constant.CHECK_ID) != 0 && id == null ) {
			result |= Constant.CHECK_ID;
		}
		if( (checkField & Constant.CHECK_AUTHOR) != 0 && author == null ) {
			result |= Constant.CHECK_AUTHOR;
		}
		if( (checkField & Constant.CHECK_TITLE) != 0 && title == null ) {
			result |= Constant.CHECK_TITLE;
		}
		if( (checkField & Constant.CHECK_SUBMIT) != 0 && isSubmit == null ) {
			result |= Constant.CHECK_SUBMIT;
		}
		return result;
	}
}