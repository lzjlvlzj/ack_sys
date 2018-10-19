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
								<select class="col-xs-10 col-sm-8" id="logisticsId" multiple="multiple" name="brandId">
									<option>冀A36984</option>
									<option>冀A36984</option>
									<option>冀A36984</option>
									<option>冀A36984</option>
								</select>
							</div>
						</div>
					</div>
					<div id="cltProductTab" class="tab-pane fade">
						<div class="row form-group">
						   <label class="col-sm-3 control-label no-padding-right"
							   for="productId"> 产品名称 </label>
						    <div class="col-sm-9">
							    <input type="text" id="productId" placeholder="产品名称"
								   name="productId" value="" class="col-xs-10 col-sm-8"/>
							</div>
					    </div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="amount"> 产品数量 </label>
							<div class="col-sm-9">
								<input type="number" id="amount" placeholder="产品数量"
									   name="amount" value="" class="typeahead scrollable tt-input col-xs-10 col-sm-8"/>
							</div>
						</div>
						<div class="row form-group">
							<label class="col-sm-3 control-label no-padding-right"
								   for="unitPrice"> 产品单价 </label>
							<div class="col-sm-9">
								<input type="text" id="unitPrice" placeholder="产品单价"
									   name="unitPrice" value="" class="col-xs-10 col-sm-8"/>
							</div>
						</div>
						<div class="row form-group control-group" id="productType">
							<label class="col-sm-3 control-label no-padding-right"
								   for="productType"> 产品类型 </label>

							<div class="radio col-sm-4">
								<label> <input name="type"  checked="checked" type="radio"
											   class="ace" value="0"> <span class="lbl"> 非赠品</span>
								</label>
							</div>
							<div class="radio col-sm-5">
								<label> <input name="type"  type="radio"
											   class="ace" value="1"> <span class="lbl"> 赠品</span>
								</label>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-sm-9">
								<button class="btn btn-primary">添加</button>
							</div>
						</div>
					</div>


				</div>
			</div>
			<div class="col-xs-12">
				<table id="simple-table" class="table  table-bordered table-hover">
					<thead>
					<tr role="row">
						<th class="sorting_disabled"  aria-label="">序号</th>
						<th class="sorting" tabindex="0" aria-controls="dynamic-table"
							rowspan="1" colspan="1">产品名称</th>
						<th class="hidden-480 sorting" tabindex="0"
							aria-controls="dynamic-table" rowspan="1" colspan="1">产品数量</th>
						<th class="hidden-480 sorting" tabindex="0"
							aria-controls="dynamic-table" rowspan="1" colspan="1">产品单位</th>
						<th class="sorting" rowspan="1" colspan="1"
							aria-label="dynamic-table" >产品规格</th>
						<th class="sorting" rowspan="1" colspan="1"
							aria-label="dynamic-table" >操作</th>
					</tr>
					</thead>

					<tbody id='tab-body'>
                       <tr>
						   <td>1</td>
						   <td>1</td>
						   <td>1</td>
						   <td>1</td>
						   <td>1</td>
						   <td>删除</td>
					   </tr>
					</tbody>
				</table>
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