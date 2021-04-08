<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	
<head>
		<meta charset="utf-8">
		<title>登录界面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
	</head>
	<body>
		<div class="wrap login_wrap">
			<div class="content">
				<div class="logo"></div>
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							登录
						</div>
						<form action="${pageContext.request.contextPath}/login/login" method="post">
							
							<div class="form_text_ipt">
								<input name="username" type="text" placeholder="用户名" onblur="checkUsername()">
							</div>
							<div class="ececk_warning"><span>用户名不能为空</span></div>
							<div class="form_text_ipt">
								<input name="password" type="password" placeholder="密码" onblur="checkPassword()">
								
							</div>
							<div class="ececk_warning"><span>密码不能为空</span></div>
							<div class="form_check_ipt">
								<div class="left check_left">
									<label><input name="" type="checkbox"> 下次自动登录</label>
								</div>
								<div class="right check_right">
									<a href="#">忘记密码</a>
								</div>
							</div>
							<div class="form_btn">
								<button type="submit" onclick="javascript:window.location.href='${pageContext.request.contextPath}/user/goto_workbench'">登录</button>
							</div>
							
							 <c:if test="${result.success == false }">
			      				<span>${result.message}</span>
			      			 </c:if>
			      			 
							<div class="form_reg_btn">
								<span>还没有帐号？</span><a href="${pageContext.request.contextPath}/login/goto_register">马上注册</a>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mins.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" ></script>
		<script type="text/javascript">
		</script>

		<style>
			.copyrights{text-indent:-9999px;height:0;line-height:0;font-size:0;overflow:hidden;}
		</style>
		

</body>
</html>
