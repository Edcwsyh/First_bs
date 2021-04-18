package com.iflysse.helper.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.bean.TimeVO;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.CourseDao;
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.dao.TimeDao;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.service.SubjectServer;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/subject/time")
public class TimeController {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private TimeDao timeDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private TermDao termDao;
	
	@Autowired 
	private SubjectServer subjectServer;
	
	private void time_merage(List<Time> timeList) {
		//合并完成的数量
		for(int i = 0 ; i  < timeList.size(); ++i) {
			for(int g = i + 1; g < timeList.size(); ++g) {
				if( timeList.get(i).getTimeQuantum() != timeList.get(g).getTimeQuantum() ) {
					timeList.subList(i + 1, g).clear(); //删除已被合并的数据
					break;
				} else {
					timeList.get(i).merage( timeList.get(g) );
				}
			}
		}
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_add 新增时间表
	 * @apiVersion 1.0.0
	 * @apiGroup Time
	 * @apiName 新增时间表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} name 学期名
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 *
	 * 	}
	 */
	@RequestMapping("/time_add")
	public String subject_add(HttpServletRequest request,  HttpSession session, List<TimeVO> timeVOList) {
		Subject subject = subjectDao.get_subject_by_id( timeVOList.get(0).getSubject() );
		User requestUser = (User) session.getAttribute("loggedUser");
		if ( subject.getTeacher() != requestUser.getId() ) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		List<Time> timeList = new ArrayList<>(timeVOList.size() );
		List<Course> courseList = new LinkedList<>();
		Calendar calendar = Calendar.getInstance(), calendarTemp = Calendar.getInstance();
		boolean flag = true;
		calendar.setTime( Cache.currTerm.getStartTime() );
		for(TimeVO timeVO: timeVOList) {
			//检测时间表的参数是否完整
			if(timeVO.check(Constant.CHECK_ALL ^ Constant.CHECK_ID) != 0) {
				request.setAttribute("result", new Result< List<Course> >(ResultCode.ERROR_PARAM, courseList) );
				return "error/400";
			}
			timeList.add( new Time(timeVO) );
		}
		//对timeList进行排序(根据每周课时)
		timeList.sort( new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.getTimeQuantum() - o2.getTimeQuantum();
			}
		} );
		//合并相同时间段上课的数据
		time_merage(timeList);

		//设置开始周和结束周
		int startWeek = 0, endWeek = Cache.currTerm.getWeeks();
		//遍历本学期的所有周次
		while (startWeek < endWeek ) {
			for (Time time : timeList) {
				//对某一周进行检测
				if( (time.getWeeksValue() & 1 << startWeek++) != 0) {
					if( flag && calendar.get(Calendar.DAY_OF_WEEK ) > time.getWeek() ) {
						continue;
					}
					calendarTemp.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_YEAR) );
					calendarTemp.set(Calendar.DAY_OF_WEEK, time.getWeek() );
					courseList.add( new Course( time, new Date( calendarTemp.getTime().getTime() ) ) );
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
		Result< List<Course> > result = timeList.size() * Constant.COURSE_LENGTH > subject.getTimeTotal() ? 
				new Result< List<Course> >(ResultCode.WARNING_SUBJECT_TIME_ERROR, courseList) :
				new Result< List<Course> >(ResultCode.SUCCESS, courseList);
		request.setAttribute("result",  result);
		//插入时间表
		timeDao.insert_time_list(timeList);
		return "courseAdd";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_update 新增时间表
	 * @apiVersion 1.0.0
	 * @apiGroup Time
	 * @apiName 删除时间表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} name 学期名
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 *
	 * 	}
	 */
	@RequestMapping("/time_update")
	public String time_update(HttpServletRequest request, HttpSession session, List<TimeVO> timeVOList) {
		if( timeVOList.size() == 0 ) {
			request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PARAM, null) );
			return "error/404";
		}
		Subject subject = subjectDao.get_subject_by_id( timeVOList.get(0).getSubject() );
		User requestUser = (User) session.getAttribute("loggedUser");
		if ( subject.getTeacher() != requestUser.getId() ) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		List<Time> timeList = new LinkedList<Time>();
		List<Course> courseList = courseDao.get_course_list_by_subject(subject.getId());
		List<Course> insertCoureseList = new LinkedList<Course>();
		Iterator<Course> courseIterator = courseList.listIterator();
		Calendar calendar = Calendar.getInstance(), calendarTemp = Calendar.getInstance();
		boolean flag = true;
		Course courseCache;
		calendar.setTime( Cache.currTerm.getStartTime() );
		for ( TimeVO timeVOIter : timeVOList ) {
			timeList.add( new Time(timeVOIter) );
		}
		int startWeek = 0, 
			endWeek = subject.getTerm() == Cache.currTerm.getId() ? 
						Cache.currTerm.getWeeks() : 
						termDao.get_term_by_id( subject.getTerm() ).getWeeks() ;
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
						courseCache = courseIterator.next();
						courseCache.setSpecificTime(new Date( calendarTemp.getTime().getTime() ) );
					} else {
						insertCoureseList.add( new Course( time, new Date( calendarTemp.getTime().getTime() ) ) );
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
		Result< List<Time> > result = timeList.size() * Constant.COURSE_LENGTH > subject.getTimeTotal() ? 
				new Result< List<Time> >(ResultCode.WARNING_SUBJECT_TIME_ERROR, timeList) :
				new Result< List<Time> >(ResultCode.SUCCESS, timeList);
		subjectServer.update_time(timeList, courseList,insertCoureseList);
		subjectServer.update_time(timeList, courseList,insertCoureseList);
		request.setAttribute("result",  result);
		return "timeList";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_update 新增时间表
	 * @apiVersion 1.0.0
	 * @apiGroup Time
	 * @apiName 删除时间表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} name 学期名
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 *
	 * 	}
	 */
	@RequestMapping("/time_delete")
	public String time_delete(HttpServletRequest request, HttpSession session, Integer timeId) {
		Subject subject = subjectServer.get_subject_by_time(timeId);
		User requestUser = (User) session.getAttribute("loggedUser");
		if ( subject.getTeacher() != requestUser.getId() ) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		List<Course> courseList = subjectServer.get_course_by_time(timeId);
		if ( courseList != null ) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_TIME_NOT_EMPTY, null) );
			return "timeList";
		}
		return null;
	}
	
	@RequestMapping("/goto_timeAdd")
	public String subject_add() {
		
		return "timeAdd";
	}
	
	
}
