package com.iflysse.helper.tools;

public enum ResultCode{
	/**
	 * 请求成功
	 */
	SUCCESS(true, 20000, "请求成功"),
	/**
	 * 请求失败 - 未知的错误
	 */
	ERROR_UNKNOW(false, 20001, "未知错误"), 
	
	/**
	 * 请求失败 - 前端传入参数出错
	 */
	ERROR_PARAM(false, 21001, "错误的参数"),
	
	/**
	 * 请求失败 - 用户密码错误
	 */
	ERROR_USER_PASSWORD(false, 21010, "用户密码错误"), 
	/**
	 * 请求失败 - 用户不存在
	 */
	ERROR_USER_NOT_FOUND(false, 21011, "用户不存在"),
	/**
	 * 请求失败 - 用户已存在
	 */
	ERROR_USER_EXIST(false, 21012, "该用户已存在"),
	/**
	 * 请求失败 - 邮箱已存在
	 */
	ERROR_MAIL_EXIST(false, 21013, "该邮箱已被注册"),
	/**
	 * 请求失败 - 电话已存在
	 */
	ERROR_PHONE_EXIST(false, 21014, "该电话号码已被注册"),
	/**
	 * 请求失败 - 用户为登录
	 */
	ERROR_NOT_LOGIN(false, 21015, "用户未登录"),
	/**
	 * 请求失败 - 用户被冻结
	 */
	ERROR_USER_FREEZE(false, 21016, "用户被冻结"),
	/**
	 * 请求失败 - 用户修改密码时新旧密码一致
	 */
	ERROR_USER_SAME_OLD_NEW_PASSWORD(false, 21017, "用户新旧密码一致"),

	
	ERROR_SUBJECT_CREATE(false, 22000, "科目创建失败"),
	ERROR_SUBJECT_DELETE(false, 22001, "科目删除失败"),
	ERROR_SUBJECT_NOT_FOUND(false, 22002, "科目不存在"),
	
	/**
	 * 请求失败 - 当前学期已激活
	 */
	ERROR_TERM_ACTIVATE(false, 23000, "当前学期已激活"),
	/**
	 * 请求失败 - 当前学期已激活, 不可删除
	 */
	ERROR_TERM_DELETE_ACTION(false, 23001, "当前学期已激活"),
	/**
	 * 请求失败 - 学期未找到
	 */
	ERROR_TERM_NOT_FOUND(false, 23002, "未找到学期"),
	/**
	 * 请求失败 - 学期所属科目不为空
	 */
	ERROR_TERM_NOT_EMPTY(false, 23003, "当前学期被其他科目引用, 不可删除!"),
	
	/**
	 * 请求失败 - 周报内容为空
	 */
	ERROR_REPORT_CONTENT_EMPTY(false, 43000, "周报内容不能为空"),
	/**
	 * 请求失败 - 未找到周报
	 */
	ERROR_REPORT_NOT_FOUNT(false, 43001, "该周报不存在"),
	/**
	 * 请求失败 - 未找到周报
	 */
	ERROR_REPORT_ALREADY_SUBMIT(false, 43002, "该周报已提交"),
	
	/**
	 * 请求失败 - 权限不足
	 */
	ERROR_PERMISSION(false, 21100, "权限不足"),
	
	/**
	 * 请求失败 - 服务器发送错误
	 */
	ERROR_SERVER(false, 99999, "服务器发送错误!");
	
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
