package com.iflysse.helper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iflysse.helper.bean.Report;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.service.ReportServer;
import com.iflysse.helper.service.SubjectServer;
import com.iflysse.helper.service.TermServer;
import com.iflysse.helper.tools.CacheUtil;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/term")
public class TermController {
	
	@Autowired
	private SubjectServer subjectServer;
	
	@Autowired
	private TermServer termServer;
	
	@Autowired
	private ReportServer reportServer;	
	/**
	 * @api {get} /term/term_current 获取当前学期信息
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 获取当前学期id
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {number} id 学期的id
	 * @apiSuccess {string} name 学期的的名称
	 * @apiSuccess {number} state 学期的状态
	 * @apiVersion 1.0.0
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      		"id" : "123456",
	 *      		"name" : "2099年上半年秋季第12345学期",
	 *      		"state" : 1
	 *      	}
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口没有参数，但只允许登录后调用
	 * 学期id缓存在后端，若需频繁获取学期信息，应当使用本接口
	 */	
	@ResponseBody
	@RequestMapping("/term_current")
	public Result<Term> term_current(HttpServletRequest request) {
		return new Result<Term>(ResultCode.SUCCESS, CacheUtil.currTerm);
	}
	
	/**
	 * @api {get} /term/term_list 获取当前学期列表
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 获取学期列表
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {number} id 学期的id
	 * @apiSuccess {string} name 学期的的名称
	 * @apiSuccess {number} state 学期的状态
	 * @apiVersion 1.0.0
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      		"id" : "123456",
	 *      		"name" : "2099年上半年秋季第12345学期",
	 *      		"state" : 1
	 *      	}
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口没有参数，但只允许登录后调用
	 */
	@RequestMapping("/term_list")
	public String term_list(HttpServletRequest request , @RequestParam(defaultValue = "1")Integer pageIndex) {
		request.setAttribute( "result", new Result<List<Term>>(ResultCode.SUCCESS, termServer.get_term_list() ) );
		Page<Term> termPage = PageHelper.startPage(pageIndex, Constant.PAGE_NUMBER);
		request.setAttribute("page", termPage.toPageInfo() );
		return "termList";
	}
	
