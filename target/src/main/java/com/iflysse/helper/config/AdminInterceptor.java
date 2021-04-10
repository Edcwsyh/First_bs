//package com.iflysse.helper.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.iflysse.helper.bean.User;
//import com.iflysse.helper.tools.Constant;
//import com.iflysse.helper.tools.Result;
//import com.iflysse.helper.tools.ResultCode;
//
//public class AdminInterceptor implements HandlerInterceptor {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		User user = (User) request.getSession().getAttribute("loggedUser");
//		if(  user.getPermission() != Constant.USER_PERMISSION_ADMIN ) {
//			request.setAttribute("result", new Result<Boolean>( ResultCode.ERROR_PERMISSION, null) );
//			request.getRequestDispatcher("/WEB-INF/jsp/error/403.jsp").forward(request, response);
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	}
//
//}
