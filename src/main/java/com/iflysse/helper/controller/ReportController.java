package com.iflysse.helper.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iflysse.helper.bean.Report;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.ReportDao;
import com.iflysse.helper.service.ReportServer;
import com.iflysse.helper.tools.CacheUtil;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportServer reportServer;
	
	/**
	 * 通过id获取指定报告
	 * @return
	 */
	private Result<Report> get_report_by_id(User requestUser, Integer reportId) {
		Report dbReport = reportServer.get_report_by_id( reportId );
		if ( dbReport == null ) {
			return new Result<Report>(ResultCode.ERROR_REPORT_NOT_FOUNT, null);
		} else if( requestUser.getId() != dbReport.getAuthor() && 
				   requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
			return new Result<Report>(ResultCode.ERROR_PERMISSION, null);
		}
		return new Result<Report>(ResultCode.SUCCESS, dbReport);
	}
	
	/**
	 * @api {post} /TeacherHelper/report/report_update 跳转至更新周报
	 * @apiVersion 1.0.0
	 * @apiGroup Page
	 * @apiName 跳转至更新周报
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} id 周报id
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 *     
	 */
	@RequestMapping("/goto_report_update")
	public String goto_report_update(HttpServletRequest request, HttpSession session, Integer reportId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		//通过封装的方法获取数据库中的周报
		Result<Report> result = get_report_by_id(requestUser, reportId);
		request.setAttribute("result", result);
		switch( result.getResultCode() ) {
			case ERROR_REPORT_NOT_FOUNT : return "error/404";
			case ERROR_PERMISSION : 	  return "error/403";
			case SUCCESS : 				  return "reportUpdate";
			default : 					  return "error/500";
		}
	}

	/**
	 * @api {get} /TeacherHelper/submit_report_list 获取所有用户的周报列表
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 获取所有用户的周报
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据
	 * @apiSuccess {number} id 周报的id
	 * @apiSuccess {number} author 周报作者的id
	 * @apiSuccess {String} content 周报的内容
	 * @apiSuccess {Timestamp} time 周报的创建时间
	 * @apiSuccess {isSubmit} id 周报的提交状态
	 * @apiParam {number} pageIndex 分页页数
	 * @apiParam {number} week=-1 本学期的第几周, 若不指定则表示本周
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      		{
	 *      		"id" : 8977
	 * 				"author": 1234,
	 * 				"content":"没有内容",
	 * 				"time":"2020-9-1",
	 * 				"isSubmit":true
	 *      		},
	 *      		{
	 *      		"id" : 8978
	 * 				"author": 12345,
	 * 				"content":"1234567",
	 * 				"time":"2020-9-2",
					"isSubmit":true
	 *      		}
	 *      	}
	 *     }
	 */
	@RequestMapping("/submit_report_list")
	public String submit_report_list(HttpServletRequest request, HttpSession session, 
			@RequestParam(defaultValue = "1")Integer pageIndex, @RequestParam(defaultValue = "-1")Byte week) {
		User requestUser = (User) session.getAttribute("loggedUser");
		//判断发出请求的用户的权限
		switch ( requestUser.getPermission() ) {
			case Constant.USER_PERMISSION_NORMAL:
			case Constant.USER_PERMISSION_UNKNOW:
				request.setAttribute("result", new Result< List <Report> >(ResultCode.ERROR_PERMISSION, null));
				return "error/403";
			default:break;
		}
		if ( week == -1 ) {
			Calendar startTime = Calendar.getInstance(), currentTime = Calendar.getInstance();
			startTime.setTime( CacheUtil.currTerm.getStartTime() );
			startTime.setFirstDayOfWeek(Calendar.MONDAY);
			currentTime.setTime( new Date() );
			currentTime.setFirstDayOfWeek( Calendar.MONDAY );
			week = (byte) (currentTime.get(	Calendar.WEEK_OF_YEAR) - startTime.get( Calendar.WEEK_OF_YEAR ) + 1 );
			System.out.println(week);
		}
		request.setAttribute("result", new Result< List <Report> >(
					ResultCode.SUCCESS, 
					reportServer.get_report_list_by_submit( CacheUtil.currTerm.getId(), week ) )
				);
		Page<Report> subjectPage = PageHelper.startPage(pageIndex, Constant.PAGE_NUMBER);
		request.setAttribute("page", subjectPage.toPageInfo() );
		return "submitReportList";
	}
	
	/**
	 * @api {get} /TeacherHelper/report/user_report_list 获取某一用户的所有周报
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 获取用户周报列表
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {number} id 周报的id
	 * @apiSuccess {number} author 周报作者的id
	 * @apiSuccess {String} content 周报的内容
	 * @apiSuccess {Timestamp} time 周报的创建时间
	 * @apiSuccess {isSubmit} id 周报的提交状态
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"result"
	 *     			{
	 *     	     	"Success" : true,
	 *      		"code" : 20000,
	 *      		"message" : "请求成功",
	 *      		"data" : 
	 *      			{
	 *      				{
	 *      				"id" : 8977
	 *      				"week" : 1,
	 * 						"author": 1234,
	 * 						"content":"没有内容",
	 * 						"time":"2020-9-1",
	 * 						"isSubmit":true
	 *      				},
	 *      				{
	 *      				"id" : 8978
	 *      				"week" : 2,
	 * 						"author": 1234,
	 * 						"content":"1234567",
	 * 						"time":"2020-9-2",
							"isSubmit":true
	 *      				}
	 *      			}
	 *     			}
	 *     }
	 */
	@RequestMapping("/user_report_list")
	public String user_report_list(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1")Integer pageIndex) {
		User requestUser = (User) session.getAttribute("loggedUser");
		request.setAttribute("result", new Result< List <Report> >(
					ResultCode.SUCCESS, 
					reportServer.get_report_list_by_user( requestUser.getId() )
					)
				);
		Page<Report> subjectPage = PageHelper.startPage(pageIndex, Constant.PAGE_NUMBER);
		request.setAttribute("page", subjectPage.toPageInfo() );
		return "userReportList";
	}
	
	/**
	 * @api {get} /TeacherHelper/report_info 获取周报详细信息
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 获取周报信息
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据
	 * @apiSuccess {number} id 周报的id
	 * @apiSuccess {number} author 周报作者的id
	 * @apiSuccess {String} content 周报的内容
	 * @apiSuccess {Timestamp} time 周报的创建时间
	 * @apiSuccess {isSubmit} id 周报的提交状态
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      	"id" : 8977
	 * 			"author": 1234,
	 * 			"content":"没有内容",
	 * 			"time":"2020-9-1",
	 * 			"isSubmit":true
	 *      	}
	 *     }
	 */
	@RequestMapping("/report_info")
	public String report_info(HttpServletRequest request, HttpSession session, Integer reportId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		//通过封装的方法获取数据库中的周报
		Result<Report> result = get_report_by_id(requestUser, reportId);
		request.setAttribute("result", result);
		switch( result.getResultCode() ) {
			case ERROR_REPORT_NOT_FOUNT : return "error/404";
			case ERROR_PERMISSION : 	  return "error/403";
			case SUCCESS : 				  return "reportInfo";
			default : 					  return "error/500";
		}
	}
	
	/**
	 * @api {post} /TeacherHelper/report/report_add 新增周报
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 新增周报
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} week 周报所属周
	 * @apiParam {string} content 周报内容
	 * @apiParam {boolean} isSubmit 是否提交, 若为true,则创建的周报将被设为只读的提交状态, 若为false,则存为草稿
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 * @apiParamExample {json} 请求示例:
	 *	{
	 *		"user"
	 * 			{
	 * 				"week" : 16,
	 * 				"content":"abcdefg",
	 * 				"isSubmit":true
	 * 			}
	 * 	}
	 */
	@RequestMapping("/report_add")
	public String report_add(HttpServletRequest request,HttpSession session, Report report) {
		//检测report中除id外是否存在空字段(time不进行检测)
		User requestUser = (User) session.getAttribute("loggedUser");
		report.setAuthor( requestUser.getId() );
		if( report.check(Constant.CHECK_ALL ^ Constant.CHECK_ID ^ Constant.CHECK_AUTHOR) != 0 ) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_PARAM, null));
			return "error/404";
		}
		report.setTerm(CacheUtil.currTerm.getId() );
		reportServer.insert_report(report);
		request.setAttribute("result", new Result<Boolean>(ResultCode.SUCCESS, null));
		return "redirect:user_report_list";
	}
	
	/**
	 * @api {post} /TeacherHelper/report/report_update 更新周报
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 更新周报
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} id 周报id
	 * @apiParam {number} week 周报所属周数
	 * @apiParam {string} content 周报内容
	 * @apiParam {boolean} isSubmit 是否提交, 若为true,则创建的周报将被设为只读的提交状态, 若为false,则存为草稿
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 * @apiParamExample {json} 请求示例:
	 *	{
	 *		"user"
	 * 			{
	 * 				"author":1234,
	 * 				"content":"abcdefg",
	 * 				"isSubmit":true
	 * 			}
	 * 	}
	 */
	@RequestMapping("/report_update")
	public String report_update(HttpServletRequest request, HttpSession session, Report report) {
		User requestUser = (User) session.getAttribute("loggedUser");
		Report dbReport;
		//检测report中的id和状态是否为空
		if( report.check(Constant.CHECK_ID | Constant.CHECK_SUBMIT) != 0 ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null));
			return "error/400";
		}
		//检测数据库中是否存在该周报
		if( (dbReport = reportServer.get_report_by_id( report.getId() ) ) == null ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_REPORT_NOT_FOUNT, null));
			return "error/404";
		//判断该周报是否属于请求用户
		} else if ( dbReport.getAuthor() != requestUser.getId() ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_REPORT_ALREADY_SUBMIT, null));
			return "error/403";
		//判断该周报是否为已提交(只读状态)
		} else if ( dbReport.getIsSubmit() == true) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_REPORT_ALREADY_SUBMIT, null));
			return "error/403";
		//判断周报的内容是否为空
		} else if(report.getContent() == null ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_REPORT_CONTENT_EMPTY, null));
			return "";
		}
		reportServer.update_report(report);;
		return "redirect:user_report_list";
	}
	
	/**
	 * @api {get} /TeacherHelper/report_delete 删除周报
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 删除周报
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据
	 * @apiSuccess {number} id 周报的id
	 * @apiSuccess {number} author 周报作者的id
	 * @apiSuccess {String} content 周报的内容
	 * @apiSuccess {Timestamp} time 周报的创建时间
	 * @apiSuccess {isSubmit} id 周报的提交状态
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      	"id" : 8977
	 * 			"author": 1234,
	 * 			"content":"没有内容",
	 * 			"time":"2020-9-1",
	 * 			"isSubmit":true
	 *      	}
	 *     }
	 */
	@RequestMapping("/report_delete")
	public String report_delete(HttpServletRequest request, HttpSession session, Integer reportId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		//通过封装的方法获取数据库中的周报
		Report dbReport = reportServer.get_report_by_id(reportId);
		if( dbReport == null ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_REPORT_NOT_FOUNT, null) );
			return "error/404";
		} else if ( dbReport.getAuthor() == requestUser.getId() ) {
			if( dbReport.getIsSubmit() == true ) {
				request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null) );
				return "error/403";
			}
			request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
			return "redirect:user_report_list";
		} else if ( requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		} else if ( dbReport.getIsSubmit() == false ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null) );
			return "error/403";
		}
		reportServer.delete_report(reportId);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
		return "redirect:report_list";
	}
	
	/**
	 * @api {post} /TeacherHelper/report/report_submit 提交某一周报
	 * @apiVersion 1.0.0
	 * @apiGroup Report
	 * @apiName 提交周报
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} reportId 所要提交周报的id
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 * @apiParamExample {json} 请求示例:
	 *	{
	 *		"reportId" : 1334
	 * 	}
	 */
	@RequestMapping("/report_submit")
	public String report_submit(HttpServletRequest request,HttpSession session, Integer reportId) {
		//检测report中除id外是否存在空字段(time不进行检测)
		User requestUser = (User) session.getAttribute("loggedUser");
		Report dbReport = reportServer.get_report_by_id(reportId);
		if ( dbReport == null) {
			request.setAttribute("result", new Result<Void>( ResultCode.ERROR_REPORT_NOT_FOUNT, null) );
			return "error/404";
		}
		if ( requestUser.getId() != dbReport.getAuthor() ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PERMISSION, null));
			return "error/403";
		}
		reportServer.update_report_state(reportId, true);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null));
		return "redirect:user_report_list";
	}
	
}
