<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>CodePen - Spacy 404 with count up</title>
		<link rel='stylesheet' href='https://cdn.3up.dk/flexgrid.io@2.5.1/css/flexgrid.min.css'>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleerror.css">

	</head>
	<body>
		<!-- partial:index.partial.html -->
		<div class="container">
			<div class="row">
				<div class="xs-12 md-6 mx-auto">
					<div id="countUp">
						<div class="number" data-count="400">0</div>
						<div class="text">Page not found</div>
						<div class="text">This may not mean anything.</div>
						<div class="text">I'm probably working on something that has blown up.</div>
						<div class="text">
							<c:if test="${result.success == false }">
				      			<span>${result.message}</span>
				      		</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- partial -->
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
		<script src='https://cdn.3up.dk/in-view@0.6.1'></script>
		<script src="${pageContext.request.contextPath}/js/scripterror.js"></script>

	</body>
</html>
