/**
 * 菜单js
 */
var Department = window.Department || {};

Department.modal = parent.AckSystem.modal;

Department.document = window.parent.document || window.document;

Department.config = function(){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Department.getOneTr;
	return option;
}

Department.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var item;
	var tr = $("<tr></tr>");
	tr.attr("id",data.id);
	//序号
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	//菜单名称
	var departmentName = $("<td>"+data.departmentName+"</td>");
	tr.append(departmentName);
	//父id
	var parentId = $("<td>"+data.parentId+"</td>");
	tr.append(parentId);
	//备注
	var comments = $("<td>"+data.comments+"</td>");
	tr.append(comments);
	//创建时间
	var createTime = $("<td>"+data.createTime+"</td>");
	tr.append(createTime);
	//操作按钮
	var optionTd = $("<td></td>");
	//获得操作按钮
	var data = option.data;
	var opt = {};
	opt.data = data;
	opt.prefix = "dept";
	var buttons = AckTool.optionButton.getTrAuthButtons(opt);
	optionTd.append(buttons);
	tr.append(optionTd);
	return tr;
}

/**
 * 
 * 初始化显示
 * */
Department.showList = function() {
	//主要option在AckSystem.table里面有默认值
	var option = Department.config();
	var data = {};
	var url = "/dept/page";
	AckTool.postReq(data,url,function(obj) {
		if (obj) {
			option.data = obj.result;
			var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Department.list};
			$("#page").paginator(conf);
			AckTool.table.show(option);
		}
	});
}

Department.list = function(pageNo){
	var url = "/dept/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = Department.config();
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
 * @param flag 0 : 导航菜单 , 1 : 功能菜单
 * */
Department.eidtUI = function(id) {
	var url = "";
	var data = {};
	data.reqData = {};
	if(id){
	   url = "/dept/edit/ui/"+id;
	   var deptDataUrl = "/dept/id/" + id;
	   //这里需要有个请求回显数据
	   Department.modal.open(url,data,function(){
		   AckTool.postReq({},deptDataUrl,function(obj){
			   $("#optionFlag",Department.document).val("1");
			   $("#id",Department.document).val(obj.id);
			   $("#departmentName",Department.document).val(obj.departmentName);
			   $("#parentId",Department.document).val(obj.parentId);
			   $("#comments",Department.document).val(obj.comments);
		   });
	   });
	} else {
	   url = "/dept/add/ui";
	   Department.modal.open(url,data,function(){
		   $("#optionFlag",Department.document).val("0");
	   });
	}
	
}

/**
 * 编辑操作
 */

Department.eidt = function(flag) {
	var url = "";
	//添加
	if("0" == flag){
		url = "/dept/add"
	}
	if("1" == flag){
		url = "/dept/edit"
	}
	
	var data = $("#ack-add-form", Department.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Department.modal.close();
			//刷新当前页面
			Department.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Department.modal.close();
		}
		
	});
	
}
/**
 * 删除
 * 
 * */
Department.del = function(id){
	var url = "/dept/del/"+id;
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
				Department.modal.close();
				//刷新当前页面
				Department.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Department.modal.close();
			}
			
		});
	};
	var modal = Department.modal.modalTemplate(option);
	modal.modal('show');
	
}

/**
 * 绑定事件
 * */
Department.bind = function() {
	var ackModal = $("#ack-modal", Department.document);
	//修改
	$("#tab-body").on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		Department.eidtUI(id);
	});
	//删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	Department.del(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", Department.document);
		var flag = fp.val();
		Department.eidt(flag);
	});
}

Department.init = function (){
	Department.showList();
	Department.bind();
}


