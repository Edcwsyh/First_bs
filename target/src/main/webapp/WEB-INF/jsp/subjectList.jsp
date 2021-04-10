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
                <li class="active"><a href=""><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
                <li><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
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
                <li><a href="#bbs"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
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
	        <a href="content.html" class="list-group-item active">课表管理</a>
	        <a href="${pageContext.request.contextPath}/report/goto_report_add" class="list-group-item">添加课表</a>
	    </div>
	</div>
	<div class="col-md-10">
	<div class="page-header">
	    <h1>课表管理</h1>
	</div>
	<ul class="nav nav-tabs">
	    <li class="active">
	        <a href="content.html">课表管理</a>
	    </li>
	    <li>
	        <a href="${pageContext.request.contextPath}/report/goto_report_add">添加课表</a>
	    </li>
	</ul>
	<table class="table">
	    <thead>
	    <tr>
	        <th>周报内容</th>
	        <th>作者</th>
	        <th>发布时间</th>
	    </tr>
	    </thead>
	    <tbody>
	    <tr>
	    	<c:forEach items="${result.data }" var="report">
	        <th scope="row">${report.titel}</th>
	        <td>${report.author }</td>
	        <td>${report.time }</td>
	        <td>
	            <div role="presentation" class="dropdown">
	                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
	                    操作<span class="caret"></span>
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a href="#">编辑</a></li>
	                    <li><a href="#">删除</a></li>
	                    <li><a href="#">全局置顶</a></li>
	                </ul>
	            </div>
	        </td>
	        </c:forEach>
	    </tr>	  	    
	    </tbody>
	</table>
	<nav class="pull-right">
	    <ul class="pagination">
	        <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
	        <li class="active"><a href="#">1</a></li>
	        <li><a href="#">2 </a></li>
	        <li><a href="#">3 </a></li>
	        <li><a href="#">4 </a></li>
	        <li><a href="#">5 </a></li>
	        <li><a href="#">6 </a></li>
	        <li><a href="#"><span aria-hidden="true">&raquo;</span></a></li>
	    </ul>
	</nav>
	</div>
	</div>
	</div>
	<!--footer-->
	<footer>
	    <div class="container">
	        <div class="row">
	            <div class="col-md-12">
	                <p>
	                   2021-4-09
	                </p>
	            </div>
	        </div>
	    </div>
	</footer>
	<!--footer-->
	
	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>