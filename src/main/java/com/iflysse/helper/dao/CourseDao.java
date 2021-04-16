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
	@Select("select * from t_course where subject=#{subjectId}")
	public List<Course> get_course_list_by_subject(Integer subjectId);
	
	/**
	 * 新增多条课表数据
	 * @param courseList
	 */
	public void insert_course_list (List<Course> courseList);
	
	/**
	 * 更新课表的信息(教学目标, 教学内容, 教学模式, 是否布置作业, 上课的具体时间)
	 * @param courseList
	 */
	public void update_course_list (List<Course> courseList);
	/**
	 * 更新用户的密码
	 * @param user
	 */
	@Update("update t_course set goal=#{goal}, content=#{content}, mode=#{mode}, isHomework=#{isHomework}, specifcTime=#{specifcTime} where id = #{id}")
	public void update_user_password (Course course);
	
	
	@Update("update t_user set state=#{state} where id=#{id}")
	public void update_user_state (int id, byte state);
	
	@Delete("delete from t_course where id=#{id}")
	public void delete_course_single (Integer id);
}
