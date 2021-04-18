package com.iflysse.helper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iflysse.helper.bean.User;
import com.iflysse.helper.service.UserServer;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserServer userServer;
	
	/**
	 * @api {post} /TeacherHelper/login/login_check 检测用户
	 * @apiVersion 1.0.0
	 * @apiGroup Login
	 * @apiName 检测用户
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {object} null， 该接口不返回数据， 请通过接口的错误信息判断用户是否重复
	 * @apiParam {number} field 检测字段, 0表示用户名, 1表示邮箱, 2表示电话号码
	 * @apiParam {string} value
	 * @apiSuccessExample {json} 请求成功例子:
	 * 		"result" : 
	 * 		{
	 * 			{
	 *     		"Success" : true,
	 *      	"code" : 20000,
	 *      	"message" : "请求成功",
	 *      	"data" : null
	 *     		}
	 *     	}
	 * @apiParamExample {json} 请求示例:
	 * 		{
	 * 		"field" : 0,
	 * 		"value" : "supperman"
	 * 		}
	 * @apiDescription 接口说明.
	 * 该接口返回的类型 : 成功(不存在重复信息)、用户已存在、错误的参数
	 */
	@ResponseBody
	@RequestMapping("/login_check")
	public Result<Void> login_check(@RequestParam Byte field, @RequestParam String value) {
		User dbUser = userServer.get_user(field, value);
		if (dbUser != null) {
			return new Result<Void>(ResultCode.SUCCESS, null);
		}
		switch ( field.intValue() ) {
			case Constant.FIELD_TYPE_USERNAME : return new Result<Void>(ResultCode.ERROR_USER_EXIST, null);
			case Constant.FIELD_TYPE_MAIL : return new Result<Void>(ResultCode.ERROR_USER_EXIST, null);
			case Constant.FIELD_TYPE_PHONE : return new Result<Void>(ResultCode.ERROR_PHONE_EXIST, null);
			default : return new Result<Void>(ResultCode.ERROR_PARAM, null);
		}
	}
	
	/**
	 * @api {post} /TeacherHelper/login/login 用户登录
	 * @apiVersion 1.0.0
	 * @apiGroup Login
	 * @apiName 用户登录
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {User} user 登录的用户信息
	 * @apiParam {string} username 用户名
	 * @apiParam {string} password 密码
	 * @apiSuccessExample {json} 请求成功例子:
	 *     	"result":
	 *     	{
	 *     		{
	 *     		"success" : true,
	 *      	"code" : 20000,
	 *      	"message" : "请求成功",
	 *      	"data" : null
	 *     		}
	 *  	},
	 *  	"user" : 
	 *  	{
	 *  		"username":"supper man",
	 * 			"password":"1234567",
	 * 			"realname":"张三",
	 * 			"mail":"123456@gmail.com",
	 * 			"phone":"13960241683",
	 * 			"gender":1,
	 * 			"permission":1  
	 *  	}
	 * @apiParamExample {json} 请求样示例:
	 * 		{
	 * 		"username":"supper man",
	 * 		"password":"1234567",
	 * 		}
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpSession session, User user) {
		//判断传入的参数是否为空
		if(user.getUsername() == null || user.getPassword() == null) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_PARAM, null));
		}
		User dbUser  = userServer.get_user(Constant.FIELD_TYPE_USERNAME, user.getUsername() );
		//判断该用户是否存在
		if(dbUser == null) { 
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_USER_NOT_FOUND, null));
		}
		//判断该用户是否处于锁定状态
		else if(dbUser.getState() == Constant.USER_STATE_FREEZE) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_USER_FREEZE, null));
		}
		//校验密码
		else if(dbUser.getPassword().equals(user.getPassword())) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.SUCCESS, null));
			session.setAttribute("loggedUser", dbUser);
			return "redirect:/login/goto_index";
		} else {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_USER_PASSWORD, null));
		}
		return "login";
	}
	
	/**
	 * @api {post} /TeacherHelper/login/retrieve_password 忘记密码
	 * @apiVersion 1.0.0
	 * @apiGroup Login
	 * @apiName 忘记密码
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} value, 该字段可以是用户名也可以是邮箱
	 * @apiSuccessExample {json} 请求成功例子:
	 * 		"result" : 
	 * 		{
	 * 			{
	 *     		"Success" : true,
	 *      	"code" : 20000,
	 *      	"message" : "请求成功",
	 *      	"data" : true
	 *     		}
	 *     }
	 * @apiParamExample {json} 请求示例:
	 * 		{
	 * 		"value" : "supper man"
	 * 		}
	 */
//	@RequestMapping("/retrieve_password")
//	public Result<Boolean> retrieve_password(HttpServletRequest request) {
//		
//		return new Result<Boolean>(ResultCode.SUCCESS, true);
//	}
//	
	
	/**
	 * @api {get} /TeacherHelper/login/goto_login 跳转至登录页面
	 * @apiVersion 1.0.0
	 * @apiGroup Page
	 * @apiName 跳转至登录页面
	 * @apiDescription 接口说明.
	 * 该接口没有参数，也没有返回数据,仅进行页面切换
	 */
	@RequestMapping("/goto_login")
	public String goto_login() {
		return "login";
	}
	
	/**
	 * @api {get} /TeacherHelper/login/goto_register 跳转至注册页面
	 * @apiVersion 1.0.0
	 * @apiGroup Page
	 * @apiName 跳转至注册页面
	 * @apiDescription 接口说明.
	 * 该接口没有参数，也没有返回数据,仅进行页面切换
	 */
	@RequestMapping("/goto_register")
	public String goto_register() {
		return "register";
	}
	
	/**
	 * @api {get} /TeacherHelper/login/goto_forgot 跳转至找回密码页面
	 * @apiVersion 1.0.0
	 * @apiGroup Page
	 * @apiName 跳转至忘记密码
	 * @apiDescription 接口说明.
	 * 该接口没有参数，也没有返回数据,仅进行页面切换
	 */
//	@RequestMapping("/goto_forgot")
//	public String goto_forgot() {
//		return "";
//	}
	
	@RequestMapping("/goto_index")
	public String goto_index() {
		return "index";
	}
	
	/**
	 * @api {post} /TeacherHelper/login/logout 用户退出登录
	 * @apiVersion 1.0.0
	 * @apiGroup Login
	 * @apiName 用户退出
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login/goto_login";
	}
	
}
