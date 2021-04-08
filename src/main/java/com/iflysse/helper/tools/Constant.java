package com.iflysse.helper.tools;

public interface Constant {
	/**
	 *  用户id-未知
	 */
	int ID_UNKNOW = -1;
	
	/**
	 * 学期id-当前
	 */
	int TERM_ID_CURRENT = -1;
	
	/**
	 * 用户状态-正常
	 */
	byte USER_STATE_NORMAL = 1;
	/**
	 * 用户状态-冻结
	 */
	byte USER_STATE_FREEZE = 0;
	/**
	 * 用户状态-未知
	 */
	byte USER_STATE_UNKNOW = 127;
	
	/**
	 * 用户权限-管理员
	 */
	byte USER_PERMISSION_ADMIN = 1;
	/**
	 * 用户权限-普通用户
	 */
	byte USER_PERMISSION_NORMAL = 0;
	/**
	 * 用户权限-经理
	 */
	byte USER_PERMISSION_MANAGER = 2;
	/**
	 * 用户权限-未知
	 */
	byte USER_PERMISSION_UNKNOW = 127;
	/**
	 * 用户性别-未知
	 */
	byte USER_GENDER_UNKNOW = 0;
	/**
	 * 用户性别-男
	 */
	byte USER_GENDER_BOY = 1;
	/**
	 * 用户性别-女
	 */
	byte USER_GENDER_GIRL = 2;
	
	/**
	 * 科目类型-方向课
	 */
	byte SUBJECT_TYPE_DIRECTION = 1;
	/**
	 * 科目类型-理论课
	 */
	byte SUBJECT_TYPE_THEORY = 0;
	
	/**
	 * 课程类型-线下-学练
	 */
	byte COURSE_TYPE_LEARN_AND_PRACTIVE = 1;
	/**
	 * 课程类型-授课-现场
	 */
	byte COURSE_TYPE_TEACHE = 0;
	
	/**
	 * 检测-id
	 */
	int CHECK_ID = 0B1;
	/**
	 * 检测-周报作者
	 */
	int CHECK_AUTHOR = 0B10;
	/**
	 * 检测-周报内容
	 */
	int CHECK_CONTENT = 0B100;
	/**
	 * 检测-周报提交状态
	 */
	int CHECK_SUBMIT = 0B1000;
	/**
	 * 检测-周报标题
	 */
	int CHECK_TITEL = 0B10000;
	/**
	 * 检测-用户名
	 */
	int CHECK_USERNAME = 0B1;
	/**
	 * 检测-用户密码
	 */
	int CHECK_PASSWORD = 0B10;
	/**
	 * 检测-用户真实姓名
	 */
	int CHECK_REALNAME = 0B100;
	/**
	 * 检测-用户手机
	 */
	int CHECK_PHONE = 0B1000;
	/**
	 * 检测-用户邮箱
	 */
	int CHECK_MAIL = 0B10000;
	/**
	 * 检测-学期
	 */
	int CHECK_TERM = 0B1;
	/**
	 * 检测-讲师
	 */
	int CHECK_TEACHER = 0B10;
	/**
	 * 检测-名称
	 */
	int CHECK_NAME = 0B100;
	/**
	 * 检测-类型
	 */
	int CHECK_TYPE = 0B1000;
	/**
	 * 检测-班级
	 */
	int CHECK_CLASS = 0B10000;
	/**
	 * 检测-时间
	 */
	int CHECK_TIME= 0B100000;
	/**
	 * 检测-所有字段
	 */
	int CHECK_ALL = Integer.MAX_VALUE;
	
	/**
	 * 字段类型-用户名
	 */
	byte FIELD_TYPE_USERNAME = 0;
	/**
	 * 字段类型-邮箱
	 */
	byte FIELD_TYPE_MAIL = 1;
	/**
	 * 字段类型-电话号码
	 */
	byte FIELD_TYPE_PHONE = 2;
}
