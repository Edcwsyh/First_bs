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
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
	 * 	@apiDescription 接口说明:
	 * 	 前端应该传入一个List<TimeVO>列表
	 */
	@RequestMapping("/time_update")
	public String time_update(HttpServletRequest request, HttpSession session, List<TimeVO> timeVOList) {
		if( timeVOList.size() == 0 ) {
			request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PARAM, null) );
			System.out.println("已拦截 - 参数错误");
			return "error/404";
		}
		Subject subject = subjectServer.get_subject_by_id( timeVOList.get(0).getSubject() );
		User requestUser = (User) session.getAttribute("loggedUser");
		if ( subject.getTeacher() != requestUser.getId() ) {
			request.setAttribute("result", new Result< Void >( ResultCode.ERROR_PERMISSION, null) );
			System.out.println("已拦截 - 用户权限不足");
			return "error/403";
		}
		List<Time> timeList = new LinkedList<Time>();
		for ( TimeVO timeVOIter : timeVOList ) {
			timeList.add( new Time(timeVOIter) );
		}
		List<Course> courseList = subjectServer.time_update(subject, timeList);
		System.out.println("请求成功 - 已更新时间表和课程表");
		request.setAttribute("result", new Result< List<Course> >( ResultCode.SUCCESS, courseList) );
		return "redirect:/course/courseList";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/time/time_list 查看时间列表
	 * @apiVersion 1.0.0
	 * @apiGroup Time
	 * @apiName 查看时间列表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {list} data 错误信息
	 * @apiParam {number} subjectId 需要查看的科目id
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 * 		"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" :
	 * 			{
	 *				"id" : 112,
	 *				"weeks" : "第1周到第7周, 第9周到第11周"
	 *				"week" : 1,
	 *				"howTime" : 1
	 *			},
	 *			{
	 *	 			"id" : 113,
	 *				"weeks" : "第1周到第7周, 第9周到第11周"
	 *				"week" : 2,
	 *				"howTime" : 1
	 *			}
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"subjectId" : 889
	 *	}
	 */
	@RequestMapping("/time_list")
	public String time_list(HttpServletRequest request, HttpSession session, Integer subjectId, @RequestParam(defaultValue = "1")Integer pageIndex) {
		if ( subjectId == null ) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PARAM, null) );
		}
		Subject subject = subjectServer.get_subject_by_id(subjectId);
		User requestUser = (User) session.getAttribute("loggedUser");
		if ( subject.getTeacher() != requestUser.getId() && 
				requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		request.setAttribute("result", new Result< List<Time> >( ResultCode.SUCCESS, subjectServer.get_time_by_subject(subjectId) ) );
		Page<TimeVO> subjectPage = PageHelper.startPage(pageIndex, Constant.PAGE_NUMBER);
		request.setAttribute("page", subjectPage.toPageInfo() );
		return "timeList";
	}
	

}
