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
				销售信息
				<input type="hidden" id="optionFlag" value="">
			</div>
		</div>

		<div class="modal-body no-padding">
			<!--
			<form class="form-horizontal" role="form" id="ack-add-form">
				<input type="hidden" name="clientId" id="clientId" value="">
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="name"> 客户名称 </label>

					<div class="col-sm-9">
						<input type="text" id="name" placeholder="客户名称"
							name="name" value="" class="col-xs-10 col-sm-8"
						readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="logisticsId">物流信息 </label>

					<div class="col-sm-9">
						<select class="col-xs-10 col-sm-8" id="logisticsId" multiple="multiple" name="brandId">
                            <option>冀A36984</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right"
						for="remark"> 销售备注信息</label>

					<div class="col-sm-9">
						<textarea class="col-xs-10 col-sm-8" id="remark"
							placeholder="销售备注信息" name="remark"></textarea>
					</div>
				</div>
			</form>
			-->
			<div class="col-sm-6">
				<div class="tabbable">
					<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
						<li class="">
							<a data-toggle="tab" href="#home4" aria-expanded="false">Home</a>
						</li>

						<li class="active">
							<a data-toggle="tab" href="#profile4" aria-expanded="true">Profile</a>
						</li>

						<li class="">
							<a data-toggle="tab" href="#dropdown14" aria-expanded="false">More</a>
						</li>
					</ul>

					<div class="tab-content">
						<div id="home4" class="tab-pane">
							<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
						</div>

						<div id="profile4" class="tab-pane active">
							<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid.</p>
						</div>

						<div id="dropdown14" class="tab-pane">
							<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
						</div>
					</div>
				</div>
			</div>
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