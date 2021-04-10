package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.User;


public interface CourseDao {
	/**
	 * 获取某一科目的所属课程
	 * @return
	 */
	@Select("select * from t_user where subject=#{subjectId}")
	public List<Course> get_course_list_by_subject(Integer subjectId);
	
	/**
	 * 新增一条用户数据
	 * @param user
	 */
	public void insert_course_list (List<Course> courseList);
	
	/**
	 * 更新用户的 用户名、真实姓名、邮箱、电话号码、性别
	 * @param user
	 */
	public void update_course_list (List<Course> courseList);
	/**
	 * 更新用户的密码
	 * @param user
	 */
	@Update("update t_user set password=#{password} where id = #{id}")
	public void update_user_password (User user);
	
	
	@Update("update t_user set state=#{state} where id=#{id}")
	public void update_user_state (int id, byte state);
	
	@Delete("delete from t_term where id=#{id}")
	public void delete_user (User user);
}
