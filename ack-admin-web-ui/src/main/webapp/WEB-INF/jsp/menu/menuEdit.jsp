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
				菜单信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="menuName"> 菜单名称 </label>

					<div class="col-sm-9">
						<input type="text" id="menuName" placeholder="菜单名称"
							name="menuName" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="url"> url </label>

					<div class="col-sm-9">
						<input type="text" id="url" placeholder="url" name="url"
							value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group control-group" id="menuType">
					<label class="col-sm-3 control-label no-padding-right"
						for="menuType"> 菜单类型 </label>

					<div class="radio col-sm-4">
						<label> <input name="menuType" id="" type="radio"
							class="ace" value="0"> <span class="lbl"> 菜单</span>
						</label>
					</div>
					<div class="radio col-sm-5">
						<label> <input name="menuType" id="" type="radio"
							class="ace" value="1"> <span class="lbl"> 按钮</span>
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="menuLevel"> 菜单等级 </label>

					<div class="col-sm-9">
						<input type="text" id="menulevel" placeholder="菜单等级"
							name="menuLevel" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="permission">权限字符 </label>

					<div class="col-sm-9">
						<input type="text" id="permission" placeholder="权限字符"
							name="permission" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="domId"> domId </label>

					<div class="col-sm-9">
						<input type="text" id="domId" placeholder="页面dom的id(只是左侧菜单用)"
							name="domId" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="css"> 菜单样式 </label>

					<div class="col-sm-9">
						<input type="text" id="css" placeholder="菜单样式(只是左侧菜单用)"
							name="css" value="" class="col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="parentId"> 菜单父id </label>

					<div class="col-sm-9">
						<input type="text" id="parentId" placeholder="父菜单id"
							name="parentId" value="" class="col-xs-10 col-sm-8">

					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="comments"> 菜单备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="comments"
							placeholder="菜单备注信息" name="comments"></textarea>
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