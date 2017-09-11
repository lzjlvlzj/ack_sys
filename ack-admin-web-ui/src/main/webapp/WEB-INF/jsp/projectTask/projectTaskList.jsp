<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/header.jsp"%>
<title></title>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<h3 class="header smaller lighter blue">项目任务管理</h3>
			<div class="table-header">项目任务列表</div>
			<!-- div.table-responsive -->
			<!-- div.dataTables_borderWrap -->
			<div>
				<div id="dynamic-table_wrapper"
					class="dataTables_wrapper form-inline no-footer">
					<table id="ack-dynamic-table"
						class="table table-striped table-bordered table-hover dataTable no-footer"
						role="grid" aria-describedby="dynamic-table_info">
						<thead>
							<tr role="row">
								<!-- <th class="sorting_disabled" 
									aria-label="">序号</th> -->
								<th class="sorting" tabindex="0" aria-controls="dynamic-table">
									工程名称
								</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" >
									任务内容
								</th>
								<th class="sorting" 
									aria-label="dynamic-table">
									责任人
                                </th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" >
									任务状态
								</th>
								<th class="sorting" 
									aria-label="dynamic-table">
									优先级
                                </th>
								<th class="sorting" 
									aria-label="dynamic-table">
									开始时间
                                </th>
								<th class="sorting" 
									aria-label="dynamic-table">
									结束时间
                                </th>
								<th class="sorting_disabled" 
									aria-label="">操作</th>
							</tr>
						</thead>
                        <!-- body -->
						<!-- <tbody id='tab-body'>
						</tbody> -->
						<tfoot>
                        </tfoot>
					</table>
				</div>
			</div>
		</div>
		<div class='col-xs-12'>
			<div class='col-xs-12 ack-datatable-new'>
			  <button class="btn btn-primary" id="ack-add-btn" onclick="ProjectTask.eidtUI();">新建</button>
			</div>
		</div>
	</div>
	<%@include file="../public/footer.jsp"%>
	<%@include file="../public/table.jsp"%>
	<script type="text/javascript" src="/static/js/projectTask/projectTask.js"></script>
	<script type="text/javascript" src="/static/js/lib/simple-paginator-1.0.js"></script>
	<script type="text/javascript">
        $(document).ready(function(){
            ProjectTask.init();
        });
    </script>
</body>
</html>