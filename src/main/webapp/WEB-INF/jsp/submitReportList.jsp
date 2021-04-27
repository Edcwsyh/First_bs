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
                <li class="active"><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
                <li><a href="${pageContext.request.contextPath}/term/term_list"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;学期管理</a></li>
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
	
	<div class="container">
	<div class="row">
	<div class="col-md-2">
	    <div class="list-group">
	        <a href="${pageContext.request.contextPath}/report/user_report_list" class="list-group-item ">周报管理</a>
	        <a href="" class="list-group-item active">查看所有用户周报</a>	 	        
	    </div>
	</div>
	<div class="col-md-10">
	<div class="page-header">
	    <h1>周报管理</h1>
	</div>
	<ul class="nav nav-tabs">
	    <li class="active">
	        <a href="content.html">周报管理</a>
	    </li>
	    
	</ul>
	<table class="table">
	    <thead>
	    <tr>
	    	<th>周次</th>
	    	<th>作者</th>
	        <th>周报内容</th>	        
	        <th>发布时间</th>
	    </tr>
	    </thead>
	    <tbody>
	    <c:forEach items="${result.data }" var="report">
	    <tr>	    	
	        <th scope="row">${report.week}</th>	        
	        <td>${report.author }</td>
	        <td>${report.content }</td>
	        <td>${report.time }</td>
	        <td>
	            <div role="presentation" class="dropdown">
	                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
	                    操作<span class="caret"></span>
	                </button>
	                <ul class="dropdown-menu">
	                    <li><a href="${pageContext.request.contextPath}/report/goto_report_update?reportId=${report.id}">编辑</a></li>
	                    <li><a href="#">删除</a></li>
	                    <li><a href="#">全局置顶</a></li>
	                </ul>
	            </div>
	        </td>	        
	    </tr>
	    </c:forEach>	  	    
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
	<div class="modal fade" id="reportAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加周报</h4>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/report/report_add" method="post">
                	<input type="hidden" name="author" value="${loggedUser.id}"> 
                    <div class="form-group">
                        <label for="week">周报所属周次</label>
                        <select id="week" name="week" class="form-control">
                        	  
                        	<option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                                                  
                        </select>
                    </div>
                    <div class="form-group">
	                    <label for="content">周报内容</label>
	                    <textarea id="content" name="content" class="form-control" rows="15" cols="10" placeholder="请输入周报正文部分"></textarea>
                	</div>
                    <div class="form-group">
                        <label for="isSubmit">是否提交周报</label>
                        <div>
	                        <input type="radio" name="isSubmit" value="1" > 是
	                        <input type="radio" name="isSubmit" value="0" > 否
                        </div>
                        
                    </div>
                    <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary">添加</button>
            		</div>
                </form>
            </div>
            
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