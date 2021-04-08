package com.iflysse.helper.bean;

public enum ResultCode{
	SUCCESS(true, 20000, "请求成功"),
	ERROR_UNKNOW(false, 20001, "未知错误"), 
	
	ERROR_BAD_SQL(false, 21001, "错误的SQL语句"),
	ERROR_PARAM(false, 21002, "错误的参数"),
	
	ERROR_PASSWORD(false, 21010, "用户密码错误"), 
	ERROR_USER_NOT_FOUND(false, 21011, "用户不存在"),
	ERROR_USER_EXIST(false, 21012, "用户已存在"),
	ERROR_NOT_LOGIN(false, 21013, "用户未登录"),
	ERROR_USER_FREEZE(false, 21014, "用户被冻结"),
	ERROR_USERNAME_PASSWORD(false, 21015, "用户名或密码错误"),
	
	ERROR_SUBJECT_CREATE(false, 22000, "科目创建失败"),
	ERROR_SUBJECT_DELETE(false, 22001, "科目删除失败"),
	ERROR_SUBJECT_NOT_FOUND(false, 22002, "科目不存在"),
	
	ERROR_TERM_ACTIVATE(false, 23000, "当前学期已激活"),
	ERROR_TERM_NOT_FOUND(false, 23001, "未找到学期"),
	ERROR_TERM_NOT_DATA(false, 23002, "不存在任何学期数据"),
	
	ERROR_PERMISSION(false, 21100, "权限不足");
	
	private boolean success;
	private Integer code;
	private String message;
	
	ResultCode(Boolean success, Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public String getMessage(){
		return message;
	}
	
	public Integer getCode(){
		return code;
	}
}
