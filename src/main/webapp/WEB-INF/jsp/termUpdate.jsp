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
                <li><a href="content.html"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
                <li><a href="${pageContext.request.contextPath}/report/user_report_list"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
                <li class="active"><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;学期管理</a></li>
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
	

   
   
   <div > 
   <div style="margin-left: 400px;margin-right: 200px" class="col-md-5">
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/term/term_update" method="post">
                    <div class="form-group">
                        <label for="name">学期名称</label>
                        <input type="text" id="name" name="name" class="form-control" value="${result.data.name }">
                    </div>
                    <div class="form-group">
                        <label for="startTime">学期开始时间</label>
                        <input type="date" id="startTime" name="startTime" class="form-control" placeholder="请选择学期开始时间">
                    </div>
                    <div class="form-group">
                        <label for="weeks">总周数</label>
                        <input type="number" id="weeks" name="weeks" class="form-control" placeholder="请确认输入用户密码">
                    </div>
                    <div class="form-group">
                        <label for="isCurrent">是否为当前学期</label>
                        <div>
	                        <input type="radio" name="isCurrent" value=true ${term.isCurrent == true ? 'checked':'' }> 是
	                        <input type="radio" name="isCurrent" value=false ${term.isCurrent == true ? 'checked':'' }> 否
                        </div>
                        
                    </div>
                    <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary">提交</button>
            		</div>
                </form>
            </div>
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