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
                <li><a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
                <li><a href=""><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
                <li class="active"><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
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
                <li><a href="${pageContext.request.contextPath}/login/login"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
            </ul>
        </div>
        <!--导航-->

    </div>
</nav>
	<!--导航-->
	
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/report/user_report_list" class="list-group-item">周报管理</a>
                <a href="" class="list-group-item active">添加周报</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>周报管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li>
                    <a href="${pageContext.request.contextPath}/report/user_report_list">周报管理</a>
                </li>
                <li class="active">
                    <a href="">添加周报</a>
                </li>
            </ul>
            <form action="${pageContext.request.contextPath}/report/report_add" class="mar_t15">
            	<input type="hidden" name="author" value="${loggedUser.id}"> 
                <div class="form-group">
                    <label for="titel">标题</label>
                    <input type="text" id="titel" name="titel" class="form-control" placeholder="请输入周报标题">
                </div>
                <div class="form-group">
                    <label for="content">周报内容</label>
                    <textarea id="content" name="content" class="form-control" rows="15" cols="10" placeholder="请输入周报正文部分"></textarea>
                </div>
                <div class="">
			    	<label class="radio-inline">
			     		<input type="radio" name="isSubmit" value="1" > 提交
			      	</label>
			     	 <label class="radio-inline">
			      		<input type="radio" name="isSubmit" value="0" > 不提交
			     	 </label>			    
                    <button type="submit" class="btn btn-default pull-right">新增周报</button>
                </div>
            </form>

        </div>
    </div>
</div>
	<!--footer-->
	
	
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>