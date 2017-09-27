/**
 * 角色操作
 */
var Role = window.Role || {};

Role.config = function() {
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Role.getOneTr;
	return option;
}

Role.modal = parent.AckSystem.modal;
//解决js报错(top页面 window.parent 为null)
Role.document = window.parent.document || window.document;

/**
 * 获得一个tr
 */
Role.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var tr = $("<tr></tr>");
	tr.attr("id",data.id);
	//序号
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	//角色名称
	var roleName = $("<td class='center'>" + data.roleName + "</td>");
	tr.append(roleName);
	//角色简称
	var abbreviation = $("<td class='center'>" + data.abbreviation + "</td>");
	tr.append(abbreviation);
	//角色权重
	var weight = $("<td class='center'>" + data.weight + "</td>");
	tr.append(weight);
	//菜单id
	var ids = data.menuIds;
	var menuIds = $("<td class='center menu-ids'>" + ids + "</td>");
	tr.append(menuIds);
	//评论
	var comments = $("<td class='center'>" + data.comments + "</td>");
	tr.append(comments);
	//时间
	var createTime = $("<td class='center'>" + data.createTime + "</td>");
	tr.append(createTime);
	//操作
	//获得操作按钮
	var optionTd = $("<td></td>");
	var data = option.data;
	var opt = {};
	opt.data = data;
	opt.prefix = "role";
	var buttons = AckTool.optionButton.getTrAuthButtons(opt);
	optionTd.append(buttons);
	
	var menuIdInput = $("<input type='hidden' value='" + ids + "'></input>");
	optionTd.append(menuIdInput);
	
	tr.append(optionTd);
	
	return tr;
}
/**
 * 显示role列表
 * 
 */
Role.showList = function() {
	var option = Role.config();
	var data = {};
	var url = "/role/list";
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			option.data = obj;
			AckTool.table.show(option);
		}
	});
}

/**
 * 页面
 */

Role.eidtUI = function(id) {
	//alert(id);
	var url = "";
	var data = {};
	data.reqData = {};
	if(id){
	   url = "/role/edit/ui/"+id;
	   var roleDataUrl = "/role/id/" + id;
	   //这里需要有个请求回显数据
	   Role.modal.open(url,data,function(){
		   AckTool.postReq({},roleDataUrl,function(obj){
			   $("#optionFlag",Role.document).val("1");
			   $("#id",Role.document).val(obj.id);
			   $("#roleName",Role.document).val(obj.roleName);
			   $("#abbreviation",Role.document).val(obj.abbreviation);
			   $("#weight",Role.document).val(obj.weight);
			   $("#menuIds",Role.document).val(obj.menuIds);
			   $("#comments",Role.document).val(obj.comments);
			   var inputs = $("#viewStatus",Role.document).find("input");
			   inputs.each(function(){
				   var val = $(this).val();
				   if(val == obj.viewStatus){
					   $(this).attr("checked","checked");
				   }
			   });
		   });
	   });
	} else {
	   url = "/role/add/ui";
	   Role.modal.open(url,data,function(){
		   $("#optionFlag",Role.document).val("0");
	   });
	}
	
}
/**
 * 新建
 */

Role.eidt = function(flag) {
	var url = "";
	//添加
	if("0" == flag){
		url = "/role/add"
	}
	if("1" == flag){
		url = "/role/edit"
	}
	
	var data = $("#ack-add-form", Role.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Role.modal.close();
			//刷新当前页面
			Role.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Role.modal.close();
		}
		
	});
	
}

Role.del = function(id){
	var url = "/role/del/"+id;
	var modalUrl = "";
	var option = {fun : {}};
	option.header = "确认操作";
	option.headerCss = "ack-medal-header-yellow";
	option.content = "确认删除该条记录?";
	option.fun.selector = ".ack-modal-ok-btn";
	var data = {};
	option.fun.callback = function(){
		AckTool.postReq(data, url, function(obj) {
			if (obj == 1) {
				//关闭modal
				Role.modal.close();
				//刷新当前页面
				Role.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Role.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
	
}

Role.menu2roleUI = function(menuIds, id){
	var url = "/role/menu2role/ui";
	var data = {};
	data.reqData = {};
	Role.modal.open(url,data,function(){
		$("#ack-role-id", Role.document).val(id);
		//加载树形菜单
		parent.zTreeInit(menuIds);
	});
	
}

Role.menu2role = function(){
	var url = "/role/menu2role";
	var data = {};
	var nodes = parent.getCheckedNodes();
	var ids = "";
	if(nodes){
		for (var i = 0; i < nodes.length; i++) {
			var node = nodes[i];
			ids = ids + node.id + ",";
		}
	}
	ids = ids.substring(0,ids.length - 1);
	data.reqData = {};
	data.menuIds = ids;
	data.id = $("#ack-role-id", Role.document).val();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Role.modal.close();
			//刷新当前页面
			Role.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Role.modal.close();
		}
		
	});
	
	
}
/**
 * 绑定事件
 * */
Role.bind = function() {
	var ackModal = $("#ack-modal", Role.document);
	//修改
	$("#simple-table").on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		Role.eidtUI(id);
	});
	//删除
    $("#simple-table").on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	Role.del(id);
	});
    //角色菜单
    $("#simple-table").on("click",".ack-simple-btn-role-menu",function(){
    	var input = $(this).parents("td").find("input");
    	var menuIds = input.val();
    	var id = $(this).parents("tr").attr("id");
    	//ztree();
    	Role.menu2roleUI(menuIds, id);
    });
	//角色菜单保存
	ackModal.on("click",".ack-modal-role-menu-save-btn", function(){
		Role.menu2role();
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", Role.document);
		var flag = fp.val();
		Role.eidt(flag);
	});
}

/**
 * 
 * role 初始化
 */
Role.init = function() {
	//绑定事件
	Role.bind();
	//展示数据
	Role.showList();
}
