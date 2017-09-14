<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../public/common.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header no-padding">
			<div class="ack-medal-header-default">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<span class="white">×</span>
				</button>
				项目任务信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="projectId"> 项目名称 
					</label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="projectId" name="projectId">
						     <option>--请选择项目--</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="task"> 项目任务内容 </label>
					<div class="col-sm-9">
					    <textarea class="col-xs-10 col-sm-8" placeholder="项目任务名称" name="task" id="task"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="priority"> 优先级 
					</label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="priority" name="priority">
						     <option value="1">1</option>
						     <option value="2">2</option>
						     <option value="3">3</option>
						     <option value="4">4</option>
						     <option value="6">5</option>
						     <option value="7">6</option>
						     <option value="8">7</option>
						     <option value="9">9</option>
						</select>
					</div>
				</div>

			</form>
		</div>

		<div class="modal-footer no-margin-top">
			<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
				<i class="ace-icon fa fa-times "></i> 关闭
			</button>

			<button
				class="pull-right btn btn-sm btn-success no-margin ack-modal-save-btn">
				<i class="ace-icon fa fa-save"></i>保存
			</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->