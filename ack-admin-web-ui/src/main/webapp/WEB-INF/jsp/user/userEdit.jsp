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
				用户信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<div class="col-xs-12" id="msgDiv" style="display: none;color: #FF0000; text-align: center"></div>
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="loginName"> 登录名 </label>

					<div class="col-sm-9">
						<input type="text" id="loginName" placeholder="登录名称"
							   name="loginName" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="surname"> 姓氏 </label>

					<div class="col-sm-9">
						<input type="text" id="surname" placeholder="姓氏"
							   name="surname" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="name"> 名 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="名字"
							   name="name" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="phone"> 用户电话 </label>

					<div class="col-sm-9">
						<input type="text" id="phone" placeholder="phone" name="phone"
							   value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						   for="address"> 用户地址 </label>

					<div class="col-sm-9">
						<input type="text" id="address" placeholder="用户地址" name="address"
							   value="" class="col-xs-10 col-sm-8">
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