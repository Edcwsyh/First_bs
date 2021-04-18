package com.iflysse.helper.service;

import java.util.List;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.tools.Result;

public interface SubjectServer {

	/**
	 * 通过科目的id来获取科目对象
	 * @param id科目的主键id
	 * @return
	 */
	public Subject get_subject_by_id(Integer id);
	
	/**
	 * 更新时间表
	 * @param timeList 已被更新的时间表
	 * @param updateCourseList 需要更新的课程表
	 * @param insertCourseList 需要新增的课程表
	 */
	public void update_time(List<Time> timeList, List<Course> updateCourseList, List<Course> insertCourseList);
	
	/**
	 * 通过时间表获取其所属的科目对象
	 * @param timeId 时间表的id
	 * @return
	 */
	public Subject get_subject_by_time(Integer timeId);
	
	/**
	 * 通过时间表获取课程列表
	 * @param timeId 时间表的id
	 * @return
	 */
	public List<Course> get_course_by_time(Integer timeId);
	/**
	 * 
	 * @param timeList 时间列表
	 * @return
	 */
	public void time_update( Subject subject,  List<Time> timeList);
}
