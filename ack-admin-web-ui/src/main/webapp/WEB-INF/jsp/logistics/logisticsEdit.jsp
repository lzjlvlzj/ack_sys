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
				品牌信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<input type="hidden" name="clientId" id="clientId" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="clientPhone"> 客户手机 </label>

					<div class="col-sm-9">
						<input type="text" id="clientPhone" placeholder="客户手机"
							name="clientPhone" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="vehicle"> 车辆信息 </label>

					<div class="col-sm-9">
						<input type="text" id="vehicle" placeholder="车辆信息" name="vehicle"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="postMan"> 司机名称 </label>

					<div class="col-sm-9">
						<input type="text" id="postMan" placeholder="司机名称" name="postMan"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="phone"> 司机手机 </label>

					<div class="col-sm-9">
						<input type="text" id="phone" placeholder="司机手机" name="phone"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="address"> 收货地址 </label>

					<div class="col-sm-9">
						<input type="text" id="address" placeholder="收货地址" name="address"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="postalCode"> 邮编 </label>

					<div class="col-sm-9">
						<input type="text" id="postalCode" placeholder="postalCode" name="postalCode"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="备注信息" name="remark"></textarea>
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