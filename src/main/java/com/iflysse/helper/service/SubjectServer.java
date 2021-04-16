package com.iflysse.helper.service;

import java.util.List;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.tools.Result;

public interface SubjectServer {

	/**
	 * 通过科目的id来获取科目对象
	 * @param id科目的主键id
	 * @return
	 */
	public Subject get_subject_by_id(Integer id);
	
	/**
	 * 更新
	 * @param id科目的主键id
	 * @return
	 */
	public void update_time(List<Time> timeList, List<Course> updateCourseList, List<Course> insertCourseList);
}
