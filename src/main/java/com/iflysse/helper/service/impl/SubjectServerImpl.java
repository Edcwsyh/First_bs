package com.iflysse.helper.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.CourseDao;
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.dao.TimeDao;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.service.SubjectServer;
import com.iflysse.helper.tools.CacheUtil;
import com.iflysse.helper.tools.Result;

@Service
public class SubjectServerImpl implements SubjectServer {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private TermDao termDao;
	
	@Autowired 
	private CourseDao courseDao;
	
	@Autowired 
	private TimeDao timeDao;
	
	private static List<Time> time_merage(List<Time> timeList) {
		//构建一个新的列表用于存放已被合并的数据
		List<Time> result = new LinkedList<Time>();
		int i, g;
		for( i = 0, g = 0; i  < timeList.size(); i = g) {
			for( g = i + 1; true; ++g) {
				if( g >= timeList.size() || timeList.get(i).getTimeQuantum() != timeList.get(g).getTimeQuantum() ) {
					result.add( timeList.get(i) ); //将已合并完成的数据添加至新的列表
					break;
				} else {
					timeList.get(i).merage( timeList.get(g) );
				}
			}
		}
		return result;
	}
	
	/**
	 * 生成1个需要更新的课程列表和1个需要插入的课程列表
	 * 返回一个包含2个课程列表的列表, [0]为插入列表, [1]为更新列表
	 * @param subject
	 * @param timeList
	 * @return 
	 */
	private List< List<Course> > generate_course( Subject subject,  List<Time> timeList) {
		int startWeek = 0, 
				endWeek = subject.getTerm() == CacheUtil.currTerm.getId() ? 
							CacheUtil.currTerm.getWeeks() : 
							termDao.get_term_by_id( subject.getTerm() ).getWeeks() ;
		//返回两个对象 : 需要插入的课程列表和需要更新的课程列表
		List<Course> insertList = new LinkedList<Course>();
		List<Course> updateList = courseDao.get_course_list_by_subject(subject.getId());
		List< List<Course> > result = Arrays.asList( insertList, updateList );
		Iterator<Course> courseIterator = updateList.listIterator();
		Calendar calendar = Calendar.getInstance(), calendarTemp = Calendar.getInstance();
		boolean flag = true;
		while (startWeek < endWeek ) {
			for (Time time : timeList) {
				//对某一周进行检测
				if( (time.getWeeksValue() & 1 << startWeek++) != 0) {
					if( flag && calendar.get(Calendar.DAY_OF_WEEK ) > time.getWeek() ) {
						continue;
					}
					calendarTemp.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_YEAR) );
					calendarTemp.set(Calendar.DAY_OF_WEEK, time.getWeek() );
					//如果课程列表中还存在课程,则修改, 否则,将新增课程到新增列表当中
					if ( courseIterator.hasNext() ) {
						Course courseCache = courseIterator.next();
						courseCache.setWeek( (byte) (startWeek + 1) );
						courseCache.setSpecificTime(new Date( calendarTemp.getTime().getTime() ) );
						courseCache.setTime(time.getId() );
					} else {
						insertList.add( new Course( time, new Date( calendarTemp.getTime().getTime() ), (byte) (startWeek + 1) ) );
					}
				}
			}
			if(flag) {
				//第一次运行将calendar格式化为周一
				int addDay = 8 - calendar.get(Calendar.DAY_OF_WEEK );
				calendar.add(Calendar.DAY_OF_WEEK, addDay);
				flag = false;
			}else {
				//向后快进一周
				calendar.add(Calendar.DAY_OF_WEEK, 7);
			}
		}
		return result;
	}

	@Override
	public Subject get_subject_by_id(Integer id) {
		return subjectDao.get_subject_by_id(id);
	}

	@Override
	public Subject get_subject_by_time(Integer timeId) {
		return subjectDao.get_subject_by_time(timeId);
	}
	
	@Override
	public Subject get_subject_by_course(Integer courseId) {
		return subjectDao.get_subject_by_course(courseId);
	}
	
	@Override
	public List<CourseVO> get_courseVO_by_subject(Integer subjectId) {
		return courseDao.get_courseVO_list_by_subject(subjectId);
	}

	@Override
	public List<Time> get_time_by_subject(Integer subjectId) {
		return timeDao.get_time_list_by_subject(subjectId);
	}
	
	@Override
	public List<Course> get_course_by_time(Integer timeId){
		return courseDao.get_course_list_by_time(timeId);
	}
	
	@Transactional
	@Override
	public List<Course> time_update( Subject subject,  List<Time> timeList) {
		//对timeList进行排序(根据每周课时)
		timeList.sort( new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.getTimeQuantum() - o2.getTimeQuantum();
			}
		} );
		//合并相同时间段上课的数据
		time_merage(timeList);
		//先删除所有的时间表
		timeDao.delete_time_by_subject( subject.getId() );
		//先插入新的时间表获取其主键id
		timeDao.insert_time_list(timeList);
		/*
		 * [0]为待插入的列表, [1]为需要更新的列表
		 */
		List< List<Course> > courseList = generate_course(subject, timeList);
		courseDao.insert_course_list(courseList.get(0)); //插入新的课程列表
		courseDao.update_course_list(courseList.get(1)); //更新课程列表
		List<Course> result = new ArrayList<Course>(courseList.get(0));
		result.addAll(courseList.get(1));
		return result;
	}

	@Override
	public void insert_course_list(List<Course> courseList) {
		courseDao.insert_course_list(courseList);
	}

	@Override
	public void delete_course_single(Integer courseId) {
		courseDao.delete_course_single(courseId);
	}

	@Override
	public List<Subject> get_subject_list(Integer userId, Integer termId) {
		return subjectDao.get_subject_list(userId, termId);
	}

	@Override
	public void insert_subject(Subject newSubject) {
		subjectDao.insert_subject(newSubject);
	}

	@Override
	public void delete_subject(Integer subjectId) {
		subjectDao.delete_subject(subjectId);
	}

	@Override
	public void update_subject(Subject subject) {
		subjectDao.update_subject(subject);
	}


}
