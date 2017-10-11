<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/header.jsp"%>
<title>500-系统错误</title>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<div class="error-container">
				<div class="well">
					<h1 class="grey lighter smaller">
						<span class="blue bigger-125"> <i
							class="ace-icon fa fa-random"></i> 500
						</span> 系统出错了
					</h1>

					<hr>
					<h3 class="lighter smaller">
						我们正在 <i
							class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>
						这个问题
					</h3>

					<div class="space"></div>

					<div>
						<h4 class="lighter smaller">尝试以下操作:</h4>

						<ul class="list-unstyled spaced inline bigger-110 margin-15">
							<li><i class="ace-icon fa fa-hand-o-right blue"></i>仔细阅读相关说明</li>

							<li><i class="ace-icon fa fa-hand-o-right blue"></i> 给我们发送详细的错误信息</li>
						</ul>
					</div>

					<hr>
					<div class="space"></div>

					<div class="center">
						<a href="javascript:history.back()" class="btn btn-grey"> <i
							class="ace-icon fa fa-arrow-left"></i> 回退
						</a> <a href="/" class="btn btn-primary"> <i
							class="ace-icon fa fa-tachometer"></i> 首页
						</a>
					</div>
				</div>
			</div>

			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>
</body>
</html>