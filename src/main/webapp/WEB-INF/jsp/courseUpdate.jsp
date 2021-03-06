<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1 , user-scalable=no">
<title>博思教师辅助平台</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-maizi.css" />
	
	
</head>
<body>
	
	<!--导航-->
	<nav class="navbar navbar-default">
		<div class="container">
			<!--小屏幕导航按钮和logo-->
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="index.html" class="navbar-brand">博思教师辅助平台</a>
			</div>
			<!--小屏幕导航按钮和logo-->
			<!--导航-->
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a
						href="${pageContext.request.contextPath}/login/goto_index"><span
							class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></li>
					<li><a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
					<li class="active"><a href=""><span
							class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
					<li><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
					<li><a href=""><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;学期管理</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a id="dLabel" type="button"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							${loggedUser.username} <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" aria-labelledby="dLabel">
							<li><a
								href="${pageContext.request.contextPath}/user/user_info?id=${loggedUser.id}"><span
									class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;个人信息</a></li>
							<li><a href="index.html"><span
									class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;个人设置</a></li>
						</ul></li>
					<li><a href="#bbs"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
				</ul>
			</div>
			<!--导航-->

		</div>
	</nav>
	<!--导航-->
	
	
	
	
	<div class="modal-body">
						
		<form action="${pageContext.request.contextPath}/subject/course/course_update"  method="post">
			
			
			<div class="form-group">
				<label for="week">周次</label>
				<select id="week" name="week" class="form-control">
                     	 <option selected="selected">${courseList[0].week}</option>
                     	 <option>1</option>
                         <option>2</option>
                         <option>3</option>
                         <option>4</option>
                         <option>5</option>
                                               
                 </select>
                     	
			</div>
			
			<div class="form-group">
				<label for="goal">教学目标</label> 
				<textarea id="goal" name="goal"  class="form-control" rows="15" cols="10" >${result.data.goal}</textarea>
			</div>
			
			<div class="form-group">
				<label for="content">教学内容</label> 
				<textarea id="content" name="courseList[0].content" class="form-control" rows="15" cols="10" >${result.data.content}</textarea>
			</div>
			
			<div class="form-group">
                      <label for="mode">教学模式</label>
                      <div>
                       <input type="radio" name="courseList[0].mode" value="0" ${result.data.mode == 1 ? 'checked':''}> 学练-线下
                       <input type="radio" name="courseList[0].mode" value="1" ${result.data.mode == 1 ? 'checked':''}> 授课-现场
                      </div>
                     </div>
                     
                   	<div class="form-group">
                      <label for="isHomework">是否有作业</label>
                      <div>
                       <input type="radio" name="courseList[0].isHomework" value="1" ${result.data.isHomework == 1 ? 'checked':''}> 是
                       <input type="radio" name="courseList[0].isHomework" value="0" ${result.data.isHomework == 0 ? 'checked':''}> 否
                      </div>
                     </div>                    					

			<div class="form-group">
				<label for="specificTime">具体上课时间</label> 
				<input type="date" id="specificTime" name="courseList[0].specificTime" class="form-control"
					value="${result.data.specificTime} ">
			</div>
		

			<div class="modal-footer">
				<button type="button" class="btn btn-default"
					data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary" >提交</button>
			</div>
			
		</form>
	 
		
		
	</div>


	
	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>