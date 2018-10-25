<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../public/header.jsp"%>
<script
	src="${pageContext.request.contextPath}/static/3part/ace/js/ace-extra.min.js"></script>
<title>交易管理</title>
</head>
<body>
	<!-- table2 -->
	<div class="row">
		<div class="col-xs-12">
			<h3 class="header smaller lighter blue">交易管理</h3>
			<div class="" id="query-div" style="display: none">
				<form class="form-horizontal" role="form" id="ack-query-form">
					<input type="hidden" name="clientId" id="clientId" value="">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							   for="sellerId"> 美导登录名 </label>

						<div class="col-sm-2">
							<input type="text" id="sellerId" placeholder="美导登录名"
								   name="sellerId" value="" class="col-xs-10 col-sm-8"/>
						</div>
						<div class="col-sm-2">
							<input type="text" id="startTime" placeholder="开始时间"
								   name="startTime" value="" class="col-xs-10 col-sm-8"/>
						</div>
						<div class="col-sm-2">
							<input type="text" id="endTime" placeholder="结束时间"
								   name="endTime" value="" class="col-xs-10 col-sm-8"/>
						</div>
						<div class="col-sm-3">
							<input type="button" class="btn btn-primary" id="trade-statistics" onclick="Trade.statistics();" value="统计" size="4"></input>
						</div>

					</div>

				</form>
			</div>
			<div class="space-3"></div>
			<table id="simple-table" class="table  table-bordered table-hover">
				<thead>
					<tr role="row">
						<th class="sorting_disabled"  aria-label="">序号</th>
						<th class="sorting" tabindex="0" aria-controls="dynamic-table"
							rowspan="1" colspan="1">流水号</th>
						<th class="hidden-480 sorting" tabindex="0"
							aria-controls="dynamic-table" rowspan="1" colspan="1">客户名称</th>
						<th class="hidden-480 sorting" tabindex="0"
							aria-controls="dynamic-table" rowspan="1" colspan="1">美导账号</th>
						<th class="hidden-480 sorting" tabindex="0"
							aria-controls="dynamic-table" rowspan="1" colspan="1">美导老师</th>
						<th class="hidden-480 sorting" tabindex="0"
							aria-controls="dynamic-table" rowspan="1" colspan="1">状态</th>
						<th class="sorting" rowspan="1" colspan="1"
							aria-label="dynamic-table">修改时间</th>
						<th class="sorting" rowspan="1" colspan="1"
							aria-label="dynamic-table">备注</th>	
						<th class="sorting" tabindex="0" aria-controls="dynamic-table"
							rowspan="1" colspan="1"><i
							class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> 添加时间</th>
						<th class="sorting_disabled" rowspan="1" colspan="1" aria-label="">操作</th>
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
		<%--<div class='col-xs-12'>
			<button class="btn btn-primary" id="ack-add-btn" data-toggle="modal"
				onclick="Trade.eidtUI();">新建交易</button>
		</div>--%>
	</div>

	<%@include file="../public/footer.jsp"%>
	<%@include file="../public/table.jsp"%>
	<script type="text/javascript"
		src="/static/js/lib/simple-paginator-1.0.js"></script>
	<script type="text/javascript" src="/static/js/trade/trade.js"></script>
	<script type="text/javascript">
        $(document).ready(function(){
            Trade.init();
        });
    </script>
</body>
</html>