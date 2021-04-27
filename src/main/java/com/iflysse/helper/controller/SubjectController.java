package com.iflysse.helper.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iflysse.helper.bean.CourseVO;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.CourseDao;
import com.iflysse.helper.dao.SubjectDao;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.dao.TimeDao;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.service.SubjectServer;
import com.iflysse.helper.service.TermServer;
import com.iflysse.helper.tools.CacheUtil;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.ExcelUtil;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;
import com.iflysse.helper.tools.WordUtil;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	private TermDao termDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private SubjectServer subjectServer;
	
	@Autowired 
	private TermServer termServer;
	
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
			newSubject.setTerm( CacheUtil.currTerm.getId() );
		}
		if(newSubject.check( Constant.CHECK_ALL ^ Constant.CHECK_TERM ^ Constant.CHECK_ID ) != 0 ) {
			request.setAttribute("result", new Result<Void>(ResultCode.ERROR_PARAM, null) );
			System.out.println(ResultCode.ERROR_PARAM.getMessage());
			return "error/400";
		}
		subjectServer.insert_subject(newSubject);
		System.out.println(newSubject.getId());
		request.setAttribute("result", new Result<Void>(ResultCode.SUCCESS, null ) );
		return "timeList";
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
	 * @apiParam {number} timeTotal 该科目的总课时
	 * @apiParam {number} timeTheory 该科目的理论课时
	 * @apiParam {number} timePractice 该科目的实践课时
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
	 * 		"timeTotal":"66",
	 * 		"timeTheory":"33",
	 * 		"timePractice":"33"
	 * 	}
	 */
	@RequestMapping("/subject_update")
	public String subject_update(HttpServletRequest request,Subject subject) {
		if( subject.check( Constant.CHECK_ALL ^ Constant.CHECK_TERM ) != 0 ) {
			request.setAttribute("result", new Result<Subject>(ResultCode.ERROR_PARAM, null) );
			return "error/400";
		}
		subjectServer.update_subject(subject);
		request.setAttribute("result", new Result<Subject>(ResultCode.SUCCESS, null) );
		return "redirect:subject_list";
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
	 * @apiSuccess {number} timeTotal 该科目的总课时
	 * @apiSuccess {number} timeTheory 该科目的理论课时
	 * @apiSuccess {number} timePractice 该科目的实践课时
	 * @apiSuccess {number} term 学期外键
	 * @apiParam {number} subjectId 科目id
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
		 * 		"timeTotal":"66",
		 * 		"timeTheory":"33",
		 * 		"timePractice":"33",
		 * 		"term" : 2233
	 *      }
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"subjectId":778899,
	 * 	}
	 */
	@RequestMapping("/subject_info")
	public String subject_info(HttpServletRequest request, HttpSession session, Integer subjectId) {
		User requestUser = (User) session.getAttribute("loggedUser");
		Subject dbSubject = subjectServer.get_subject_by_id(subjectId);
		if(dbSubject == null) {
			request.setAttribute( "result", new Result<Subject>( ResultCode.ERROR_SUBJECT_NOT_FOUND, null ) );
			return "error/404";
		}
		if( requestUser.getId() != dbSubject.getTeacher() ) {
			if(requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
				request.setAttribute( "result", new Result<Subject>( ResultCode.ERROR_PARAM, null ) );
				return "error/400";
			}
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
	 * @apiSuccess {number} timeTotal 该科目的总课时
	 * @apiSuccess {number} timeTheory 该科目的理论课时
	 * @apiSuccess {number} timePractice 该科目的实践课时
	 * @apiParam {number} termId=nullptr 学期Id, 若不指定则表示当前学期
	 * @apiParam {number} userId 用户Id
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
		 * 		"timeTotal":"66",
		 * 		"timeTheory":"33",
		 * 		"timePractice":"33",
		 * 		"term":1221
	 *      },
	 *   	{
	 *      	"id":2778899,
		 * 		"name":"植发与护理",
		 * 		"type":"方向课",
		 * 		"ta":"蒋介石",
		 * 		"klass":"头发护理学333班",
		 * 		"timeTotal":"66",
		 * 		"timeTheory":"33",
		 * 		"timePractice":"33",
		 * 		"term":1221
	 *      }
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"termId" : null,
	 * 		"userId" : 1123
	 * 	}
	 */
	@RequestMapping("/subject_list")
	public String subject_list(HttpServletRequest request, HttpSession session, Integer userId, Integer termId, 
			@RequestParam(defaultValue = "1")Integer pageIndex ) {
		User requestUser = (User) session.getAttribute("loggedUser");
		//判断用户请求的是否为自己的科目表, 若不是则需验证用户权限
		if(userId == null) {
			userId = requestUser.getId();
		} else if (requestUser.getId() != userId ) {
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
			termId = CacheUtil.currTerm.getId();
		}else {
			if(termDao.get_term_by_id(termId) == null ) {
				request.setAttribute( "result", new Result< List<Subject> >( ResultCode.ERROR_TERM_NOT_FOUND, null ) );
				return "error/404";
			}
		}
		Page<Subject> subjectPage = PageHelper.startPage(pageIndex, Constant.PAGE_NUMBER);
		//请求成功, 返回数据
		request.setAttribute( "result", 
								new Result< List<Subject> >( 
									ResultCode.SUCCESS,  
									subjectServer.get_subject_list(userId, termId)
								) 
							);
		request.setAttribute("page", subjectPage.toPageInfo() );
		return "subjectList";
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/subject_delete 删除某一科目
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 删除某一科目
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} subjectId 科目id
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"subjectId":"1167"
	 * 	}
	 */
	@RequestMapping("/subject_delete")
	public String subject_delete(HttpServletRequest request, HttpSession session, Integer subjectId) {
		System.out.println("调用删除接口");
		User requestUser = (User) session.getAttribute("loggedUser");
		if(subjectId == null ) {
			request.setAttribute("result", new Result<Subject>(ResultCode.ERROR_PARAM, null) );
			return "error/403";
		}
		Subject subject = subjectServer.get_subject_by_id(subjectId);
		//权限验证
		if(subject.getTeacher() != requestUser.getId() ) {
			if(requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
				System.out.println("已拦截 - 请求用户权限不足");
				request.setAttribute("result", new Result<Subject>(ResultCode.ERROR_PERMISSION, null) );
				return "error/403";
			}
		}
		//验证被删除的科目是否非空
		if ( subjectServer.get_time_by_subject(subjectId).size() != 0 || 
				subjectServer.get_courseVO_by_subject(subjectId).size() != 0 ) {
			System.out.println("已拦截 - 试图删除非空科目");
			request.setAttribute("result", new Result<Subject>(ResultCode.ERROR_SUBJECT_NOT_EMPTY, null) );

		}
		subjectServer.delete_subject(subjectId);
		request.setAttribute("result", new Result<Subject>(ResultCode.SUCCESS, null) );
		System.out.println("请求成功 - 已删除空的科目");
		return "redirect:subject_list?userId=" + requestUser.getId();
	}
	
	/**
	 * @api {post} /TeacherHelper/subject/export_excel 导出-excel
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 导出-excel
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} subjectId 科目id
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"subjectId":"1167"
	 * 	}
	 * @apiDescription 接口说明:
	 * 	 调用成功后返回到科目列表
	 */
	@RequestMapping("/export_excel")
	public ResponseEntity<byte[]> export_excel(HttpServletRequest request, HttpSession session, Integer subjectId) {
		try {
			User requestUser = (User) session.getAttribute("loggedUser");
			Subject subject = subjectServer.get_subject_by_id(subjectId);
			if ( requestUser.getId() != subject.getTeacher() && requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
				System.out.println("已拦截 - 请求用户权限不足");
				throw new Exception(ResultCode.ERROR_PERMISSION.getMessage() );
			}
			List<CourseVO> courseList = subjectServer.get_courseVO_by_subject(subjectId);
			SXSSFWorkbook excel = ExcelUtil.create_excel(requestUser.getRealName(), subject, courseList);
			String fileName = "《" + subject.getName() + "》博思课表_" + subject.getKlass() + ".xlsx";
			String downloadFileName = new String(fileName.getBytes(StandardCharsets.UTF_8.name()),StandardCharsets.ISO_8859_1.name());
			HttpHeaders headers = new HttpHeaders();
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			excel.write(byteOut);
			headers.setContentDispositionFormData("attachment", downloadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>( byteOut.toByteArray(), headers, HttpStatus.CREATED );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * @api {post} /TeacherHelper/subject/export_word 导出-word
	 * @apiVersion 1.0.0
	 * @apiGroup Subject
	 * @apiName 导出-word
	 * @apiSuccess {Boolean} success true表示请求成功，false表示请求失败
	 * @apiSuccess {number} code 错误代码
	 * @apiSuccess {string} message 错误信息
	 * @apiParam {number} subjectId 科目id
	 * @apiSuccessExample {json} 请求成功例子:
	 * 	{
	 *     	"success" : true,
	 *      "code" : 20000,
	 *      "message" : "请求成功",
	 *      "data" : null
	 *	}
	 * @apiParamExample {json} 请求示例:
	 * 	{
	 * 		"subjectId":"1167"
	 * 	}
	 * @apiDescription 接口说明:
	 * 	 调用成功后返回到科目列表
	 */
	@RequestMapping("/export_word")
	public ResponseEntity<byte[]> export_word(HttpServletRequest request, HttpSession session, Integer subjectId) {
		try {
			User requestUser = (User) session.getAttribute("loggedUser");
			Subject subject = subjectServer.get_subject_by_id(subjectId);
			if ( requestUser.getId() != subject.getTeacher() && requestUser.getPermission() == Constant.USER_PERMISSION_NORMAL ) {
				System.out.println("已拦截 - 请求用户权限不足");
				throw new Exception(ResultCode.ERROR_PERMISSION.getMessage() );
			}
			List<CourseVO> courseList = subjectServer.get_courseVO_by_subject(subjectId);
			Term term = CacheUtil.currTerm;
			if ( subject.getTerm() != term.getId() ) {
				term = termServer.get_term_by_subject(subject );
			}
			XWPFDocument word = WordUtil.create_word(requestUser, subject, term.getName(), courseList);
			String fileName = "《" + subject.getName() + "》教学计划进度表_" + subject.getKlass() + ".docx";
			String downloadFileName = new String(fileName.getBytes(StandardCharsets.UTF_8.name()),StandardCharsets.ISO_8859_1.name());
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			HttpHeaders headers = new HttpHeaders();
			word.write(byteOut);
			headers.setContentDispositionFormData("attachment", downloadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			word.close();
			return new ResponseEntity<byte[]>( byteOut.toByteArray(), headers, HttpStatus.CREATED );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
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
