package com.iflysse.helper.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
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
	public String subject_add(HttpServletRequest request, List<TimeVO> timeVOList) {
		List<Time> timeList = new ArrayList<>(timeVOList.size() );
		List<Course> courseList = new LinkedList<>();
		Calendar calendar = Calendar.getInstance(), calendarTemp = Calendar.getInstance();
		boolean flag = true;
		calendar.setTime( CacheController.termBuffer.getStartTime() );
		Subject subject = subjectDao.get_subject_by_id( timeVOList.get(0).getSubject() );
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
		int startWeek = 0, endWeek = CacheController.termBuffer.getWeeks();
		//遍历本学期的所有周次
		try {
			while (startWeek < endWeek ) {
				for (Time time : timeList) {
					//对某一周进行检测
					if( (time.getWeeksValue() & 1 << startWeek++) != 0) {
						subject.setTimeTotal( (short) (subject.getTimeTotal() - 2 ) );
						if( flag && calendar.get(Calendar.DAY_OF_WEEK ) > time.getWeek() ) {
							continue;
						}
						if( subject.getTimeTotal() <= 0 ) {
							throw new Exception("跳出循环");
						}
						calendarTemp.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_YEAR) );
						calendarTemp.set(Calendar.DAY_OF_WEEK, time.getWeek() );
						courseList.add( new Course(
								time.getSubject(),
								time.getId(),
								null,
								null,
								(byte) 0,
								false,
								new Date( calendarTemp.getTime().getTime() ) ) );
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
		} catch (Exception e) {
			//通过try-catch跳出二重循环
			request.setAttribute("result", new Result< List<Course> >(ResultCode.SUCCESS, courseList) );
			//插入时间表
			timeDao.insert_time_list(timeList);
			return "courseAdd";
		}
		return "error/500";
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
	public String time_delete(HttpServletRequest request, List<TimeVO> timeVOList) {
		if( timeVOList.size() == 0 ) {
			request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PARAM, null) );
			return "error/404";
		}
		List<Time> timeList = timeDao.get_time_list_by_subject( timeVOList.get(0).getSubject() );
		TimeVO timeVOBuf = null;
		Time timeBuf = null;
		/*
		 * 此段代码可优化
		 * 思路: 数据库直接查询多个id返回列表
		 */
		for( int i = 0, g = 0; i < timeVOList.size() ; ++g ) {
			if( timeList.get(g).getId() == timeVOBuf.getId() ) {
				timeBuf = timeList.get(g);
				if( timeVOBuf.getAddWeek() != null) {
					timeBuf.addWeeks( timeVOBuf.getAddWeek() );
				}
				if( timeVOBuf.getDeleteWeek() != null) {
					timeBuf.deleteWeeks( timeVOBuf.getDeleteWeek() );
				}
				timeBuf.setTimeQuantum(timeVOBuf.getWeek(), timeVOBuf.getHowTime());
				++i;
			}
			if(g >= timeList.size() ) {
				g = 0;
			}
		}
		Map<Integer, Time> timeCache = new HashMap<Integer, Time>();
		for(Time time : timeList) {
			timeCache.put(time.getId(), time);
		}
		/*
		 * 此处可优化
		 * 思路 : 查询数据库时按外键time排序, 一旦subject发生变化再去map中查询time对象
		 */
		List<Course> courseList = courseDao.get_course_list_by_subject( timeVOBuf.getSubject() );
		
		for(Course course : courseList) {
			timeBuf = timeCache.get( course.getTime() );
			
		}
		timeDao.update_time_list(timeList);
		return null;
	}
}
