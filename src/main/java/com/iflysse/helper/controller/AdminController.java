package com.iflysse.helper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserDao userDao;

	/**
	 * @api {post} /admin/user_list 获取用户列表
	 * @apiVersion 1.0.0
	 * @apiGroup Admin
	 * @apiName 获取用户列表
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {Object} data 返回的数据
	 * @apiSuccessExample {json} 请求成功例子:
	 *     {
	 *     	"Success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      	{
	 *      	"user" : 
	 *      		{
	 *      		"id" : 8977
	 * 				"username":"supper man",
	 * 				"password":"1234567",
	 * 				"realname":"张三",
	 * 				"mail":"123456@gmail.com",
	 * 				"phone":"13960241683",
	 * 				"gender":1,
	 * 				"permission":1
	 *      		},
	 *      	"user":
	 *      		{
	 *      		"id" : 8978
	 *      		"username":"supper woman",
	 * 				"password":"1234567",
	 * 				"realname":"李四",
	 * 				"mail":"12345677@gmail.com",
	 * 				"phone":"15960241683",
	 * 				"gender":1,
	 * 				"permission":2
	 *      		}
	 *      	}
	 *     }
	 */
	public String user_list(HttpServletRequest request) {
		request.setAttribute(
				"result", 
				new Result< List<User> >(ResultCode.SUCCESS, userDao.get_user_list() ) );
		return "userList";
	}
	
	/**
	 * @api {post} /TeacherHelper/admin/user_add 新增用户
	 * @apiVersion 1.0.0
	 * @apiGroup Admin
	 * @apiName 新增用户
	 * @apiSuccess {Boolean} Success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} username 用户名, 长度为3-16位, 只能由字母、数字及下划线组成，且首字符不能为数字
	 * @apiParam {string} password 密码, 长度位6至64位
	 * @apiParam {string} realName="未知" 真实姓名
	 * @apiParam {string} mail 邮箱
	 * @apiParam {string} phone 电话号码，长度为11位的中国电话号码
	 * @apiParam {number} gender=0 性别， 0表示不填写性别，1表示男，2表示女
	 * @apiParam {number} permission 权限, 0表示管理员, 1表示普通用户, 2表示经理
	 * @apiParam {string} code 图片验证码
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
	 * 				"permission":1
	 * 			}
	 * 	}
	 * @apiDescription 接口说明:
	 * 	 该接口只允许管理员调用
	 */
	@RequestMapping("/user_add")
	public String user_register(HttpServletRequest request, User user) {
		if(user == null) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_PARAM, null));
			return "error/400";
		}
		if(userDao.get_user_by_ump(user) != null) {
			request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_USER_EXIST, null));
			return  "error/409";
		}
		userDao.insert_user(user);
		request.setAttribute("result", new Result<Boolean>(ResultCode.ERROR_PARAM, null));
		return "redirect:/userList";
	}
}
