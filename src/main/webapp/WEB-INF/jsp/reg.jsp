<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/style.css">

</head>
<body>


<div class="materialContainer">


	<div class="overbox">
		<div class="material-button alt-2">
			<span class="shape"></span>
		</div>
		<div class="title">注册</div>
		<div class="input">
			<label for="regname">用户名</label>
			<input type="text" name="regname" id="regname">
			<span class="spin"></span>
		</div>
		<div class="input">
			<label for="regpass">密码</label>
			<input type="password" name="regpass" id="regpass">
			<span class="spin"></span>
		</div>
		<div class="input">
			<label for="reregpass">确认密码</label>
			<input type="password" name="reregpass" id="reregpass">
			<span class="spin"></span>
		</div>
		<div class="button">
			<button>
				<span>注册</span>
			</button>
		</div>
	</div>

</div>

<script src="js/jquery.min.js"></script>
<script src="js/index.js"></script>

<style>
.copyrights{text-indent:-9999px;height:0;line-height:0;font-size:0;overflow:hidden;}
</style>
<div class="copyrights" id="links20210126">
	Collect from <a href="http://www.cssmoban.com/"  title="网站模板">模板之家</a>
	<a href="http://cooco.net.cn/" title="组卷网">组卷网</a>
</div>
</body>
</html>