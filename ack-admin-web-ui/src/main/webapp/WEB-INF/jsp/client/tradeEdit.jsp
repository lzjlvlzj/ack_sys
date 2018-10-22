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
								   for="remark"> 备注 </label>
							<div class="col-sm-9">
								<textarea class="col-xs-10 col-sm-8" id="remark"
										  placeholder="销售备注信息" name="remark"></textarea>
							</div>
						</div>
					</div>
					<div id="messages" class="tab-pane fade">
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="logisticsId">物流信息 </label>

							<div class="col-sm-9">
								<select class="col-xs-10 col-sm-8" id="logisticsId" size="4" name="logisticsId">

								</select>
							</div>
						</div>
					</div>
					<div id="cltProductTab" class="tab-pane fade">
						<div class="col-xs-12 form-group">
							<div class="col-xs-8">
								<input type="text" id="productName"></input>
							</div>
							<div class="col-xs-4">
							    <button class="btn btn-primary" id="product-find">查询</button>
						    </div>

						</div>
						<table id="" class="table  table-bordered table-hover">
							<thead>
							<tr role="row">
								<th class="sorting_disabled"  aria-label="">序号</th>
								<th class="sorting" tabindex="0" aria-controls="dynamic-table"
									rowspan="1" colspan="1">名称</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">单价</th>
								<%--<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >总价</th>--%>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >是否是赠品</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">数量</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">备注</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >操作</th>
							</tr>
							</thead>

							<tbody id='product-tab-body'>

							</tbody>
						</table>
						<div class="space-3"></div>
						<h4 class="header smaller lighter blue">已添加商品</h4>
						<div class="col-xs-12" style="display: none; color: #F00;" id="product-null-msg">请添加销售的产品</div>
						<table id="simple-table" class="table  table-bordered table-hover">
							<thead>
							<tr role="row">
								<th class="sorting" tabindex="0" aria-controls="dynamic-table"
									rowspan="1" colspan="1">名称</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">数量</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">单价</th>
								<th class="hidden-480 sorting" tabindex="0"
									aria-controls="dynamic-table" rowspan="1" colspan="1">总价</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >是否是赠品</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >备注</th>
								<th class="sorting" rowspan="1" colspan="1"
									aria-label="dynamic-table" >操作</th>
							</tr>
							</thead>

							<tbody id='trade-product-tab-body'>

							</tbody>
						</table>
					</div>
				</div>
			</div>


		</div>



		<div class="modal-footer no-margin-top">
			<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
				<i class="ace-icon fa fa-times "></i> 关闭
			</button>

			<button
					class="pull-right btn btn-sm btn-success no-margin ack-trade-save-btn">
				<i class="ace-icon fa fa-save"></i>保存
			</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->