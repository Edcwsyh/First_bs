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

	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<div class="list-group">
					<a href="content.html" class="list-group-item ">课表管理</a> 
					<a href="${pageContext.request.contextPath}/subject/course/course_list"
						class="list-group-item active">课程管理</a>
				</div>
			</div>
			<div class="col-md-10">
				<div class="page-header">
					<h1>课表管理</h1>
				</div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="content.html">课程管理</a></li>
					<li>
						<a href="" role="button" data-toggle="modal"
						data-target="#courseAdd">添加课程</a>
					</li>
					
				</ul>
				<table class="table">
					<thead>
						<tr>
							<th>上课时间</th>
							<th>教学目标</th>
							<th>教学内容</th>
							<th>教学模式</th>
							<th>是否有作业</th>
							<th>具体上课时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${result.data }" var="course">
							<tr>
								<th scope="row">${course.time}</th>
								<td>${course.goal }</td>
								<td>${course.teachingContent }</td>
								<td>${course.mode }</td>
								<td>${course.isHomework}</td>
								<td>${course.specificTime}</td>

								<td>
									<div role="presentation" class="dropdown">
										<button class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" href="#" role="button"
											aria-haspopup="true" aria-expanded="false">
											操作<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="${pageContext.request.contextPath}/subject/subject_info?subjectId=${subject.id}">编辑</a></li>
											<li><a href="${pageContext.request.contextPath}/subject/course/course_delete_single?courseId=${course.id}">删除</a></li>

										</ul>
									</div>
								</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav class="pull-right">
					<ul class="pagination">
						<li class="disabled"><a href="#" aria-label="Previous"><span
								aria-hidden="true">&laquo;</span></a></li>
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
					<p>2021-4-09</p>
				</div>
			</div>
		</div>
	</footer>
	<!--footer-->
	<div class="modal fade" id="courseAdd" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加科目</h4>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath}/subject/course/course_add"  method="post">
						<input type="hidden" name="teacher" value="${loggedUser.id }">
						<div class="form-group">
							<label for="time">时间</label> <input type="date" id="time"
								name="time" class="form-control" placeholder="请选择时间">
						</div>
						
						<div class="form-group">
							<label for="teachingGoal">教学内容</label> 
							<textarea id="teachingGoal" name="teachingGoal" class="form-control" rows="15" cols="10" ></textarea>
						</div>
						
						<div class="form-group">
							<label for="goal">教学目标</label> 
							<textarea id="goal" name="goal" class="form-control" rows="15" cols="10" ></textarea>
						</div>
						
						<div class="form-group">
	                        <label for="mode">教学模式</label>
	                        <div>
		                        <input type="radio" name="mode" value="1" > 线上
		                        <input type="radio" name="mode" value="0" > 线下
	                        </div>
                        </div>
                        
                      	<div class="form-group">
	                        <label for="isHomework">是否有作业</label>
	                        <div>
		                        <input type="radio" name="isHomework" value="1" > 是
		                        <input type="radio" name="isHomework" value="0" > 否
	                        </div>
                        </div>                    					

						<div class="form-group">
							<label for="specificTime">具体上课时间</label> 
							<input type="time" id="specificTime" name="specificTime" class="form-control"
								placeholder="请选择具体上课时间">
						</div>
					

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary" >提交</button>
						</div>
					</form>
					
					
				</div>

			</div>
		</div>
	</div>
	
	
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>