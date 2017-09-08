<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/header.jsp"%>
<link rel="stylesheet" href="/static/3part/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<title>角色列表</title>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<h3 class="header smaller lighter blue">角色管理</h3>
			<table id="simple-table" class="table  table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">序号</th>
						<th>角色名称</th>
						<th>角色简写</th>
						<th>menusId</th>
						<th>备注</th>
						<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
							创建时间</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody id='tab-body'>
					<tr>
						<td class="center">1</td>

						<td class="center">
							<div class="action-buttons">
								<a href="#" class="green bigger-140 show-details-btn"
									title="Show Details"> <i
									class="ace-icon fa fa-angle-double-down"></i> <span
									class="sr-only">Details</span>
								</a>
							</div>
						</td>

						<td><a href="#">admin</a></td>
						<td>2017-03-21 12:30:24</td>
						<td>
							<div class="hidden-sm hidden-xs btn-group">
								<button class="btn btn-xs btn-info">
									<i class="ace-icon fa fa-pencil bigger-120"></i>
								</button>

								<button class="btn btn-xs btn-danger">
									<i class="ace-icon fa fa-trash-o bigger-120"></i>
								</button>
							</div>

							<div class="hidden-md hidden-lg">
								<div class="inline pos-rel">
									<button class="btn btn-minier btn-primary dropdown-toggle"
										data-toggle="dropdown" data-position="auto">
										<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
									</button>

									<ul
										class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
										<li><a href="#" class="tooltip-info" data-rel="tooltip"
											title="" data-original-title="View"> <span class="blue">
													<i class="ace-icon fa fa-search-plus bigger-120"></i>
											</span>
										</a></li>

										<li><a href="#" class="tooltip-success"
											data-rel="tooltip" title="" data-original-title="Edit"> <span
												class="green"> <i
													class="ace-icon fa fa-pencil-square-o bigger-120"></i>
											</span>
										</a></li>

										<li><a href="#" class="tooltip-error" data-rel="tooltip"
											title="" data-original-title="Delete"> <span class="red">
													<i class="ace-icon fa fa-trash-o bigger-120"></i>
											</span>
										</a></li>
									</ul>
								</div>
							</div>
						</td>
					</tr>

				</tbody>
			</table>
		</div>
		
		<!-- /.span -->
		<div class='col-xs-12'>
			<button class="btn btn-primary" id="ack-add-btn" data-toggle="modal"
				onclick="Role.eidtUI();">新建</button>
		</div>
	</div>
	<%@include file="../public/footer.jsp"%>
	<%@include file="../public/table.jsp"%>
	<script type="text/javascript" src="/static/js/role/role.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			Role.init();
		});
	</script>
</body>
</html>