<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>注册界面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		
		<style type="text/css">
		
			.radioBox input{
    				-webkit-appearance: none;
				    width: 20px;
				    height: 20px;
				    padding: 0;
				    background-color: #fff;
				    border: 1px solid #c9c9c9;
				    border-radius: 50%;
				    outline: none;
				    margin-right: 22px;
				    cursor: pointer;
			}
		</style>
		
	</head>
	<body >
		<div class="wrap login_wrap">
			<div class="content">
				
				<div class="logo"></div>
				
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							注册
						</div>
						<form action="${pageContext.request.contextPath}/user/user_register" method="post" onsubmit="return checkUserName()">
							
							<div class="form_text_ipt">								
								<input id="username" name="username" type="text" style="color:red" placeholder="用户名" onblur="checkUserName()">	
								<span id="usernameErr"></span>		
							</div>
							<div class="ececk_warning"></div>
							
							<div class="form_text_ipt">
								<input id="password" name="password" type="password" placeholder="密码" onblur="checkPassword()">
								<span id="passwordErr"></span>
							</div>
							<div class="ececk_warning"><span></span></div>
							
							<div class="form_text_ipt">
								<input id="realName" name="realName" type="text" placeholder="真实名" >
							</div>
							<div class="ececk_warning"><span>真实名不能为空</span></div>
							
							<div class="form_text_ipt">
								<input id="mail" name="mail" type="email" placeholder="邮箱" onblur="checkMail()">
								<span id="mailErr"></span>
							</div>							
							<div class="ececk_warning"></div>
							
							<div class="form_text_ipt">
								<input id="phone" name="phone" type="text" placeholder="电话" onblur="checkPhone()">
								<span id="phoneErr"></span>
							</div>
							<div class="ececk_warning"></div>
							

						
							<div style="text-align: center;">
								<p><input type="radio" id="gender" name="gender" value="1">男</p>
					            <p><input type="radio" id="gender" name="gender" value="0">女</p>
							</div>

							<div class="ececk_warning"></div>						
							
							<div class="form_btn">
								<button type="submit" >注册</button>
							</div>
							
							<div class="form_reg_btn">
								<span>已有帐号？</span><a href="${pageContext.request.contextPath}/login/goto_login">马上登录</a>
							</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mins.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" ></script>
		<script type="text/javascript">
			function checkUserName(){
				var reg = new RegExp('^[a-zA-Z_][a-zA-Z_0-9]{0,}')
				var username = $('#username').val()                  
				if(username == ''){
					$('#usernameErr').html('用户名不能为空')
					return false
				}else if(username.length < 5 || username.length > 16){
					$('#usernameErr').html('用户名的长度为6-16位之间')
					return false
				}else if(!reg.test(username)){
					$('#usernameErr').html('用户名开头不能是数字')
					return false
				}else{
					$('#usernameErr').html('')
				}
				var url = '${pageContext.request.contextPath}/login/login_check?field=0&value='+$('#username').val()
				$.get(url, function(result){
					if(! result.success){
						$('#usernameErr').html(result.message)
					}
				})
			}
			function checkPassword(){
				$('#passwordErr').html('不少6个字符，不多于54个字符')
				var password = $('#password').val()		
				if(password == ''){
					$('#passwordErr').html('密码不能为空')
					return false
				}else if(password.length < 6 || password.length > 54){
					$('#passwordErr').html('密码的长度为6-54位之间')
				}else{
					$('#passwordErr').html('')
				}				

			}	
			
			function checkMail(){ 
				 var reg = new RegExp('^(\w+((-\w+)|(\.\w+))*)\+\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$');
				 var mail = $('#mail').val();
				 if(mail == ''){
					$('#mailErr').html('邮箱不能为空')
					return false
				 }else if(!reg.test(mail)){ 
					 $('#mailErr').html('邮箱格式有误，请重写输入！');  
				     return false
				 }else{
					 $('#mailErr').html('')
				 }
			}
			
			function checkPhone(){ 
				 var reg = new RegExp('^1(3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$');
				 var phone = $('#phone').val();
				 if(phone == ''){
					$('#phoneErr').html('电话不能为空')
					return false
				 }else if(!reg.test(phone)){ 
				 	$('#phoneErr').html('手机号码有误，请重填！');  
			        return false
				 }else{
					 $('#phoneErr').html('')
				 }
			}
						
		</script>

	</body>
</html>

