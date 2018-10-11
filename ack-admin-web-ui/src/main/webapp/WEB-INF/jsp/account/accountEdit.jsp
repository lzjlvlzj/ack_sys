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
						for="clientId"> 客户名称 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="clientId" multiple="multiple" name="clientId">
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="productId"> 产品 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="productId" multiple="multiple" name="productId">
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="totalPrice"> 产品原价 </label>

					<div class="col-sm-9">
						<input type="text" id="totalPrice" placeholder="" name=""
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="flow">实际扣款</label>

					<div class="col-sm-9">
						<input type="text" id="flow" placeholder="flow" name="flow"
							   value="" class="col-xs-10 col-sm-8">
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