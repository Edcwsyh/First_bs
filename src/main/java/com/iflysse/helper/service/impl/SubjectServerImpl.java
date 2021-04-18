package com.iflysse.helper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.dao.CourseDao;
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.dao.TimeDao;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.service.SubjectServer;

@Service
public class SubjectServerImpl implements SubjectServer {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private TermDao termDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private CourseDao courseDao;
	
	@Autowired 
	private TimeDao timeDao;

	@Override
	public Subject get_subject_by_id(Integer id) {
		return subjectDao.get_subject_by_id(id);
	}

	@Transactional
	@Override
	public void update_time(List<Time> timeList, List<Course> updateCourseList, List<Course> insertCourseList) {
		subjectDao.delete_time_and_course_by_subject(timeList.get(0).getSubject());
		timeDao.insert_time_list(timeList);
		courseDao.update_course_list(updateCourseList);
		if ( insertCourseList != null && insertCourseList.size() != 0 ) {
			courseDao.insert_course_list(insertCourseList);
		}
	}

	@Override
	public Subject get_subject_by_time(Integer timeId) {
		return subjectDao.get_subject_by_time(timeId);
	}
	@Override
	public List<Course> get_course_by_time(Integer timeId){
		return courseDao.get_course_list_by_time(timeId);
	}
}
