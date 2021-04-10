package com.iflysse.helper.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@RequestMapping("/subject/course")
public class CourseController {

	/**
	 * @api {post} /course/course_update 更新科目信息
	 * @apiVersion 1.0.0
	 * @apiGroup Course
	 * @apiName 更新课程信息
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} teachingGoal 教学目标
	 * @apiParam {string} teachingContent 教学内容
	 * @apiParam {string} teachingMode 教学模式， 0表示学练-线下， 1表示授课-现场
	 * @apiParam {boolean} isHomework 是否布置作业
	 * @apiParam {date} subjectTimeTotal 该科目的总课时
	 * @apiParam {number} subjectTimeTheory 该科目的理论课时
	 * @apiParam {number} subjectTimePractice 该科目的实践课时
	 * @apiParam {number} subjectId 科目名
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"subjectName":"拾金不昧",
	 * 		"subjectType":0,
	 * 		"subjectTa":"窦尔敦",
	 * 		"subjectClass":"不知道取什么名字",
	 * 		"subjectTimeTotal":"66",
	 * 		"subjectTimeTheory":"33",
	 * 		"subjectTimePractice":"33"
	 * 	}
	 */
	@RequestMapping("/course_update")
	public Result<Boolean> course_update(HttpServletRequest request) {
		return new Result<Boolean>(ResultCode.SUCCESS, true);
	}
	
}
