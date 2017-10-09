<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../public/header.jsp"%>
<link href='/static/3part/fullcalendar/fullcalendar.min.css'
	rel='stylesheet' />
<link href='/static/3part/fullcalendar/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<link href='/static/3part/jqueryQtip/jquery.qtip.min.css'
	rel='stylesheet' media='print' />
<title></title>
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-sm-3">
					<div class="widget-box ack-widget">
						<div class="widget-header">
							<h4>日志信息条目</h4>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<form action="" id="log-item-form" class='form-horizontal'>
									<div id="external-events">
									</div>
									<div class="form-actions text-right">
										<button type="button" class="btn btn-sm btn-primary"
											id="del-event">删除缓存</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="widget-box ">
						<div class="widget-header">
							<h4 class="widget-title">新建日志信息</h4>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<form id="add-log-form" class="form-horizontal">
									<input type="hidden" name="color" id="ack-log-event-color"
										value="" />
									<fieldset>
										<ul class="fc-color-picker" id="color-chooser">
											<li><a class="text-blue" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-aqua" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-light-blue" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-teal" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-yellow" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-orange" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-green" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-lime" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-red" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-purple" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-fuchsia" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-muted" href="#"><i
													class="fa fa-square"></i></a></li>
											<li><a class="text-navy" href="#"><i
													class="fa fa-square"></i></a></li>
										</ul>
									</fieldset>
									<fieldset >
										<select class="col-xs-12 col-sm-12" id="projectId"
											name="projectId">
											<option value="">--请选择工作计划--</option>
										</select>
									</fieldset>
									<fieldset>
										<textarea type="text" id="new-event" placeholder="请输入日志信息"
											name="content" value="" class="col-xs-12 col-sm-12"></textarea>
										<small class="col-xs-12 col-sm-12 ack-form-validator" id="ack-add-log-msg">日志内容不能为空</small>	
									</fieldset>

									<div class="form-actions text-right">
										<button type="button"
											class="ack-btn btn-sm ack-btn-primary ack-btn-right"
											id="add-new-event">新建日志</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-9">
					<div class="space"></div>

					<div id="calendar" class="fc fc-ltr fc-unthemed"></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../public/footer.jsp"%>
	<script type="text/javascript"
		src="/static/3part/ace/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="/static/3part/jqueryQtip/jquery.qtip.min.js"></script>
	<script type="text/javascript"
		src="/static/3part/fullcalendar/lib/moment.min.js"></script>
	<script type="text/javascript"
		src="/static/3part/fullcalendar/fullcalendar.min.js"></script>
	<script type="text/javascript"
		src="/static/3part/fullcalendar/locale-all.js"></script>
	<script type="text/javascript"
		src="/static/js/employeeJob/employeeJob.js"></script>
	<script type="text/javascript">
        $(document).ready(function(){
        	EmployeeJob.init();
        });
    </script>
</body>
</html>