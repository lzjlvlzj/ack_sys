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
				产品信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="brandId"> 品牌名称 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="brandId" multiple="multiple" name="brandId">

						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="name"> 产品名称 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="产品名称"
							name="name" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="unitPrice"> 产品单价(￥) </label>

					<div class="col-sm-9">
						<input type="number" id="unitPrice"  placeholder="产品单价" name="unitPrice"
							value="" class="col-xs-10 col-sm-8" onkeyup="">
					</div>
				</div>
				<!--
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="url"> url </label>

					<div class="col-sm-9">
						<input type="text" id="url" placeholder="url" name="url"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				-->
				<div class="form-group control-group" id="productType">
					<label class="col-sm-3 control-label no-padding-right"
						for="productType"> 产品类型 </label>

					<div class="radio col-sm-4">
						<label> <input name="type"  checked="checked" type="radio"
							class="ace" value="0"> <span class="lbl"> 液体</span>
						</label>
					</div>
					<div class="radio col-sm-5">
						<label> <input name="type"  type="radio"
							class="ace" value="1"> <span class="lbl"> 粉末</span>
						</label>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 产品备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="产品备注信息" name="remark"></textarea>
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