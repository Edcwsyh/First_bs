package com.iflysse.helper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.Report;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@RequestMapping("/subject/course")
public class CourseController {

	/**
	 * @api {post} /TeacherHelper/subject/course/course_update 更新课程信息
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 更新课程信息
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} teachingGoal 教学目标
	 * @apiParam {string} teachingContent 教学内容
	 * @apiParam {string} teachingMode 教学模式， 0表示学练-线下， 1表示授课-现场
	 * @apiParam {string} goal 教学目标
	 * @apiParam {string} content 教学内容
	 * @apiParam {number} mode 教学模式
	 * @apiParam {date} time 具体上课时间(年-月-日)
	 * @apiParam {boolean} isHomework 是否布置作业
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
	 *		"isHomework" : false
	 *		},
	 *	 	{
	 *		"goal" : "有目标",
	 *		"content" : "有内容",
	 *		"mode" : 0,
	 *		"time" : 2020-9-9,
	 *		"isHomework" : true
	 *		}
	 * 	}
	 */
	@RequestMapping("/course_insert")
	public String course_update(HttpServletRequest request, List<Course> courseList) {
		for(Course course : courseList) {
			if(course.check( Constant.CHECK_ALL ^ Constant.CHECK_ID ) != 0) {
				request.setAttribute("result", new Result< List <Report> >(ResultCode.ERROR_PERMISSION, null));
				return "error/400";
			}
		}
		return null;
	}
	
}
