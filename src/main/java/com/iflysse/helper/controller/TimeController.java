package com.iflysse.helper.controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
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
	private TermDao termDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_add 新增时间表
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 新增科目
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
		Subject subject = ;
		for(TimeVO timeVO: timeVOList) {
			timeList.add( new Time(timeVO) );
		}
		//对timeList进行排序(根据每周课时)
		timeList.sort( new Comparator<Time>() {
			@Override
			public int compare(Time o1, Time o2) {
				return o1.getTimeQuantum() - o2.getTimeQuantum();
			}
		} );
		//设置开始周和结束周
		int startWeek = 0, endWeek = CacheController.termBuffer.getWeeks();
		//遍历本学期的所有周次
		try {
			while (startWeek < endWeek ) {
				for (Time time : timeList) {
					//对某一周进行检测
					if( (time.getWeeksValue() & 1 << startWeek) != 0) {
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
			request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
			return "courseUpdate";
		}
		return "error/500";
	}
	
}
