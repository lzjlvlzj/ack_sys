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
				<input type="hidden" name="managerName" id="managerName" value="">
				<input type="hidden" name="departmentId" id="departmentId" value="">
				<input type="hidden" name="departmentName" id="departmentName" value="">
				
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="projectName"> 项目名称 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="项目名称"
							name="name" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="departmentName"> 部门名称 
					</label>

					<div class="col-sm-9">
						<input type="text" id="deptName" placeholder="部门名称"
							name="deptName" value="" class="col-xs-10 col-sm-8" readonly="readonly">
					</div>
				</div>
				<div class="form-group control-group" id="projectType">
					<label class="col-sm-3 control-label no-padding-right"
						for="type" > 项目类型 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="type" name="type">
						    <option value='0'>非公开</option>
						    <option value='1'>公开</option>
						</select>	 
					</div>
				</div>
				<div class="form-group control-group" id="projectType">
					<label class="col-sm-3 control-label no-padding-right"
						for="managerName" > 项目负责人 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="managerId" name="managerId">
						</select>	 
					</div>
				</div>
				<div class="form-group control-group" id="projectType">
					<label class="col-sm-3 control-label no-padding-right"
						for="cooperativeSector" > 参与部门 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="cooperativeSector" name="cooperativeSector" multiple="multiple">
						     <!-- <option value='0'>非公开</option>
						     <option value='1'>公开2</option>
						     <option value='0'>非公开34</option>
						     <option value='1'>公开22</option> -->
						</select>	 
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 项目备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="项目备注信息" name="remark"></textarea>
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