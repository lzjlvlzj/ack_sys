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
				客户信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="name"> 客户名称 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="客户名称"
							name="name" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="address"> 客户地址 </label>

					<div class="col-sm-9">
						<input type="text" id="address" placeholder="客户地址" name="address"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="phone"> 客户电话 </label>

					<div class="col-sm-9">
						<input type="text" id="phone" placeholder="phone" name="phone"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="qq"> 客户QQ </label>

					<div class="col-sm-9">
						<input type="text" id="qq" placeholder="phone" name="qq"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="weiXin"> 客户微信 </label>

					<div class="col-sm-9">
						<input type="text" id="weiXin" placeholder="weiXin" name="weiXin"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="userId"> 美导老师 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="userId" multiple="multiple" name="userId">

						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 客户备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="客户备注信息" name="remark"></textarea>
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