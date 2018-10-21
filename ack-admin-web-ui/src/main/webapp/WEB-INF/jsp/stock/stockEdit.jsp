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
				<input type="hidden" name="id" id="id" value=""/>
				<input type="hidden" name="oldAmount" id="oldAmount" value=""/>
                <input type="hidden" name="productId" id="productId" value="">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right"
                           for="productCode"> 产品编码 </label>

                    <div class="col-sm-9">
                        <input type="text" class="col-xs-10 col-sm-8" placeholder="请输入产品的正确编码，没有请先添加产品。"
                               id="productCode"  name="productCode">

                        </input>
                    </div>
                </div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="productName"> 产品名称 </label>

					<div class="col-sm-9">
						<input type="text" id="productName" placeholder="产品名称"
							name="productName" value="" class="col-xs-10 col-sm-8"readonly="readonly"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="amount"> 产品数量 </label>

					<div class="col-sm-9">
						<input type="number" id="amount" placeholder="产品数量" name="amount"
							value="1" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="inspectorId"> 质检员 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="inspectorId" size="4" name="inspectorId">

						</select>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="品牌备注信息" name="remark"></textarea>
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