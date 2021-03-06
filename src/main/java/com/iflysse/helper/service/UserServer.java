package com.iflysse.helper.service;

import java.util.List;

import com.iflysse.helper.bean.User;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

public interface UserServer {
	/**
	 * 获取用户的基本信息
	 * @param user {id，用户名，邮箱，电话号码，性别，真实姓名}
	 * @return
	 */
	/**
	 * @param field 可选参数为：用户名、邮箱、电话号码，应使用Constant.FIELD_*传入
	 * @param value 匹配的字符串
	 * @return
	 */
	public User get_user(Byte field, String value);
	/**
	 * 更新用户的基本信息
	 * @param user {用户名，邮箱，电话号码，性别，真实姓名}
	 * @return
	 */
	public Result<Void> user_update_info(User user);
	/**
	 * 更新用户的密码
	 * @param user {密码}
	 * @return
	 */
	public Result<Void> user_update_password(User user);
	/**
	 * 获取用户的所有信息
	 * @param userId
	 * @return
	 */
	public Result<User> user_all_info(Integer userId);
	/**
	 * 更新用户的所有信息
	 * @param user
	 * @return
	 */
	public Result<Void> user_update_all_info(User user);
	/**
	 * 添加用户，该接口仅允许管理员调用，能够为用户设置所有属性
	 * @param user
	 * @return
	 */
	public Result<Void> user_add(User user);
	/**
	 * 获取用户列表
	 * @return
	 */
	public Result< List<User> > user_list();
	/**
	 * 删除某一用户
	 * @param userId 必须是未被其他数据引用的用户
	 * @return
	 */
	public Result<Void> user_delete(Integer userId);
	/**
	 * 通过id获取用户信息
	 * @param id
	 * @return
	 */
	public User get_user_by_id(Integer id);
	/**
	 * 通过用户名(u)、邮箱(m)、电话号码(p)查询用户
	 * @param user
	 * @return
	 */
	public List<User> get_user_by_ump(User user);
	/**
	 * 插入一个新的用户
	 * @param user
	 */
	public void insert_user(User user);
	/**
	 * 更新用户的状态
	 * @param userId
	 * @param state 用户状态（应通过Constant中的宏常量设置）
	 */
	public void update_user_state(Integer userId, byte state);
}
