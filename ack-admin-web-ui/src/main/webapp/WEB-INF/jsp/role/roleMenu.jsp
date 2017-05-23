<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/static/3part/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
			     <input type="hidden" name="id" id="ack-role-id" value="">
				 <div class="zTreeDemoBackground left">
                   <ul id="act_tree" class="ztree"></ul>
                 </div>
			</form>
		</div>

		<div class="modal-footer no-margin-top">
			<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
				<i class="ace-icon fa fa-times "></i> 关闭
			</button>

			<button
				class="pull-right btn btn-sm btn-success no-margin ack-modal-role-menu-save-btn">
				<i class="ace-icon fa fa-save"></i>保存
			</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
<script type="text/javascript" src="/static/3part/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/static/3part/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/static/js/role/rolemenu.js"></script>