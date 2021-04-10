package com.iflysse.helper.bean;

public interface Macro {
	int USER_ID_UNKNOW = -1;//用户id-未知
	
	int TERM_ID_CURRENT = -1;//学期id-当前
	
	byte USER_STATE_NORMAL = 1;//用户状态-正常
	byte USER_STATE_FREEZE = 0;//用户状态-冻结
	byte USER_STATE_UNKNOW = 127;//用户状态-未知
	
	byte USER_PERMISSION_ADMIN = 1;//用户权限-管理员
	byte USER_PERMISSION_NORMAL = 0;//用户权限-普通用户
	byte USER_PERMISSION_MANAGER = 2;//用户权限-经理
	byte USER_PERMISSION_UNKNOW = 127;//用户权限-未知
	
	byte SUBJECT_TYPE_DIRECTION = 1;//科目类型-方向课
	byte SUBJECT_TYPE_THEORY = 0;//科目类型-理论课
	
	byte COURSE_TYPE_LEARN_AND_PRACTIVE = 1;//课程类型-线下-学练
	byte COURSE_TYPE_TEACHE = 0;//课程类型-授课-现场
	
	byte FIELD_TYPE_USERNAME = 0;//字段类型-用户名
	byte FIELD_TYPE_MAIL = 1;//字段类型-邮箱
	byte FIELD_TYPE_PHONE = 2;//字段类型-电话号码
}
