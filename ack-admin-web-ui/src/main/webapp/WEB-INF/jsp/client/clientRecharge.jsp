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
				客户充值
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="clientId" id="clientId" value="">
				<input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="name"> 客户名称 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="客户名称"
							name="name" value="" readonly="readonly" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="flow"> 充值金额 </label>

					<div class="col-sm-9">
						<input type="number" id="flow" placeholder="请填写充值金额" name="flow"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="coinFlow"> 点券金额 </label>

					<div class="col-sm-9">
						<input type="number" id="coinFlow" placeholder="请填写点券金额" name="coinFlow"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="cause"> 充值原因 </label>

					<div class="col-sm-9">
						<input type="text" id="cause" placeholder="请填写充值原因" name="cause"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="请填写备注信息" name="remark"></textarea>
					</div>
				</div>
			</form>
		</div>

		<div class="modal-footer no-margin-top">
			<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
				<i class="ace-icon fa fa-times "></i> 关闭
			</button>

			<button
				class="pull-right btn btn-sm btn-success no-margin ack-recharge-save-btn">
				<i class="ace-icon fa fa-save"></i>保存
			</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->