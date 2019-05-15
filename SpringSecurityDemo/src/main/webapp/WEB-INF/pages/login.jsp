<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<section id="login">
		<div class="row animated fadeILeftBig">
			<div class="login-holder col-md-6 col-md-offset-3">
				<h2 class="page-header text-center text-primary"
					style="margin-top: 20px;">
				</h2>
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
				<form name="loginForm" action="<c:url value='/login' />" method="POST">
					<h3>USER LOGIN</h3>
					<div class="form-group">
						<input type="text" class="form-control" name="username" id="username" placeholder="Username" />
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="password" id="password" placeholder="Password" />
					</div>
					<div class="col-lg-12" style="padding: 0px;">
						<button type="submit" class="btn btn-info btn-submit submit-btn-generate">Login</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>