	/**
	 * @api {post} /TeacherHelper/term/term_add 创建一个新的学期
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 创建一个新的学期
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {string} name 学期名
	 * @apiParam {number} isCurrent 学期状态，1表示激活，0表示不激活，若指定为激活，将设置新学期为当前学期
	 * @apiVersion 1.0.0
	 * @apiSuccessExample {json} 请求成功示例:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : "null"
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{	
	 * 		"termName" : "2099年上半年秋季第12345学期",
	 * 		"isCurrent" : 1
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用
	 */
	@RequestMapping("/term_add")
	public String term_add(HttpServletRequest request, Term newTerm) {
		if(newTerm.check( Constant.CHECK_ALL ^ Constant.CHECK_ID ) != 0) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) ); 
			return "error/400";
		}
		//判断新增的是否是激活的学期
		if(newTerm.getIsCurrent() ) {
			//关闭原激活学期,并更改当前缓存学期
			termServer.update_term_state( CacheUtil.currTerm.getId(), false ); 
			CacheUtil.currTerm = newTerm;
		}
		termServer.insert_term(newTerm);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) ); 
		return "redirect:term_list";
	}
	
	/**
	 * @api {post} /TeacherHelper/term/term_delete 删除一个学期
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 删除学期
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {number} termId 学期的id
	 * @apiVersion 1.0.0
	 * @apiSuccessExample {json} 请求成功示例:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : "null"
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{	
	 *		"termId" : 学期id
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用
	 */
	@RequestMapping("/term_delete")
	public String term_delete(HttpServletRequest request, Integer termId) {
		if (CacheUtil.currTerm.getId() == termId) {
			System.out.println("已拦截  - 删除当前学期");
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_TERM_DELETE_ACTION, null) ); 
			return "redirect:term_list";
		}
		List<Subject> subjectList = subjectServer.get_subject_list_by_term(termId);
		List<Report> reportList = reportServer.get_report_list_by_term(termId);
		if ( subjectList != null && subjectList.size() != 0 && 
				reportList != null && reportList.size() != 0) {
			System.out.println("已拦截  - 删除被引用学期");
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_TERM_NOT_EMPTY, null) ); 
			return "redirect:term_list";
		}
		termServer.delete_term(termId);
		System.out.println("请求通过  - 删除学期id : " + termId);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) ); 
		return "redirect:term_list";
	}
	
	/**
	 * @api {get} /TeacherHelper/term/term_update 更新某一学期的信息
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 更新学期
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {number} id 学期的id
	 * @apiParam {string} name 学期名
	 * @apiParam {number} weeks 周数
	 * @apiParam {Date} startTime 学期开始时间
	 * @apiVersion 1.0.0
	 * @apiSuccessExample {json} 请求成功示例:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : "null"
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{	
	 * 		"id" : 172,
	 * 		"name" : "2099年上半年秋季第12345学期",
	 * 		"weeks" : 20,
	 * 		"startTime" : 2019-02-01,
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用
	 */
	@RequestMapping("/term_update")
	public String term_update(HttpServletRequest request, Term term) {
		if(term.check( Constant.CHECK_ALL ) != 0) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) ); 
			return "error/404";
		}
		termServer.update_term_info(term);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) ); 
		return "redirect:term_list";
	}
	
	/**
	 * @api {get} /TeacherHelper/term/goto_term_update 跳转到更新学期页面
	 * @apiVersion 1.0.0
	 * @apiGroup page
	 * @apiName 更新学期页面
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {number} termId 学期id
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      	"id" : 172,
	 * 			"name" : "2099年上半年秋季第12345学期",
	 * 			"weeks" : 20,
	 * 			"startTime" : 2019-02-01,
	 *      	}
	 *	}
	 * @apiParamExample {json} 请求示例:
	 *	{	
	 * 		"termId" : 19921
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用,
	 */
	@RequestMapping("/goto_term_update")
	public String goto_term_update(HttpServletRequest request, Integer termId) {
		if ( termId == null ) {
			request.setAttribute("result", new Result<Term>(ResultCode.ERROR_PARAM, null) ); 
			return "error/404";
		}
		Term term = termServer.get_term_by_id(termId);
		if ( term == null ) {
			request.setAttribute("result", new Result<Term>(ResultCode.ERROR_TERM_NOT_FOUND, null) ); 
			return "error/404";
		}
		request.setAttribute("result", new Result<Term>(ResultCode.SUCCESS, term) ); 
		return "termUpdate";
	}
	
	/**
	 * @api {get} /TeacherHelper/term/term_activate 激活某一学期
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 激活某一学期
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {number} termId 学期id
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : "null"
	 *	}
	 * @apiParamExample {json} 请求示例:
	 *	{	
	 * 		"termId" : 19921
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用,
	 * 该接口调用成功后，原先激活的学期将失效
	 */
	@RequestMapping("/term_activate")
	public String term_activate(HttpServletRequest request, Integer termId) {
		if(termId == CacheUtil.currTerm.getId() ) {
			System.out.println("已拦截  - 当前学期已被激活");
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_TERM_ACTIVATE, null) );
			return "error/403";
		}
		Term term = termServer.get_term_by_id(termId);
		if(term == null) {
			System.out.println("已拦截  - 新的学期为空");
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_TERM_NOT_FOUND, null) );
			return "error/404";
		} else {
			termServer.term_activate(CacheUtil.currTerm, term);
			CacheUtil.currTerm = term;
			System.out.println("请求通过 - 已更改当前激活学期");
			request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
			return "redirect:term_list";
		}
	}
	
}
