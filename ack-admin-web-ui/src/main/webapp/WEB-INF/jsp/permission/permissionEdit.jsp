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
				${optionName}
				<input type="hidden" id="optionFlag" name="optionFlag" value="${optionFlag}"/>
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
			    <input type="hidden" name="id" value="${permission.id}">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-roleName"> 权限名称 </label>

					<div class="col-sm-9">
						<input type="text" id="form-menuName" placeholder="权限名称 "
							name="permissionName" value="${permission.permissionName }" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-roleName"> 权限标识 </label>

					<div class="col-sm-9">
						<input type="text" id="form-url" placeholder="url"
							name="flag" value="${permission.flag }" class="col-xs-10 col-sm-8">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-comments"> 菜单备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="form-comments"
							placeholder="菜单备注信息" name="comments">${permission.comments}</textarea>
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