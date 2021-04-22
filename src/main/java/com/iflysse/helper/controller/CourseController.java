package com.iflysse.helper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Report;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.service.SubjectServer;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@RequestMapping("/subject/course")
public class CourseController {
	
	@Autowired
	private SubjectServer subjectServer;

	/**
	 * @api {post} /TeacherHelper/subject/course/course_add 新增课程信息
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 新增课程信息
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {list} courseList 课程列表
	 * @apiParam {string} Course.goal 教学目标
	 * @apiParam {string} Course.content 教学内容
	 * @apiParam {number} Course.mode 教学模式
	 * @apiParam {date} Course.specificTime 具体上课时间(年-月-日)
	 * @apiParam {boolean} Course.isHomework 是否布置作业
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"courseList" : 
	 *			{
	 *			"goal" : "没有目标",
	 *			"content" : "没有内容",
	 *			"mode" : 1,
	 *			"time" : 2020-9-10,
	 *			"isHomework" : false
	 *			},
	 *	 		{
	 *			"goal" : "有目标",
	 *			"content" : "有内容",
	 *			"mode" : 0,
	 *			"time" : 2020-9-9,
	 *			"isHomework" : true
	 *			}
	 * 	}
	 * 	 @apiDescription 接口说明:
	 * 	 前端应该传入一个List<Course>列表
	 */
	@RequestMapping("/course_add")
	public String course_add(HttpServletRequest request, HttpSession session, List<Course> courseList) {
		if(courseList.size() == 0) {
			request.setAttribute("result", new Result< List <Report> >(ResultCode.ERROR_PARAM, null));
			return "error/400";
		}
		User requestUser = (User) session.getAttribute("loggedUser");
		Subject subject = subjectServer.get_subject_by_id(courseList.get(0).getId());
		if ( subject.getTeacher() !=  requestUser.getId() ) {
			request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PERMISSION, null));
			return "error/403";
		}
		for(Course course : courseList) {
			//检测所传入的课程是否合法
			if(course.check( Constant.CHECK_ALL ^ Constant.CHECK_ID ) != 0) {
				request.setAttribute("result", new Result< List <Report> >(ResultCode.ERROR_PARAM, null));
				return "error/400";
			}
		}
		subjectServer.insert_course_list(courseList);
		request.setAttribute("result", new Result< List <Report> >(ResultCode.SUCCESS, null));
		return "redirect:course_list?subjectId=" + courseList.get(0).getSubject() ;
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/course/course_update 更新课程信息
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 更新课程信息
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {list} courseList 课程列表
	 * @apiParam {number} Course.id 单次课程id
	 * @apiParam {string} Course.goal 教学目标
	 * @apiParam {string} Course.content 教学内容
	 * @apiParam {number} Course.mode 教学模式
	 * @apiParam {date} Course.specificTime 具体上课时间(年-月-日)
	 * @apiParam {boolean} Course.isHomework 是否布置作业
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"courseList" : 
	 *			{
	 *			"id" : 1,
	 *			"goal" : "没有目标",
	 *			"content" : "没有内容",
	 *			"mode" : 1,
	 *			"time" : 2020-9-10,
	 *			"isHomework" : false
	 *			},
	 *	 		{
	 *			"id" : 2,
	 *			"goal" : "有目标",
	 *			"content" : "有内容",
	 *			"mode" : 0,
	 *			"time" : 2020-9-9,
	 *			"isHomework" : true
	 *			}
	 * 	}
	 * 	 @apiDescription 接口说明:
	 * 	 前端应该传入一个List<Course>列表
	 */
	@RequestMapping("/course_update")
	public String course_update(HttpServletRequest request, HttpSession session, List<Course> courseList) {
		if(courseList.size() == 0) {
			request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PARAM, null));
			return "error/400";
		}
		User requestUser = (User) session.getAttribute("loggedUser");
		Subject subject = subjectServer.get_subject_by_id(courseList.get(0).getId());
		if ( subject.getTeacher() !=  requestUser.getId() ) {
			request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PERMISSION, null));
			return "error/403";
		}
		for(Course course : courseList) {
			//检测所传入的课程是否合法
			if(course.check( Constant.CHECK_ALL ^ Constant.CHECK_ID ) != 0) {
				request.setAttribute("result", new Result< Void >(ResultCode.ERROR_PARAM, null));
				return "error/400";
			}
		}
		subjectServer.insert_course_list(courseList);
		request.setAttribute("result", new Result< List <Report> >(ResultCode.SUCCESS, null));
		return "redirect:course_list?subjectId=" + courseList.get(0).getSubject() ;
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/course/course_list 获取课程列表
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 获取课程列表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} CourseVO.goal 教学目标
	 * @apiParam {string} CourseVO.content 教学内容
	 * @apiParam {number} CourseVO.mode 教学模式, 0表示学练-线下， 1表示授课-现场
	 * @apiParam {date} CourseVO.specificTime 具体上课时间(年-月-日)
	 * @apiParam {boolean} CourseVO.isHomework 是否布置作业
	 * @apiParam {number} CourseVO.howTime 第几大节上课
	 * @apiParam {number} CourseVO.week 周几上课
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 *		{
	 *		"goal" : "没有目标",
	 *		"content" : "没有内容",
	 *		"mode" : 1,
	 *		"time" : 2020-9-10,
	 *		"isHomework" : false,
	 *		"week" : 5,
	 *		"howTime" : 6
	 *		},
	 *	 	{
	 *		"goal" : "有目标",
	 *		"content" : "有内容",
	 *		"mode" : 0,
	 *		"time" : 2020-9-9,
	 *		"isHomework" : true
	 *	 	"week" : 4,
	 *		"howTime" : 3
	 *		}
	 * 	}
	 */
	@RequestMapping("/course_list")
	public String course_list(HttpServletRequest request, HttpSession session, Integer subjectId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		if(subjectId == null) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null));
			return "error/400";
		}
		Subject subject = subjectServer.get_subject_by_id(subjectId);
		//如果非本人请求则需验证权限
		if( requestUser.getId() != subject.getTeacher() ) {
			if (requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
				request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null));
				return "error/403";
			}
		}
		List<CourseVO> result = subjectServer.get_courseVO_by_subject(subjectId);
		request.setAttribute("result", new Result< List <CourseVO> >(ResultCode.SUCCESS, result));
		return "courseList"; 
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/course/course_delete_single 删除单次课程
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 删除单次课程
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} courseId 教学目标
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		courseId: "2020"
	 * 	}
	 */
	@RequestMapping("/course_delete_single")
	public String course_delete_single(HttpServletRequest request, HttpSession session, Integer courseId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		if(courseId == null) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) );
			return "error/400";
		}
		
		Subject subject = subjectServer.get_subject_by_course(courseId);
		if( requestUser.getId() != subject.getTeacher() ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		subjectServer.delete_course_single(courseId);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
		return "redirect:course_list?subjectId=" + subject.getId();
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/course/course_delete_single 删除多次课程
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 删除多次课程
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {list} courseIdList 课程id列表
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"courseIdList" : { 11,12,13 }
	 * 	}
	 */
	@RequestMapping("/course_delete_list")
	public String course_delete_list(HttpServletRequest request, HttpSession session, List<Integer> courseIdList) {
		User requestUser = (User) session.getAttribute("loggedUser");
		if(courseIdList == null || courseIdList.size() == 0) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) );
			return "error/400";
		}
		
		Subject subject = subjectServer.get_subject_by_course(courseIdList.get(0));
		if( requestUser.getId() != subject.getTeacher() ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
		return "redirect:course_list?subjectId=" + subject.getId();
	}
	
}
