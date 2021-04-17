package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.User;


public interface UserDao {
	/**
	 * 获取所有用户列表(包含被冻结)
	 * @return
	 */
	@Select("select * from t_user")
	public List<User> get_user_list();
	
	/**
	 * 通过 用户名 查询用户
	 * @param username
	 * @return
	 */
	@Select("select * from t_user where username=#{username}")
	public User get_user_by_username(String username);
	
	/**
	 * 通过 电话号码 查询用户
	 * @param phone
	 * @return
	 */
	@Select("select * from t_user where phone=#{phone}")
	public User get_user_by_phone(String phone);
	
	/**
	 * 通过 邮箱 查询用户
	 * @param mail
	 * @return
	 */
	@Select("select * from t_user where mail=#{mail}")
	public User get_user_by_mail(String mail);
	
	/**
	 * 通过 id 查询用户
	 * @param id
	 * @return
	 */
	@Select("select * from t_user where id=#{id}")
	public User get_user_by_id(int id);
	
	/**
	 * 通过 用户名 || 邮箱  || 电话 查询用户
	 * @param user
	 * @return
	 */
	@Select("select * from t_user where username=#{username} or mail=#{mail} or phone=#{phone}")
	//ump代表username, mail, phone, 即查找用户名、邮箱或电话号码
	public User get_user_by_ump(User user);
	
	/**
	 * 新增一条用户数据
	 * @param user
	 */
	@Insert("insert into t_user(username, password, realName, phone, mail, gender, permission, state) "
			+ "value(#{username}, #{password},#{realName}, #{phone}, #{mail}, #{gender}, #{permission}, #{state})")
	public void insert_user (User user);
	
	/**
	 * 新增一条用户数据(由用户指定id)
	 * @param user
	 */
	@Insert("insert into t_user(id, username, password, realName, phone, mail, gender, permission, state) "
			+ "value(#{id}, #{username}, #{password},#{realName}, #{phone}, #{mail}, #{gender}, #{permission}, #{state})")
	public void _insert_user (User user);
	
	/**
	 * 更新用户的 用户名、真实姓名、邮箱、电话号码、性别
	 * @param user
	 */
	@Update("update t_user set "
			+ "username=#{username}, "
			+ "realName=#{realName}, "
			+ "mail=#{mail}, "
			+ "phone=#{phone}, "
			+ "gender=#{gender} "
			+ "where id=#{id}")
	public void update_user_info (User user);
	/**
	 * 更新用户的密码
	 * @param user
	 */
	@Update("update t_user set password=#{password} where id = #{id}")
	public void update_user_password (User user);
	
	/**
	 * 更新用户所有信息
	 * @param user
	 */
	@Update("update t_user set "
			+ "username=#{username}, "
			+ "password=#{password}, "
			+ "realName=#{realName}, "
			+ "mail=#{mail}, "
			+ "phone=#{phone}, "
			+ "gender=#{gender}, "
			+ "permission=#{permission}, "
			+ "state=#{state} "
			+ "where id=#{userid}")
	public void update_user_all (User user);
	
	@Update("update t_user set state=#{state} where id=#{id}")
	public void update_user_state (int id, byte state);
	
	@Delete("delete from t_term where id=#{id}")
	public void delete_user (User user);
}
