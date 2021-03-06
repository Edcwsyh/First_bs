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
                <li><a href="${pageContext.request.contextPath}/login/goto_index"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></li>
                <li class="active"><a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
                <li><a href="${pageContext.request.contextPath}/subject/subject_list"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;课表管理</a></li>
                <li><a href="${pageContext.request.contextPath}/report/user_report_list"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;我的周报</a></li>
                <li><a href="${pageContext.request.contextPath}/term/term_list"><span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;学期管理</a></li>
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
               <li><a href="${pageContext.request.contextPath}/login/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
            </ul>
        </div>


    </div>
</nav>

	 <div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="user_list.html" class="list-group-item active">用户管理</a>                
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header">
                <h1>用户管理</h1>
            </div>
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="user_list.html">用户列表</a>
                </li>
                <li>
                    <a href="uesr_search.html">用户搜索</a>
                </li>
                <li>
                    <a href="" role="button" data-toggle="modal" data-target="#userAdd">添加用户</a>
                </li>
            </ul>
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>真实名</th>
                        <th>电话</th>
                        <th>性别</th>
                        <th>邮箱</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${result.data }" var="user">
                    <tr>
                        <th scope="row">${user.id}</th>
                        <td>${user.username}</td>
                        <td>${user.realName}</td>
                        <td>${user.phone}</td>
                        
                        <c:if test="${user.gender == 1 }">
                        	 <td>男</td>
                        </c:if>
                        <c:if test="${user.gender == 0 }">
                        	 <td>女</td>
                        </c:if>
                       
                        <td>${user.mail}</td>
                        <td>
                            <div role="presentation" class="dropdown">
                                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                    操作<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                   <li><a href="${pageContext.request.contextPath}/admin/user_all_info?userId=${user.id}">编辑</a></li>
                                   <li><a href="#">删除</a></li>
                                   <li><a href="#">锁定</a></li>
                                   <li><a href="#">修改密码</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>                 
                  
                </tbody>
            </table>
            <nav aria-label="Page navigation">
			  <ul class="pagination">
			    <li ${page.pageNum == 1 ? 'class="disabled"' : '' }>
			      <a href="${pageContext.request.contextPath}/admin/user_list?pageIndex=${page.pageNum - 1 < 1 ? 1 : page.pageNum - 1 }" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    
			    <c:forEach begin="${page.pageNum - 2 < 1 ? 1 : page.pageNum - 2 }" 
			    	end="${page.pageNum + 2 > page.pages ? page.pages : page.pageNum + 2 }" var="num">
			    	
			    	<li ${page.pageNum == num ? 'class="active"' : '' }>
			    		<a href="${pageContext.request.contextPath}/admin/user_list?pageIndex=${num }">${num }</a>
			    	</li>
			    </c:forEach>
			    
			    <li ${page.pageNum == page.pages ? 'class="disabled"' : '' }>
			      <a href="${pageContext.request.contextPath}/admin/user_list?pageIndex=${page.pageNum + 1 > page.pages ? page.pages : page.pageNum + 1 }" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			  <p>总条数：${page.total } 
				总页数：${page.pages } 
				当前页 ：${page.pageNum } 
				每页大小：${page.pageSize }
			  </p>
			</nav>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="userAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加用户</h4>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/admin/user_add" method="post">
                    <div class="form-group">
                        <label for="username">用户名</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="请输入用户名">
                    </div>
                    
                    <div class="form-group">
                        <label for="realName">真实名</label>
                        <input type="text" id="realName" name="realName" class="form-control" placeholder="请输入用户真实名">
                    </div>
                    
                    <div class="form-group">
                        <label for="password">用户密码</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="请输入用户密码">
                    </div>
                   
                    <div class="form-group">
                        <label for="mail">用户邮箱</label>
                        <input type="email" id="mail" name="mail" class="form-control" placeholder="请输入用户邮箱">
                    </div>
                    
                    <div class="form-group">
                        <label for="phone">用户电话</label>
                        <input type="text" id="phone" name="phone" class="form-control" placeholder="请输入用户电话">
                    </div>
                    
                    <div class="form-group">
                        <label for="gender">用户权限</label>
                        <div>
	                        <input type="radio" id="permission" name="permission" value="1" > 普通用户
	                        <input type="radio" id="permission" name="permission" value="0" > 管理员
	                        <input type="radio" id="permission" name="permission" value="2" > 经理
                        </div>
                        
                    </div>
                    
                     <div class="form-group">
                        <label for="gender">用户状态</label>
                        <div>
	                        <input type="radio" id="state" name="state" value="1" > 正常
	                        <input type="radio" id="state" name="state" value="0" > 冻结
                        </div>
                        
                    </div>
                    
                   	<div class="form-group">
                        <label for="gender">用户性别</label>
                        <div>
	                        <input type="radio" id="gender" name="gender" value="1" > 男
	                        <input type="radio" id="gender" name="gender" value="0" > 女
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
</div>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>