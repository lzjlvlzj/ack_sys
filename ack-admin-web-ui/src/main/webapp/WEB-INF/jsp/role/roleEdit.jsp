<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header no-padding">
			<div class="ack-medal-header-default">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<span class="white">×</span>
				</button>
				角色信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
			    <input type="hidden" name="id" id="id" value="">
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="roleName"> 角色名称 </label>

					<div class="col-sm-9">
						<input type="text" placeholder="角色名称"
							name="roleName" id="roleName" value="" class="form-control col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="abbreviation"> 角色简称</label>

					<div class="col-sm-9">
						<input type="text" placeholder="角色简称"
							name="abbreviation" id="abbreviation" value="" class="form-control col-xs-10 col-sm-8">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="abbreviation"> 角色权重</label>

					<div class="col-sm-9">
						<input type="text" placeholder="角色权重(权重高的可以给低的授权)"
							name="weight" id="weight" value="" class="form-control col-xs-10 col-sm-8">
					</div>
				</div>
				
				<div class="form-group" id="viewStatus">
					<label class="col-sm-2 control-label no-padding-right"
						for="menuType"> 查看类型 </label>

					<div class="radio col-sm-4">
						<label> <input name="viewStatus" id="" type="radio"
							class="ace" value="1"> <span class="lbl"> 全部</span>
						</label>
					</div>
					<div class="radio col-sm-5">
						<label> <input name="viewStatus" id="" type="radio" checked="checked"
							class="ace" value="0"> <span class="lbl"> 部分</span>
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="menuIds"> 菜单id </label>

					<div class="col-sm-9">
						<input type="text" placeholder="菜单id"
							name="menuIds" id="menuIds" value="" class="form-control col-xs-10 col-sm-8">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label no-padding-right"
						for="form-comments"> 角色备注信息</label>

					<div class="col-sm-9">
						<textarea class="form-control col-xs-10 col-sm-8" id="comments"
							placeholder="角色备注信息" name="comments"></textarea>
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