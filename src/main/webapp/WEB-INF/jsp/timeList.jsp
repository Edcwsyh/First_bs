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
                <li   class="active"><a href="content.html"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
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
					<a href="${pageContext.request.contextPath}/subject/course/course_list"
						class="list-group-item">课程管理</a>
				</div>
			</div>
			
			<div class="col-md-10">
				<div class="page-header">
					<h1>课表管理</h1>
				</div>
				
					<ul class="nav nav-tabs">
					<!--  
						<li class="active"><a href="content.html">课表管理</a></li>
						<li>
							<a href="" role="button" data-toggle="modal"
							data-target="#subjectAdd">时间表管理</a>
						</li>
						<li ><a href="${pageContext.request.contextPath}/subject/time/goto_time_add">添加时间表</a></li>
						-->
						
							<li><button type="submit" class="btn btn-primary" onclick="adddate()">添加时间段</button></li>
							<li>
								<a href="" role="button" data-toggle="modal" data-target="#courseAdd">时间表管理</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/subject/course/course_list" >时间表管理</a>
							</li>
					</ul>
				<form action="${pageContext.request.contextPath}/subject/time/time_update"  method="post">
				<table class="table" id="interfaceParam" >
					<thead>
							<tr>
								
								<th>开始周</th>
								<th>结束周</th>
								<th>上课间隔</th>
								<th>节次</th>
								<th>天次</th>
								<th>教室</th>
								<th>添加周</th>
								<th>删除周</th>
								

							</tr>
					</thead>
				<tbody>
						<c:forEach items="${result.data }" var="time">
							<tr class="typeface">														
								<th scope="row">${time.weeks}</th>
								<td>${time.timeQuantum}</td>
								<td>${time.classRoom }</td>
								<td>
									<div role="presentation" class="dropdown">
										<button class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" href="#" role="button"
											aria-haspopup="true" aria-expanded="false">
											操作<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="${pageContext.request.contextPath}/subject/subject_info?subjectId=${subject.id}">编辑</a></li>
											<li><a href="${pageContext.request.contextPath}/subject/subject_delete?subjectId=${subject.id}">删除</a></li>
											<li><a href="#">全局置顶</a></li>
										</ul>
									</div>
								</td>

							</tr>
						</c:forEach>
						
					</tbody>
				</table>
				 	<div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="submit" class="btn btn-primary">提交</button>
            		</div>
				</form>
				
				
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
					<form action="${pageContext.request.contextPath}/subject/"  method="post">
						<input type="hidden" name="teacher" value="${loggedUser.id }">
						<div class="form-group">
							<label for="name">课程名</label> <input type="text" id="name"
								name="name" class="form-control" placeholder="请输入课程名">
						</div>

						
						<div class="form-group">
							<label for="ta">课程助教</label> <input type="text" id="ta" name="ta"
								class="form-control" placeholder="请输入课程助教">
						</div>
						
						<div class="form-group">
							<label for="klass">授课班级</label> 
								<input type="text" id="klass" name="klass" class="form-control" placeholder="请输入授课班级">
						</div>
						
						<div class="form-group">
							<label for="timeTotal">总课时</label> <input type="number"
								id="timeTotal" name="timeTotal" class="form-control"
								placeholder="">
						</div>

						<div class="form-group">
							<label for="timeTheory">理论课时</label> <input type="number"
								id="timeTheory" name="timeTheory" class="form-control"
								placeholder="">
						</div>

						<div class="form-group">
							<label for="timePractice">实践课时</label> <input type="number"
								id="timePractice" name="timePractice" class="form-control"
								placeholder="">
						</div>

						<div class="form-group">
							<label for="type">课程类型</label>
							<div>
								<input type="radio" id="type" name="type" value="1">
								 理论课<input type="radio" id="type" name="type" value="0">
								方向课
							</div>

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
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/multiple.js"></script>
	<script type="text/javascript">
			function adddate() {
				// alert(1);
				var Table = document.getElementById("interfaceParam");
				var rowsNum = Table.rows.length - 1;
				NewRow = Table.insertRow(); //添加行
				NewRow.setAttribute('class', 'typeface');
				 //添加列
				startweek = NewRow.insertCell();
				endweek = NewRow.insertCell();
				interval = NewRow.insertCell();
				howTime = NewRow.insertCell();
				week = NewRow.insertCell();
				klass = NewRow.insertCell();
				addWeek = NewRow.insertCell();
				deleteWeek = NewRow.insertCell();
				operate = NewRow.insertCell();
				
				
				//属性赋值s
			
				startweek.innerHTML = "<select class='form-control' style='width: 60px;'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option></select>";
				endweek.innerHTML = "<select class='form-control' style='width: 60px;'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option></select>";
				interval.innerHTML = "<select class='form-control' style='width: 60px;'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option></select>";
				howTime.innerHTML = "<input type='text' class='form-control'placeholder='必填' id=howTime" + (rowsNum +1) + "  style='width: 80px;' />";
				week.innerHTML = "<input type='text' class='form-control'placeholder='必填' id=howTime" + (rowsNum +1) + "  style='width: 80px;' />";
				klass.innerHTML = "<input type='text' class='form-control'placeholder='必填' id=howTime" + (rowsNum +1) + "  style='width: 80px;' />";
				addWeek.innerHTML = "<input type='text' class='form-control'placeholder='非必填' id=howTime" + (rowsNum +1) + "  style='width: 80px;' />";
				deleteWeek.innerHTML = "<input type='text' class='form-control'placeholder='非必填' id=howTime" + (rowsNum +1) + "  style='width: 80px;' />";
				<!--operate.innerHTML = '<div id=operate' + (rowsNum +1) +'><a style="cursor:pointer;color:#007bff;"  onclick="saveInterfaceParam();">保存</a>&nbsp;&nbsp;<a style="cursor:pointer;color:#007bff;"  onclick="saveInterfaceParam();">取消</a></div>';
				-->
			}
			</script>
</body>
</html>