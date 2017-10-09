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
				信息确认
				<input type="hidden" id="optionFlag" value="0">
			</div>
		</div>

		<div class="modal-body no-padding">
			<form class="form-horizontal" role="form" id="ack-add-form">
			    <input type="hidden" name="id" id="id" value="">
			    <input type="hidden" name="_id" id="_id" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-comments"> 操作</label>

					<div class="radio col-sm-4" id="event-option">
						<label> 
						    <input name="event-option-flag" id="ack-log-del" type="radio"
							class="ace" value="0" checked="checked"/> 
							<span class="lbl"> 删除</span>
						</label>
					</div>
					<div class="radio col-sm-5">
						<label> 
						     <input name="event-option-flag" id="ack-log-edit" type="radio"
							class="ace" value="1"/> 
							<span class="lbl"> 修改</span>
						</label>
					</div>
				</div>
				<div class="form-group" id="ack-del-msg">
					<div class="col-sm-12  col-sm-offset-4" style="font-size:18px">
						确定要删除该条日志?
					</div>
				</div>
				<div class="form-group" id="ack-edit-msg" style="display:none;">
					<label class="col-sm-3 control-label no-padding-right"
						for="form-comments"> 日志信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="event-content"
							placeholder="请输入日志信息" name="content"></textarea>
					</div>
					<small class="col-xs-12 col-sm-12 col-sm-offset-3 ack-form-validator" id="ack-add-log-msg">日志内容不能为空</small>
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