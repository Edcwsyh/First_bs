package com.iflysse.helper.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iflysse.helper.bean.Term;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.tools.Cache;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/term")
public class TermController {
	
	@Autowired
	private TermDao termDao;
	
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
		return new Result<Term>(ResultCode.SUCCESS, Cache.termBuffer);
	}
	
	/**
	 * @api {get} /term/term_list 获取当前学期列表
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
	 */
	@RequestMapping("/term_list")
	public String term_list(HttpServletRequest request) {
		request.setAttribute("result", new Result<List<Term>>(ResultCode.SUCCESS, termDao.get_term_list()));
		return "termList";
	}
	
	/**
	 * @api {get} /TeacherHelper/term/term_add 创建一个新的学期
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 创建一个新的学期
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {string} name 学期名
	 * @apiParam {number} state 学期状态，1表示激活，0表示不激活，若指定为激活，将设置新学期为当前学期
	 * @apiParam {number} requestId 执行请求的用户id
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
	 * 	    "requestId" : 173131,
	 *		"data":
	 * 			{
	 * 				"termName" : "2099年上半年秋季第12345学期",
	 * 				"state" : 1
	 * 			}
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用
	 */
	@RequestMapping("/term_add")
	public String term_add(HttpServletRequest request, Term newTerm) {
		if(newTerm.check( Constant.CHECK_ALL ) != 0) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) ); 
		}
		//判断新增的是否是激活的学期
		if(newTerm.getIsCurrent() ) {
			//关闭原激活学期,并更改当前缓存学期
			termDao.update_term_state(Cache.termBuffer.getId(), false); 
			Cache.termBuffer = newTerm;
		}
		termDao.insert_term(newTerm);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) ); 
		return "termList";
	}
	
	/**
	 * @api {get} /TeacherHelper/term/term_add 创建一个新的学期
	 * @apiVersion 1.0.0
	 * @apiGroup Term
	 * @apiName 创建一个新的学期
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据，该接口不返回任何数据
	 * @apiParam {string} name 学期名
	 * @apiParam {number} weeks 周数
	 * @apiParam {Date} startTime 学期开始时间
	 * @apiParam {number} state 学期状态，1表示激活，0表示不激活，若指定为激活，将设置新学期为当前学期
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
	 *		"data":
	 * 			{
	 * 				"termName" : "2099年上半年秋季第12345学期",
	 * 				"weeks" : 20,
	 * 				"startTime" : 2019-02-01,
	 * 				"state" : 1
	 * 			}
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用
	 */
	@RequestMapping("/term_update")
	public String term_update(HttpServletRequest request, Term term) {
		if(term.check( Constant.CHECK_ALL ) != 0) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) ); 
		}
		termDao.update_term(term);
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) ); 
		return "termList";
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
	 * @apiParam {number} requestId 执行请求的用户id
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : "null"
	 *	}
	 * @apiParamExample {json} 请求示例:
	 *	{	
	 * 	    "requestId":311241,
	 *		"data":
	 * 			{
	 * 				"termId" : 19921
	 * 			}
	 * 	}
	 * @apiDescription 接口说明.
	 * 该接口只允许管理员调用,
	 * 该接口调用成功后，原先激活的学期将失效
	 */
	@ResponseBody
	@RequestMapping("/term_activate")
	public Result<Void> term_activate(HttpServletRequest request, Integer termId) {
		System.out.print("接口调用");
		if(termId == Cache.termBuffer.getId() ) {
			return new Result<Void>(ResultCode.ERROR_TERM_ACTIVATE, null);
		}
		Term term = termDao.get_term_by_id(termId);
		if(term == null) {
			return new Result<Void>(ResultCode.ERROR_TERM_NOT_FOUND, null);
		} else {
			termDao.update_term_state(term.getId(), true);
			termDao.update_term_state(Cache.termBuffer.getId(), false);
			Cache.termBuffer = term;
			return new Result<Void>(ResultCode.SUCCESS, null);
		}
	}
	
}
