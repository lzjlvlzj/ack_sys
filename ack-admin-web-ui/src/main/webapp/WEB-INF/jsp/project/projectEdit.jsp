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
				项目信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="projectName"> 项目名称 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="项目名称"
							name="name" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<!-- <div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="departmentName"> 部门名称 
					</label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="departmentName">
						</select>
					</div>
				</div> -->
				<div class="form-group control-group" id="projectType">
					<label class="col-sm-3 control-label no-padding-right"
						for="managerName" > 项目经理 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="managerName" name="managerName">
						</select>	 
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="comments"> 项目备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="comments"
							placeholder="项目备注信息" name="comments"></textarea>
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