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
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_update 更新时间表
	 * @apiVersion 1.0.0
	 * @apiGroup Time
	 * @apiName 更新时间表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {object} timeVOList 时间列表
	 * @apiParam {number} TimeVO.id 要被更新时间表的id(非必填, 若为空, 则表示该时间段是新增的)
	 * @apiParam {number} TimeVO.startWeek 开始周(必填)
	 * @apiParam {number} TimeVO.endWeek 结束周(必填)
	 * @apiParam {number} TimeVO.interval 表示每几周上一次课(必填, 这是一个下选择下拉框, 默认为1)
	 * @apiParam {String} TimeVO.addWeek 在开始周与结束周之外还要上课的周(非必填), 格式:为一段数字序列,每个数字之间用逗号隔开,如"13,15"
	 * @apiParam {String} TimeVO.deleteWeek 在开始周与结束周之中不需要上课的周(非必填), 格式同addWeek
	 * @apiParam {number} TimeVO.week 该课程在周几上课(必填, 这应该是一个选择下拉框, 范围1-7)
	 * @apiParam {number} TimeVO.howTime 该课程在一天中的第几大节(必填, 这应该是一个选择下拉框, 范围1-6)
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"timeVOList" :
	 *			{
	 *				"id" : null,
	 *				"startWeek" : 1,
	 *				"endWeek" : 12,
	 *				"interval" : 1,
	 *				"addWeek" : null,
	 *				"deleteWeek" : "4,6",
	 *				"week" : 4,
	 *				"howTime" : 3
	 *				//参数说明, 该参数表示第1周到第12周的每周4的第3大节(5,6小节)上课, 但是第4周和第6周不用上课(deleteWeek)
	 *				//由于id为空, 表示这是一条新增数据
	 *			},
	 *			{
	 *				"id" : 112,
	 *				"startWeek" : 2,
	 *				"endWeek" : 8,
	 *				"interval" : 2,
	 *				"addWeek" : "5,13",
	 *				"deleteWeek" : null,
	 *				"week" : 1,
	 *				"howTime" : 1
	 *				//参数说明, 该参数表示第2, 4, 5(addWeek), 6, 8, 13周每周1的第1大节(1,2小节)上课
	 *				//由于id为非空, 将会更新该id所指向的时间段以及引用该时间段的课程的时间
	 *			}
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
		request.setAttribute("result",  result);
		return "timeList";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_update 删除时间表
	 * @apiVersion 1.0.0
	 * @apiGroup Time
	 * @apiName 更新时间表
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
	 *		"timeId" : 112
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
