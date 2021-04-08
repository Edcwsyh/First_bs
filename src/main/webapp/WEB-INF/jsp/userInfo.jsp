<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>博思教师辅助平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-maizi.css"/>
    <style type="text/css">
    	.s{
			display:block;
			margin-left:auto;
			margin-right:auto;
		}
    </style>
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
                <li><a href="${pageContext.request.contextPath}/user/index"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/user_info"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
                <li><a href="content.html"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
                <li><a href="tag.html"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;周报管理</a></li>
                <li><a href="tag.html"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;学期管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${loggedUser.username}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">                        
                        <li><a href="index.html"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;个人信息</a></li>
                        <li><a href="index.html"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;个人设置</a></li>                      
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/login/login"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
            </ul>
        </div>


    </div>
</nav>

	 <div class="s" style=" width: 300px"  >
                <form action="${pageContext.request.contextPath}/user/user_update" method="post">
                					<!-- 用隐藏域提交不能修改的数据 -->
					<input type="hidden" name="id" value="${result.data.id }"> 
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" id="username" name="username"  class="form-control" value="${result.data.username}">
                    </div>
                    
                     <div class="form-group">
                        <label for="realName">用户真实名</label>
                        <input type="text" id="realName" name="realName" class="form-control" value="${result.data.realName}">
                    </div>                                      
                    
                    <div class="form-group">
                        <label for="phone">用户电话</label>
                        <input type="text" id="phone" name="phone" class="form-control" value="${result.data.phone}">
                    </div>
                    
                    <div class="form-group">
                        <label for="mail">用户邮箱</label>
                        <input type="email" id="mail" name="mail" class="form-control" value="${result.data.mail}">
                    </div>
                         
                    <div class="form-group">
                        <label for="gender">用户性别</label>
                        <input type="text" id="gender" name="gender" class="form-control" value="${result.data.gender}">
                    </div>                                 
                    
                    <div class="form-group">
                		<button type="submit" class="btn btn-primary">修改</button>
                		               		               		
            		</div>
                </form>
                <div class="form-group">                		                		               		
                		<a href="${pageContext.request.contextPath}/user/goto_user_update_password"><button style="text-align: right;" class="btn btn-primary">修改密码</button></a>
            	</div>
           </div>


</body>
</html>