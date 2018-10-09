/**
 * 产品js
 */
var Product = window.Product || {};

Product.modal = parent.AckSystem.modal;

Product.document = window.parent.document || window.document;

Product.config = function(){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Product.getOneTr;
	return option;
}

Product.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var item;
	var tr = $("<tr></tr>");
	tr.attr("id",data.id);
	//序号
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	//产品名称
	var ProductName = $("<td>"+data.name+"</td>");
	tr.append(ProductName);
	//产品url
	var url = $("<td>"+data.url+"</td>");
	tr.append(url);
	//产品类型
	var ProductType = $("<td>"+data.type+"</td>");
	tr.append(ProductType);
	//产品单价
	var ProductUnitPrice = $("<td>"+data.unitPrice+"</td>");
	tr.append(ProductUnitPrice);
	//备注
	var comments = $("<td>"+data.remark+"</td>");
	tr.append(comments);
	//创建时间
	var tm = AckTool.date(data.createTime, "yyyy-MM-dd hh:mm:ss");
	var createTime = $("<td>"+tm+"</td>");
	tr.append(createTime);
	//操作按钮
	var optionTd = $("<td></td>");
	optionTd.append(AckTool.optionButton.simpleOption);
	tr.append(optionTd);
	return tr;
}

/**
 * 
 * 初始化显示
 * */
Product.showList = function() {
	//主要option在AckTool.table里面有默认值
	var option = Product.config();
	var data = {};
	var url = "/product/page";
	AckTool.postReq(data,url,function(obj) {
		if (obj) {
			option.data = obj.result;
			var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Product.list};
			$("#page").paginator(conf);
			AckTool.table.show(option);
			
		}
	});
}

Product.list = function(pageNo){
	var url = "/product/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = Product.config();
	AckTool.postReq(data,url,function(obj){
		if( obj ){
			option.data = obj.result;
			AckTool.table.show(option);
		}
	});
}
/**
 * 编辑页面
 * @param id 数据id
 * @param flag 0 : 导航产品 , 1 : 功能产品
 * */
Product.eidtUI = function(id) {
	
	var url = "";
	var data = {};
	data.reqData = {};
	if(id){
	   url = "/product/edit/ui/"+id;
	   var ProductDataUrl = "/product/id/" + id;
	   //这里需要有个请求回显数据
	   Product.modal.open(url,data,function(){
		   AckTool.postReq({},ProductDataUrl,function(obj){
			   $("#optionFlag",Product.document).val("1");
			   $("#id",Product.document).val(obj.id);
			   $("#ProductName",Product.document).val(obj.ProductName);
			   $("#url",Product.document).val(obj.url);
			   var inputs = $("#ProductType",Product.document).find("input");
			   inputs.each(function(){
				   var val = $(this).val();
				   if(val == obj.ProductType){
					   $(this).attr("checked","checked");
				   }
			   });
			   $("#Productlevel",Product.document).val(obj.Productlevel);
			   $("#permission",Product.document).val(obj.permission);
			   $("#domId",Product.document).val(obj.domId);
			   $("#css",Product.document).val(obj.css);
			   $("#parentId",Product.document).val(obj.parentId);
			   $("#comments",Product.document).val(obj.comments);
		   });
	   });
	} else {
	   url = "/product/add/ui";
	   Product.modal.open(url,data,function(){
		   $("#optionFlag",Product.document).val("0");
	   });
	}
}

/**
 * 编辑操作
 */

Product.eidt = function(flag) {
	var url = "";
	//添加
	if("0" == flag){
		url = "/product/add"
	}
	if("1" == flag){
		url = "/product/edit"
	}
	
	var data = $("#ack-add-form", Product.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Product.modal.close();
			//刷新当前页面
			Product.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Product.modal.close();
		}
		
	});
	
}
/**
 * 删除
 * 
 * */
Product.del = function(id){
	var url = "/product/del/"+id;
	var modalUrl = "";
	var option = {fun : {}};
	option.header = "确认操作";
	option.headerCss = "ack-medal-header-yellow";
	option.content = "确认删除该条记录?";
	option.fun.selector = ".ack-modal-ok-btn";
	var data = {};
	//点击弹框"确定"的回调操作
	option.fun.callback = function(){
		AckTool.postReq(data, url, function(obj) {
			if (obj == 1) {
				//关闭modal
				Product.modal.close();
				//刷新当前页面
				Product.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Product.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
	
}

/**
 * 绑定事件
 * */
Product.bind = function() {
	var ackModal = $("#ack-modal", Product.document);
	//修改
	$("#tab-body").on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		Product.eidtUI(id);
	});
	//删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	Product.del(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", Product.document);
		var flag = fp.val();
		Product.eidt(flag);
	});
}

Product.init = function (){
	Product.showList();
	Product.bind();
}


