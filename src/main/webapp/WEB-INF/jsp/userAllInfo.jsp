<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>博思教师辅助平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-maizi.css"/>
</head>
<body>
<!--导航-->
<nav class="navbar navbar-default">
    <div class="container">
        <!--小屏幕导航按钮和logo-->
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="index.html" class="navbar-brand">博思教师辅助平台</a>
        </div>
        <!--小屏幕导航按钮和logo-->
        <!--导航-->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/login/goto_index"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></li>
                <li class="active"><a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
                <li><a href="content.html"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
                <li><a href="${pageContext.request.contextPath}/report/user_report_list"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
                <li><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;学期管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       ${loggedUser.username}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">
                        <li><a href="${pageContext.request.contextPath}/user/user_info?id=${loggedUser.id}"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;个人信息</a></li>
                        <li><a href="index.html"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;个人设置</a></li>  
                    </ul>
                </li>
                
            </ul>
        </div>
        <!--导航-->

    </div>
</nav>
	<!--导航-->
	

   
   

   <div style="margin-left: 400px;margin-right: 200px" class="col-md-5">
         <div class="modal-body">
                <form action="${pageContext.request.contextPath}/admin/user_update" method="post">
                	<input type="hidden" name="id" value="${result.data.id }">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" id="username" name="username" class="form-control" value="${result.data.username }">
                    </div>
                    
                    <div class="form-group">
                        <label for="realName">真实名</label>
                        <input type="text" id="realName" name="realName" class="form-control" value="${result.data.realName }">
                    </div>
                    
                    <div class="form-group">
                        <label for="password">用户密码</label>
                        <input type="password" id="password" name="password" class="form-control" value="${result.data.password }">
                    </div>
                   
                    <div class="form-group">
                        <label for="mail">用户邮箱</label>
                        <input type="email" id="mail" name="mail" class="form-control" value="${result.data.mail }">
                    </div>
                    
                    <div class="form-group">
                        <label for="phone">用户电话</label>
                        <input type="text" id="phone" name="phone" class="form-control" value="${result.data.phone }">
                    </div>
                    
                     <div class="form-group">
                        <label for="permission">用户权限</label>
                        <div>
	                        <input type="radio" id="permission" name="permission" value="0" ${result.data.permission == 0 ? 'checked':''}> 管理员
	                        <input type="radio" id="permission" name="permission" value="1" ${result.data.permission == 1 ? 'checked':''}> 普通用户
	                        <input type="radio" id="permission" name="permission" value="2" ${result.data.permission == 2 ? 'checked':''}> 经理
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="state">用户状态</label>
                        <div>
	                        <input type="radio" id="state" name="state" value="1" ${result.data.gender == 1 ? 'checked':''}> 正常
	                        <input type="radio" id="state" name="state" value="0" ${result.data.gender == 0 ? 'checked':''}> 冻结
	                        
                        </div>
                    </div>
                    
                   	<div class="form-group">
                        <label for="gender">用户性别</label>
                        <div>
	                        <input type="radio" id="gender" name="gender" value="1" ${result.data.gender == 1 ? 'checked':''}> 男
	                        <input type="radio" id="gender" name="gender" value="0" ${result.data.gender == 0 ? 'checked':''}> 女
                        </div>
                        
                    </div>
                    <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary">提交</button>
            		</div>
                </form>
            </div>    
   </div>
            
      
    
    
	<!--footer-->
	<footer>
	    <div class="container">
	        <div class="row">
	            <div class="col-md-12">
	                <p>
	                    Copyright&nbsp;©&nbsp;2012-2015&nbsp;&nbsp;www.maiziedu.com&nbsp;&nbsp;蜀ICP备13014270号-4
	                </p>
	            </div>
	        </div>
	    </div>
	</footer>
	<!--footer-->
		
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>	

</body>
</html>