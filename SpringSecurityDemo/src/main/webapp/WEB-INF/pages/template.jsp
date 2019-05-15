<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="includes/meta-data.jsp" flush="true" />
	<!-- Load CSS -->
	<jsp:include page="includes/style.jsp" flush="true" />
	<!-- Include External CSS -->
	<jsp:include page="includes/script.jsp" flush="true" />
	<jsp:include page="includes/other-script.jsp" flush="true" />
	<!-- <script src="//cdn.tinymce.com/4/tinymce.min.js"></script> -->
</head>
<body>
	<div id="page-wapper" class="site-holder">
		<!-- this is a dummy text -->
		<!-- .navbar -->
		<jsp:include page="includes/header.jsp" flush="true" />
		<!-- /.navbar -->
		<!-- .box-holder -->
		<div class="box-holder">
			<!-- .left-sidebar -->
			<jsp:include page="includes/left-menu.jsp" flush="true" />
			<!-- /.left-sidebar -->
			<!-- .content -->
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<h3 class="page-header">Template - 
							<c:if test="${template.id eq 1}">
								Driver Template (Book Car)
							</c:if>
							<c:if test="${template.id eq 2}">
								Client Template (Book Car)
							</c:if>
							<c:if test="${template.id eq 3}">
								Client Template (End Trip)
							</c:if>
							<c:if test="${template.id eq 4}">
								Admin Template (End Trip)
							</c:if>
							<c:if test="${template.id eq 5}">
								New Driver Template								
							</c:if>
							<c:if test="${template.id eq 6}">
								Old Driver Template
							</c:if>
						</h3>
					</div>
				</div>
				<!-- Form elements -->
				<div class="row">
					<div class="col-mol-md-offset-2">
						<div class="form-group">
							<form:form action="savetemplate.htm?${_csrf.parameterName}=${_csrf.token}" modelAttribute="template" method="POST">
								<form:input type="hidden" path="id" value="${template.id}" />
								<div class="form-group">
									<form:textarea path="template" value="${template.template}" class="form-control" rows="10" cols="60"></form:textarea>
								</div>
								<div class="form-group">
									<label><strong>Break Line By Giving "\n"</strong></label>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-primary" value="Save" name="submit" />
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<!-- Form elements -->
			</div>
			<!-- /.content -->
			<!-- .right-sidebar -->
			<div class="right-sidebar right-sidebar-hidden">
				<div class="form-group backgroundImage hidden">
					<label class="control-label">Paste Image Url and then hit enter</label> <input type="text" class="form-control"
						id="backgroundImageUrl" />
				</div>
			</div>
			<!-- /.right-sidebar-holder -->
		</div>
		<!-- /.right-sidebar -->
	</div>
	<!-- /.box-holder -->
</body>
</html>