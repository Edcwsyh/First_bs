package com.iflysse.helper.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.service.UserServer;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserServer userServer;
	
	@RequestMapping("/goto_user_update_password")
	public String goto_user_update_password(HttpServletRequest request) {
		return "userUpdatePassword";
	}
	
	/**
	 * @api {post} /TeacherHelper/user/user_update 修改用户
	 * @apiVersion 1.0.0
	 * @apiGroup User
	 * @apiName 修改用户信息
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} username 用户名, 长度为3-16位, 只能由字母、数字及下划线组成，且首字符不能为数字
	 * @apiParam {string} password 密码, 长度位6至64位
	 * @apiParam {string} realname 真实姓名
	 * @apiParam {string} mail 邮箱
	 * @apiParam {string} phone 电话号码，长度为11位的中国电话号码
	 * @apiParam {number} gender 性别， 0表示不填写性别，1表示男，2表示女
	 * @apiParam {number} permission=当前值  用户权限, 0表示管理员, 1表示普通用户, 2表示经理
	 * @apiParam {string} requestId 提出请求的用户id
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 * @apiParamExample {json} 请求示例:
	 * 	{	
	 * 		requestId : 75546
	 *		data:
	 * 			{
	 * 				"username":"supper man",
	 * 				"password":"1234567",
	 * 				"realname":"张三",
	 * 				"mail":"123456@gmail.com",
	 * 				"phone":"13960241683",
	 * 				"gender":1,
	 * 				"permission":1,
	 * 			}
	 * 	}
	 * 	@apiDescription 接口说明:
	 *  该接口只更新用户信息, 不更新用户密码, 权限, 状态
	 */
	@RequestMapping("/user_update")
	public String user_update(HttpServletRequest request, User user) {
		//检测除ID和密码外的字段是否为空
		Result<Void> result = userServer.user_update_info(user);
		request.setAttribute("result",  result);
		switch( result.getResultCode() ) {
			case SUCCESS : return "redirect:/user/user_info?id=" + user.getId();
			case ERROR_PARAM : return "error/400";
			default : return "error/500";
		}
		
	}
	
	/**
	 * @api {post} /TeacherHelper/user/user_update_password 修改用户密码
	 * @apiVersion 1.0.0
	 * @apiGroup User
	 * @apiName 修改用户信息
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} oldPassword 用户旧密码
	 * @apiParam {string} newPassword 用户新密码
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 * @apiParamExample {json} 请求示例:
	 * 	{	
	 *		"oldPassword" : 123456,
	 *		"newPassword" : 9921312
	 * 	}
	 * 	@apiDescription 接口说明:
	 *  该接口只更新用户密码
	 */
	@RequestMapping("/user_update_password")
	public String user_update_password(HttpServletRequest request, HttpSession session) {
		User loggedUser = (User) session.getAttribute("loggedUser");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		//判断前端参数是否为空
		if(oldPassword == null || newPassword == null) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) );
		}
		//校验用户密码
		else if ( !oldPassword.equals( loggedUser.getPassword() ) ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_USER_PASSWORD, null) );
		}
		//检测新密码是否与旧密码一致
		else if ( oldPassword.equals( newPassword ) ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_USER_SAME_OLD_NEW_PASSWORD, null) );
		}
		else {
			loggedUser.setPassword(newPassword);
			userServer.user_update_password(loggedUser);
			request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null) );
			//修改成功重新登录
			session.invalidate();
			return "redirect:/login/login";
		}
		return "userUpdatePassword";
	}
	
	/**
	 * @api {post} /TeacherHelper/user/user_register 注册用户
	 * @apiVersion 1.0.0
	 * @apiGroup User
	 * @apiName 注册用户
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} username 用户名, 长度为3-16位, 只能由字母、数字及下划线组成，且首字符不能为数字
	 * @apiParam {string} password 密码, 长度位6至64位
	 * @apiParam {string} realName 真实姓名
	 * @apiParam {string} mail 邮箱
	 * @apiParam {string} phone 电话号码，长度为11位的中国电话号码
	 * @apiParam {number} gender 性别， 0表示不填写性别，1表示男，2表示女
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
	 * 				"username":"supper man",
	 * 				"password":"1234567",
	 * 				"realname":"张三",
	 * 				"mail":"123456@gmail.com",
	 * 				"phone":"13960241683",
	 * 				"gender":1,
	 * 			}
	 * 	}
	 */
	@RequestMapping("/user_register")
	public String user_register(HttpServletRequest request, User user) {
		if ( user.check(Constant.CHECK_ALL) != 0 ) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_USER_EXIST, null));
			return "error/400";
		}
		
		//查询用户名、邮箱、电话号码是否已经被注册
		if (userDao.get_user_by_ump(user) != null) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_USER_EXIST, null));
			return  "error/409";
		}
		userDao.insert_user(user);
		request.setAttribute("result", new Result<Boolean>(ResultCode.SUCCESS, null));
		return "login";
	}
	
	/**
	 * @api {get} /TeacherHelper/user/user_info 获取用户信息
	 * @apiVersion 1.0.0
	 * @apiGroup User
	 * @apiName 获取用户信息
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {number} userId 获取的用户的id, 该字段仅用户本人和管理员可见
	 * @apiSuccess {string} username 该用户的用户名
	 * @apiSuccess {string} password 该用户的密码, 该字段仅管理员及用户本人可见
	 * @apiSuccess {string} realName 该用户的真实姓名, 该字段仅用户本人可见
	 * @apiSuccess {string} mail 该用户的邮箱, 该字段仅管理员及用户本人可见
	 * @apiSuccess {string} phone 该用户的手机号码, 该字段仅用户本人和管理员可见
	 * @apiSuccess {number} gender 该用户的性别, 0表示不透露性别, 1表示男性, 2表示女性
	 * @apiSuccess {number} permission 该用户的权限, 该字段仅用户本人管理员可见, 0表示管理员, 1表示普通用户, 2表示经理
	 * @apiSuccess {number} state 该用户的状态, 该字段仅用户本人管理员可见, 0表示被封禁, 1表示状态正常, 
	 * @apiParam {string} requestId 发出请求的用户id
	 * @apiError 21011 未找到用户
	 * @apiError 21010 用户密码错误
	 * @apiParam {string} password 发出请求的用户的密码
	 * @apiParam {string} id 需要获取的用户的Id
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"result":
	 *     		{
	 *     		"success" : true,
	 *      	"code" : 20000,
	 *      	"message" : "请求成功",
	 *      	"data" : 
	 *      		{
	 *      			"userId" : 8977
	 * 					"username":"supper man",
	 * 					"password":"1234567",
	 * 					"realname":"张三",
	 * 					"mail":"123456@gmail.com",
	 * 					"phone":"13960241683",
	 * 					"gender":1,
	 * 					"permission":1,
	 * 					"state" : 1
	 *     			}
	 *     		}
	 *     }
	 * @apiErrorExample {json} Error-Response:
	 *     {
	 *     	"result":
	 *     		{
	 *     		"success" : false,
	 *      	"code" : 21010,
	 *      	"message" : "用户密码错误",
	 *      	"data" : null
	 *     		}
	 *     }
	 * @apiParamExample {json} 请求示例:
	 *	{
	 *		"id" : 998811,
	 * 	}
	 */
	@RequestMapping("/user_info")
	public String user_info(HttpServletRequest request, HttpSession session, @RequestParam Integer id) {
		User requestUser = (User) session.getAttribute("loggedUser");
		User user = null;
		if( requestUser == null ) {
			request.setAttribute("result", new Result<User>(ResultCode.ERROR_NOT_LOGIN, null));
			return "error/403";
		}
		else if( (user = userDao.get_user_by_id(id) ) == null) {
			request.setAttribute("result", new Result<User>(ResultCode.ERROR_USER_NOT_FOUND, null));
			return "error/404";
		}
		//判断请求者是否是用户自己或管理员, 如果不是, 则隐藏部分属性
		else if( !(requestUser.getId() == id || requestUser.getPermission() == Constant.USER_PERMISSION_ADMIN)) {
			user.setId(Constant.ID_UNKNOW);
			user.setMail("不可见");
			user.setPassword("不可见");
			user.setPhone("不可见");
			user.setRealName("不可见");
			user.setState(Constant.USER_STATE_UNKNOW);
			user.setPermission(Constant.USER_PERMISSION_UNKNOW);
		}
		request.setAttribute("result", new Result<User>(ResultCode.SUCCESS, user) );
		return "userInfo";
	}
	
	/**
	 * @api {post} /TeacherHelper/user/user_lock 锁定用户
	 * @apiVersion 1.0.0
	 * @apiGroup user
	 * @apiName 锁定用户
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} lockUserId 被锁定的用户的id
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *     }
	 * @apiParamExample {json} 请求示例:
	 *	{
	 *	    "lockUserId":"877661"
	 * 	}
	 * @apiDescription 接口说明:
	 *  用户注销账户应该调用此接口，
	 */
	@RequestMapping("/user_lock")
	public String user_lock(HttpServletRequest request, HttpSession session) {
		User requestUser = (User) session.getAttribute("loggedUser");
		User lockUser = null;
		Integer lockUserId = (Integer) request.getAttribute("lockUserId");
		if(requestUser == null ) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_NOT_LOGIN,null));
			return "error/403";
		}
		if(lockUserId == null) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PARAM,null));
			return "error/400";
		}
		//判断请求锁定的是否是用户自己
		if( requestUser.getId() != lockUserId ) {
			//判断发出请求的是否为管理员
			if ( requestUser.getPermission() != Constant.USER_PERMISSION_ADMIN) {
				request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION,null));
				return "error/403";
			}
			//判断被锁定的用户是否存在
			if ( ( lockUser = userDao.get_user_by_id(lockUserId) ) == null ) {
				request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_USER_NOT_FOUND,null));
				return "error/404";
			}
			//判断该用户的权限 (管理员用户不可被冻结！)
			else if ( lockUser.getPermission() == Constant.USER_PERMISSION_ADMIN ) {
				request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION,null));
				return "error/403";
			}
			userDao.update_user_state(lockUserId, Constant.USER_STATE_FREEZE);
			return "redirect:/userList";
		}
		//判断该用户的权限 (管理员用户不可被冻结！)
		if(requestUser.getPermission() == Constant.USER_PERMISSION_ADMIN) {
			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION,null));
			return "error/403";
		}
		userDao.update_user_state(lockUserId, Constant.USER_STATE_FREEZE);
		return "redirect:/userInfo" + lockUserId;
	}
	
}
