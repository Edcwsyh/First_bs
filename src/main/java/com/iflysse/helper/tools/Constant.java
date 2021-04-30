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
	 * 学期id-当前
	 */
	byte TERM_DEFAULT_WEEKS = 20;
	
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
	byte USER_PERMISSION_ADMIN = 0;
	/**
	 * 用户权限-普通用户
	 */
	byte USER_PERMISSION_NORMAL = 1;
	/**
	 * 用户权限-经理
	 */
	byte USER_PERMISSION_MANAGER = 2;
	/**
	 * 用户权限-未知
	 */
	byte USER_PERMISSION_UNKNOW = -1;
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
	 * 课程 - 单次课程的时长
	 */
	byte COURSE_LENGTH = 2;
	/**
	 * 单个页面所显示的数据数量
	 */
	byte PAGE_NUMBER = 10;
	/**
	 * 默认开始的页面
	 */
	String PAGE_START = "1";
	/**
	 * 所支持的最大周数
	 */
	byte MAX_WEEK = 32;
	
	/**
	 * 检测-id
	 */
	int CHECK_ID = 1 << 30;
	/**
	 * 检测-名称
	 */
	int CHECK_NAME = 1 << 29;
	/**
	 * 检测-时间
	 */
	int CHECK_TIME= 1 << 28;
	/**
	 * 检测-周数
	 */
	int CHECK_WEEKS= 1 << 27;
	/**
	 * 检测-科目(外键)
	 */
	int CHECK_SUBJECT= 1 << 26;
	/**
	 * 检测-周报作者
	 */
	int CHECK_AUTHOR = 1 << 1;
	/**
	 * 检测-周报内容
	 */
	int CHECK_CONTENT = 1 << 2;
	/**
	 * 检测-周报提交状态
	 */
	int CHECK_SUBMIT = 1 << 3;
	/**
	 * 检测-用户名
	 */
	int CHECK_USERNAME = 1 << 1;
	/**
	 * 检测-用户密码
	 */
	int CHECK_PASSWORD = 1 << 2;
	/**
	 * 检测-用户真实姓名
	 */
	int CHECK_REALNAME = 1 << 3;
	/**
	 * 检测-用户手机
	 */
	int CHECK_PHONE = 1 << 4;
	/**
	 * 检测-用户邮箱
	 */
	int CHECK_MAIL = 1 << 5;
	/**
	 * 检测-学期
	 */
	int CHECK_TERM = 1 << 1;
	/**
	 * 检测-讲师
	 */
	int CHECK_TEACHER = 1 << 2;
	/**
	 * 检测-类型
	 */
	int CHECK_TYPE = 1 << 3;
	/**
	 * 检测-班级
	 */
	int CHECK_CLASS = 1 << 4;
	/**
	 * 检测-当前学期
	 */
	int CHECK_CURRENT = 1;
	/**
	 * 检测-时间表时间段(表示一周的第几天 及 一天中的第几节课)
	 */
	int CHECK_TIME_QUAUTUM = 1 << 1;
	/**
	 * 检测-上课间隔(周数)
	 */
	int CHECK_INTERVAL = 1 << 2;
	/**
	 * 检测-上课具体时间
	 */
	int CHECK_SPECIFIC_TIME = 1;
	/**
	 * 检测-家庭作业
	 */
	int CHECK_HOMEWORK = 1 << 1;
	/**
	 * 检测-教学模式
	 */
	int CHECK_MODE = 1 << 2;
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
