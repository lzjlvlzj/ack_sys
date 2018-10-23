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
				退货信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<input type="hidden" name="productId" id="productId" value="">
				<input type="hidden" name="clientId" id="clientId" value="">
				<input type="hidden" name="coin" id="coin" value="0">
				<input type="hidden" name="oldAmount" id="oldAmount" value="0">
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
						   for="clientName"> 客户名称 </label>

					<div class="col-sm-9">
						<input type="text" id="clientName" placeholder="客户手机" readonly="readonly"
							   name="clientName" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="productCode"> 产品编码 </label>

					<div class="col-sm-9">
						<input type="text" id="productCode" placeholder="产品编码" name="productCode"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="productName"> 产品名称 </label>

					<div class="col-sm-9">
						<input type="text" id="productName" placeholder="产品名称"
							   readonly="readonly" name="productName"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="amount"> 退货数量 </label>

					<div class="col-sm-9">
						<input type="number" id="amount" placeholder="amount" name="amount"
							   onkeyup="AckTool.formValidator.number(this);" onafterpaste="AckTool.formValidator.number(this);"
							   value="0" class="col-xs-10 col-sm-8">
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="退货备注信息" name="remark"></textarea>
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