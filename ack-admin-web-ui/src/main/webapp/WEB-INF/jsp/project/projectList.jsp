<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/header.jsp"%>
<link rel="stylesheet" href="/static/3part/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>项目列表</title>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<h3 class="header smaller lighter blue">项目管理</h3>
			<table id="simple-table" class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">序号</th>
						<th>项目名称</th>
						<th>部门</th>
						<th>负责人</th>
						<th>项目状态</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>备注</th>
						<th>操作</th>
					</tr> 
				</thead>

				<tbody id='tab-body'>
				</tbody>
			</table>
			<div class='col-xs-12'>
				<div class="dataTables_paginate paging_simple_numbers" id="page">
					
				</div>
			</div>
		</div>
		<!-- /.span -->
		<div class='col-xs-12'>
			<button class="btn btn-primary" id="ack-add-btn" data-toggle="modal"
				onclick="Project.eidtUI();">新建</button>
		</div>
	</div>
	<%@include file="../public/footer.jsp"%>
	<%@include file="../public/table.jsp"%>
	<script type="text/javascript"
		src="/static/js/lib/simple-paginator-1.0.js"></script>
	<script type="text/javascript" src="/static/js/project/project.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			Project.init();
		});
	</script>
</body>
</html>