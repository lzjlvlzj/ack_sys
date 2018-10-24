<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../public/header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/3part/bootstrapValidator/css/bootstrapValidator.min.css" />
<title>密码修改</title>
</head>
<body>
<div class="col-xs-4">
	<form class="form-horizontal" role="form" id="ack-user-pass-reset-form">
		<div id="form-validate-msg" style="display: none; color:#FF0000;text-align: center"></div>
		<input type="hidden" name="id" id="id" value="">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				   for="oldPassword"> 原来密码 </label>

			<div class="col-sm-9">
				<input type="password" id="oldPassword" placeholder="请输入原来密码"
					   name="oldPassword" value="" class="col-xs-10 col-sm-8">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				   for="password1"> 新密码 </label>

			<div class="col-sm-9">
				<input type="password" id="password1" placeholder="请输入新密码" name="password1"
					   value="" class="col-xs-10 col-sm-8">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				   for="password2"> 确认密码 </label>

			<div class="col-sm-9">
				<input type="password" id="password2" placeholder="请再次输入新密码" name="password2"
					   value="" class="col-xs-10 col-sm-8">
			</div>
		</div>
		<div>
			<div class="col-sm-3 col-sm-offset-3">
				<input type="button" class="btn btn-primary" id="user-pass-rest-btn" onclick="" value="保存 "></input>
			</div>
		</div>


	</form>
</div>
<%@include file="../public/footer.jsp"%>
<%@include file="../public/table.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/3part/bootstrapValidator/js/bootstrapValidator.min.js" />
<script type="text/javascript" src="/static/js/user/userPass.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        UserPass.init();
    });
</script>
</body>
</html>