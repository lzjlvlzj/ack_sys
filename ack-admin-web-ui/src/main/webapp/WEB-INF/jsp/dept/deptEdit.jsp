<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../public/common.jsp" %>	
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header no-padding">
			<div class="ack-medal-header-default">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<span class="white">×</span>
				</button>
				部门信息
				<input type="hidden" id="optionFlag" name="optionFlag" value="${optionFlag}"/>
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
			    <input type="hidden" name="id"  id="id" value="">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="departmentName"> 部门名称 </label>

					<div class="col-sm-9">
						<input type="text" id="departmentName" placeholder="部门名称"
							name="departmentName" value="" class="form-control col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="parentId"> 部门父id </label>

					<div class="col-sm-9">
					   <input type="text" id="parentId" placeholder="父部门id"
							name="parentId" value="" class="form-control col-xs-10 col-sm-8">
						
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="comments"> 部门备注信息</label>

					<div class="col-sm-9">
						<textarea class="form-control col-xs-10 col-sm-8" id="comments"
							placeholder="部门备注信息" name="comments"></textarea>
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