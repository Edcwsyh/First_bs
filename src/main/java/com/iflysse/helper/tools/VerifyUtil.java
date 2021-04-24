//package com.iflysse.helper.tools;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.iflysse.helper.bean.Subject;
//import com.iflysse.helper.bean.User;
//import com.iflysse.helper.service.SubjectServer;
//import com.iflysse.helper.service.UserServer;
//
//public class VerifyUtil {
//	
//	private SubjectServer subjectServer;
//	
//	private UserServer userServer;
//	
//	static private VerifyUtil validator = new VerifyUtil();
//	
//	private VerifyUtil() {}
//	
//	/**
//	 * 单例模式, 获取一个验证器
//	 * @return
//	 */
//	static public VerifyUtil get_validator() {
//		return validator;
//	}
//	
//	/**
//	 * 验证是否为用户本人或者拥有更高的权限
//	 * @param session
//	 * @param userId
//	 * @param accessPermission
//	 * @return
//	 */
//	public boolean verify_user(HttpSession session , Integer userId, Byte accessPermission) {
//		User requestUser = (User) session.getAttribute("loggedUser");
//		User dbUser = userServer.get_user_by_id(userId);
//		if ( requestUser.getId() != dbUser.getId() && (requestUser.getPermission() & accessPermission) == 0 ) {
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 验证该课程是否属于请求用户,或者该用户拥有访问权限
//	 * @param session
//	 * @param courseId
//	 * @param accessPermission
//	 * @return
//	 */
//	public boolean verify_course(HttpSession session , Integer courseId, Byte accessPermission) {
//		User requestUser = (User) session.getAttribute("loggedUser");
//		Subject subject = subjectServer.get_subject_by_course(courseId);
//		if ( requestUser.getId() != subject.getTeacher() && (requestUser.getPermission() & accessPermission) == 0 ) {
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 验证该科目是否属于请求用户,或者该用户拥有访问权限
//	 * @param session
//	 * @param subjectId
//	 * @param accessPermission
//	 * @return
//	 */
//	public boolean verify_subject(HttpSession session , Integer subjectId, Byte accessPermission) {
//		User requestUser = (User) session.getAttribute("loggedUser");
//		Subject subject = subjectServer.get_subject_by_id(subjectId);
//		if ( requestUser.getId() != subject.getTeacher() && (requestUser.getPermission() & accessPermission) == 0 ) {
//			return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 验证该时间表是否属于请求用户,或者该用户拥有访问权限
//	 * @param session
//	 * @param timeId
//	 * @param accessPermission
//	 * @return
//	 */
//	public boolean verify_time(HttpSession session , Integer timeId, Byte accessPermission) {
//		User requestUser = (User) session.getAttribute("loggedUser");
//		Subject subject = subjectServer.get_subject_by_time(timeId);
//		if ( requestUser.getId() != subject.getTeacher() && (requestUser.getPermission() & accessPermission) == 0 ) {
//			return false;
//		}
//		return true;
//	}
//}
