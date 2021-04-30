package com.iflysse.helper.service;

import java.util.List;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.CourseVO;
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
	 * 通过时间表获取其所属的科目对象
	 * @param timeId 时间表的id
	 * @return
	 */
	public Subject get_subject_by_time(Integer timeId);
	
	/**
	 * 通过课程id获取其所属的科目对象
	 * @param courseId
	 * @return
	 */
	public Subject get_subject_by_course(Integer courseId);
	
	/**
	 * 通过用户id及学期id获取科目列表
	 * @param userId 用户id
	 * @param termId 学期id
	 * @return
	 */
	public List<Subject> get_subject_list(Integer userId, Integer termId);
	
	/**
	 * 通过时间表获取课程列表
	 * @param timeId 时间表的id
	 * @return
	 */
	public List<Course> get_course_by_time(Integer timeId);
	/**
	 * 通过科目id获取课程列表 (返回类型为前端视图)
	 * @param subjectId 时间表的id
	 * @return
	 */
	public List<CourseVO> get_courseVO_by_subject(Integer subjectId);
	/**
	 * 通过科目id获取课程列表
	 * @param subjectId 时间表的id
	 * @return
	 */
	public List<Time> get_time_by_subject(Integer subjectId);
	/**
	 * 更新时间列表
	 * @param timeList 时间列表
	 * @return
	 */
	public List<Course> time_update( Subject subject,  List<Time> timeList);
	
	/**
	 * 插入课程列表
	 * @param courseList 待插入的课程列表
	 * @return
	 */
	public void insert_course_list( List<Course> courseList);
	
	/**
	 * 插入新的科目表
	 * @param newSubject 新的科目表
	 */
	public void insert_subject(Subject newSubject);
	
	/**
	 * 删除单次课程
	 * @param courseId 待删除课程的id
	 */
	public void delete_course_single( Integer courseId );
	
	/**
	 * 删除某一科目( 该科目必须是非空的! )
	 * @param subjectId 待删除科目id
	 */
	public void delete_subject(Integer subjectId);

	/**
	 * 更新效科目信息
	 * @param subject 新的科目信息
	 */
	public void update_subject(Subject subject);

	/**
	 * 通过学期获取科目列表
	 * @param termId 学期id
	 * @return
	 */
	public List<Subject> get_subject_list_by_term(Integer termId);

}
