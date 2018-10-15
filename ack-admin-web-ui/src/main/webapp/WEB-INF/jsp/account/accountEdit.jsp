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
				<input type="hidden" name="clientId" id="clientId" value="">
				<input type="hidden" name="userId" id="userId" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="clientName"> 客户名称 </label>

					<div class="col-sm-9">
						<input type="text" id="clientName" placeholder="" name="clientName"
							   value="" class="col-xs-10 col-sm-8" readonly="readonly">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="balance">账户余额</label>

					<div class="col-sm-9">
						<input type="text" id="balance" placeholder="余额" name="balance" readonly="readonly"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="flow">流转金额</label>

					<div class="col-sm-9">
						<input type="text" id="flow" placeholder="flow" name="flow" readonly="readonly"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="tradeNumber">交易流水</label>

					<div class="col-sm-9">
						<input type="text" id="tradeNumber" placeholder="flow" name="tradeNumber"
							   value="" class="col-xs-10 col-sm-8" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="flowCase"> 扣款原因 </label>

					<div class="col-sm-9">
						<input type="text" id="flowCase" placeholder="flowCase" name="flowCase"
							   value="" class="col-xs-10 col-sm-8">
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