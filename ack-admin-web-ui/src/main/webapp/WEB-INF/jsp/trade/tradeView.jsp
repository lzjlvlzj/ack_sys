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
			<input type="hidden" name="clientId" id="clientId" value="">
			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active">
						<a data-toggle="tab" href="#home" aria-expanded="true">
							<i class="green ace-icon fa fa-home bigger-120"></i>
							客户信息
						</a>
					</li>

					<li class="">
						<a data-toggle="tab" href="#messages" aria-expanded="false">
							物流信息
						</a>
					</li>
					<li class="">
						<a data-toggle="tab" href="#cltProductTab" aria-expanded="false">
							产品信息
						</a>
					</li>
					<li class="">
						<a data-toggle="tab" href="#cltAccountTab" aria-expanded="false">
							账号余额
						</a>
					</li>
				</ul>

				<div class="tab-content">
					<div id="home" class="tab-pane fade in active">

						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="name"> 客户名称 </label>
							<div class="col-sm-9">
								<input type="text" id="name" placeholder="客户名称"
									   name="name" value="" class="col-xs-10 col-sm-8"
									   readonly="readonly">
							</div>
						</div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="phone"> 客户电话 </label>
							<div class="col-sm-9">
								<input type="text" id="phone" placeholder="客户电话"
									   name="phone" value="" class="col-xs-10 col-sm-8"
									   readonly="readonly">
							</div>
						</div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="address"> 客户地址 </label>
							<div class="col-sm-9">
								<input type="text" id="address" placeholder="客户电话"
									   name="address" value="" class="col-xs-10 col-sm-8"
									   readonly="readonly">
							</div>
						</div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="remark"> 备注 </label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-8" id="remark" readonly="readonly"
										  placeholder="销售备注信息" name="remark"></textarea>
							</div>
						</div>
					</div>
					<div id="messages" class="tab-pane fade">
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="vehicle">物流信息 </label>

							<div class="col-sm-9">
								<input type="text" class="col-xs-10 col-sm-8" id="vehicle"
										name="vehicle"  readonly="readonly"></input>
							</div>
						</div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="postMan">司机姓名</label>

							<div class="col-sm-9">
								<input type="text" class="col-xs-10 col-sm-8" id="postMan"
									   name="postMan"  readonly="readonly"></input>
							</div>
						</div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="postPhone">电话 </label>

							<div class="col-sm-9">
								<input type="text" class="col-xs-10 col-sm-8" id="postPhone"
									   name="postPhone"  readonly="readonly"></input>
							</div>
						</div>
					</div>
					<div id="cltProductTab" class="tab-pane fade">

						<table id="" class="table  table-bordered table-hover">
							<thead>
							<tr role="row">
								<th class="sorting_disabled"  aria-label="">序号</th>
								<th class="sorting" tabindex="0" aria-controls="dynamic-table"
									rowspan="1" colspan="1">编码</th>
								<th class="sorting" tabindex="0" aria-controls="dynamic-table"
									rowspan="1" colspan="1">名称</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">单价</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">数量</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">单位</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >总价</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >是否是赠品</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >备注</th>
							</tr>
							</thead>

							<tbody id='product-tab-body'>

							</tbody>
						</table>


					</div>
					<div id="cltAccountTab" class="tab-pane fade">
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="postMan">账号余额</label>

							<div class="col-sm-9">
								<span id="accountCoin"></span> ￥
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>



		<div class="modal-footer no-margin-top">
			<button class="btn btn-sm btn-danger pull-right" data-dismiss="modal">
				<i class="ace-icon fa fa-times "></i> 关闭
			</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->