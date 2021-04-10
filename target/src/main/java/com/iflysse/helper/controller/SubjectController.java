package com.iflysse.helper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.tools.Cache;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private TermDao termDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * @api {post} /TeacherHelper/subject/subject_add 新增科目
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 新增科目
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} name 该科目的名称
	 * @apiParam {number} type 该科目的课程类型, 0表示方向课, 1表示理论课
	 * @apiParam {number} teacher 该科目的教师id, 即登录用户的id
	 * @apiParam {string} ta 该科目的助教
	 * @apiParam {string} klass 该科目的班级
	 * @apiParam {number} timeTotal 该科目的总课时
	 * @apiParam {number} timeTheory 该科目的理论课时
	 * @apiParam {number} timePractice 该科目的实践课时
	 * @apiParam {number} term 学期的id, 默认当前学期
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"name":"如何对金钱失去兴趣",
	 * 		"type":0,
	 * 		"teacher" 1,
	 * 		"ta":"马云",
	 * 		"klass":"内卷学1班",
	 * 		"timeTotal":"66",
	 * 		"timeTheory":"33",
	 * 		"timePractice":"33"
	 * 	}
	 */
	@RequestMapping("/subject_add")
	public String subject_add(HttpServletRequest request, Subject newSubject) {
		if (newSubject.getTerm() == null) {
			newSubject.setTerm( Cache.termBuffer.getId() );
		}
		if(newSubject.check( Constant.CHECK_ALL ^ Constant.CHECK_TERM ^ Constant.CHECK_ID ) != 0 ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) );
			System.out.println(ResultCode.ERROR_PARAM.getMessage());
			return "error/400";
		}
		subjectDao.insert_subject(newSubject);
		System.out.println(newSubject.getId());
		
		try {
			Cache.subjectQueue.put(newSubject);
			
		} catch (InterruptedException e) {
			System.out.println ("subject队列发生异常 : " + e.toString() );
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_SERVER, null ) );
		}
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null ) );
		return "error/500";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/subject_update 更新科目信息
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 更新科目信息
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {string} name 该科目的名称
	 * @apiParam {number} type 该科目的课程类型, 0表示方向课, 1表示理论课
	 * @apiParam {string} ta 该科目的助教
	 * @apiParam {string} klass 该科目的班级
	 * @apiParam {number} subjectTimeTotal 该科目的总课时
	 * @apiParam {number} subjectTimeTheory 该科目的理论课时
	 * @apiParam {number} subjectTimePractice 该科目的实践课时
	 * @apiParam {number} id 科目名
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"name":"拾金不昧",
	 * 		"type":0,
	 * 		"ta":"窦尔敦",
	 * 		"klass":"不知道取什么名字",
	 * 		"subjectTimeTotal":"66",
	 * 		"subjectTimeTheory":"33",
	 * 		"subjectTimePractice":"33"
	 * 	}
	 */
	@RequestMapping("/subject_update")
	public String subject_update(HttpServletRequest request,Subject subject) {
		if( subject.check( Constant.CHECK_ALL ) != 0 ) {
			request.setAttribute("result", new Result<Subject>(ResultCode.ERROR_PARAM, null) );
		}
		subjectDao.update_subject(subject);
		request.setAttribute("result", new Result<Subject>(ResultCode.SUCCESS, null) );
		return "subjectInfo";
	}

	/**
	 * @api {get} /TeacherHelper/subject/subject_info 获取科目信息
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 获取科目信息
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {number} id 该科目的id
	 * @apiSuccess {string} name 该科目的名称
	 * @apiSuccess {number} type 该科目的课程类型, 0表示方向课, 1表示理论课
	 * @apiSuccess {string} teacher 讲师外键
	 * @apiSuccess {string} ta 该科目的助教
	 * @apiSuccess {string} klass 该科目的班级
	 * @apiSuccess {number} subjectTimeTotal 该科目的总课时
	 * @apiSuccess {number} subjectTimeTheory 该科目的理论课时
	 * @apiSuccess {number} subjectTimePractice 该科目的实践课时
	 * @apiSuccess {number} term 学期外键
	 * @apiParam {number} id 科目id
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      {
		 *      "id":778899,
		 * 		"name":"摸鱼学导论",
		 * 		"type":"理论课",
		 * 		"teacher":"马云"
		 * 		"ta":"刘备",
		 * 		"klass":"草鞋营销1班",
		 * 		"subjectTimeTotal":"66",
		 * 		"subjectTimeTheory":"33",
		 * 		"subjectTimePractice":"33",
		 * 		"term" : 2233
	 *      }
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"id":778899,
	 * 	}
	 */
	@RequestMapping("/subject_info")
	public String subject_info(HttpServletRequest request, Integer subjectId) {
		Subject dbSubject = subjectDao.get_subject_info(subjectId);
		if(dbSubject == null) {
			request.setAttribute( "result", new Result<Subject>( ResultCode.ERROR_SUBJECT_NOT_FOUND, null ) );
		}
		request.setAttribute( "result", new Result<Subject>(ResultCode.SUCCESS, dbSubject ) );
		return "subjectInfo";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/subject_list 获取科目列表
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 获取科目列表
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiSuccess {number} id 该科目的id
	 * @apiSuccess {string} name 该科目的名称
	 * @apiSuccess {number} type 该科目的课程类型, 0表示方向课, 1表示理论课
	 * @apiSuccess {string} ta 该科目的助教
	 * @apiSuccess {string} klass 该科目的班级
	 * @apiSuccess {number} subjectTimeTotal 该科目的总课时
	 * @apiSuccess {number} subjectTimeTheory 该科目的理论课时
	 * @apiSuccess {number} subjectTimePractice 该科目的实践课时
	 * @apiParam {number} termId=nullptr 学期Id, 若不指定则表示当前学期
	 * 	@apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : 
	 *      {
	 *      	"id":1778899,
		 * 		"name":"菊花鉴赏",
		 * 		"type":"方向课",
		 * 		"ta":"陶渊明",
		 * 		"klass":"植物栽培1班",
		 * 		"subjectTimeTotal":"66",
		 * 		"subjectTimeTheory":"33",
		 * 		"subjectTimePractice":"33",
		 * 		"term":1221
	 *      },
	 *   	{
	 *      	"id":2778899,
		 * 		"name":"植发与护理",
		 * 		"type":"方向课",
		 * 		"ta":"蒋介石",
		 * 		"klass":"头发护理学333班",
		 * 		"subjectTimeTotal":"66",
		 * 		"subjectTimeTheory":"33",
		 * 		"subjectTimePractice":"33",
		 * 		"term":1221
	 *      }
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"termId" : null,
	 * 		"userId" : 
	 * 	}
	 */
	@RequestMapping("/subject_list")
	public String subject_list(HttpServletRequest request, HttpSession session, Integer userId, Integer termId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		
		//判断用户请求的是否为自己的科目表, 若不是则需验证用户权限
		if (requestUser.getId() != userId ) {
			if(requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
				request.setAttribute( "result", new Result< List<Subject> >( ResultCode.ERROR_PERMISSION, null ) );
				return "error/403";
			}
			//若需要查询其他用户的课表, 则需检测该用户是否存在
			if( userDao.get_user_by_id(userId) == null ) {
				request.setAttribute( "result", new Result< List<Subject> >( ResultCode.ERROR_USER_NOT_FOUND, null ) );
				return "error/404";
			}
		}
		
		//验证传入的学期id
		if(termId == null) {
			termId = Cache.termBuffer.getId();
		}else if( termDao.get_term_by_id(termId) == null ) {
			request.setAttribute( "result", new Result< List<Subject> >( ResultCode.ERROR_TERM_NOT_FOUND, null ) );
			return "error/404";
		}
		
		//请求成功, 返回数据
		request.setAttribute( "result", 
								new Result< List<Subject> >( 
									ResultCode.SUCCESS,  
									subjectDao.get_subject_list(requestUser.getId(), termId)
								) 
							);
		return "subjectList";
	}
	
	/**
	 * @api {get} /subject/goto_create 跳转至新增课表页面
	 * @apiVersion 1.0.0
	 * @apiGroup Page
	 * @apiName 跳转至科目创建页面
	 * @apiDescription 接口说明.
	 * 该接口没有参数，也没有返回数据,仅进行页面切换
	 */
	@RequestMapping("/goto_subject_add")
	public String goto_subject_add() {
		return "subjectAdd";
	}
	
}