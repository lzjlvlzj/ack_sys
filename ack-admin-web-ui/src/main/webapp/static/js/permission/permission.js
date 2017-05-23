var Permission = window.Permission || {};

Permission.init = function() {
	Permission.showList();
	Permission.bind();
}

Permission.modal = parent.AckSystem.modal;

Permission.document = window.parent.document || window.document;

Permission.config = function() {
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Permission.getOneTr;
	return option;
}

Permission.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var item;
	var tr = $("<tr></tr>");
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	for (item in data) {
		var tdData = data[item];
		var flag = false;
		// id特殊处理
		if ("id" == item) {
			tr.attr("id", tdData);
			continue;
		}
		// 过滤
		if (excludeFields && excludeFields > 0) {
			for (var i = 0; i < excludeFields.length; i++) {
				var ex = option.excludeFileds[i];
				if (item == ex) {
					flag = true;
					break;
				}
			}
		}
		// 时间特殊处理
		if ("createTime" == item) {
			tdData = AckSystem.date(tdData, 'yyyy-MM-dd hh:mm:ss');
		}
		if(null == tdData || "menus" == item){
			continue;
		}
		// 空值处理
		if ("" === tdData || typeof (tdtData) === "undefined'") {
			tdData = "&nbsp;";
		}
		var td = $("<td></td>");
		td.append(tdData);
		tr.append(td);
	}
	var optionTd = $("<td></td>");
	optionTd.append(AckSystem.optionButton.simpleOption);
	tr.append(optionTd);
	return tr;
}

/**
 * 
 * 初始化显示
 */
Permission.showList = function() {
	// 主要option在AckSystem.table里面有默认值
	var option = Permission.config();
	var data = {};
	var url = "/permission/page";
	AckSystem.postReq(data, url, function(obj) {
		if (obj) {
			option.data = obj.result;
			var conf = {
				totalPage : obj.totalPage,
				pageNumSize : 5,
				callback : Permission.list
			};
			$("#page").paginator(conf);
			AckSystem.table.show(option);
		}
	});
}
/**
 * 查询显示
 */
Permission.list = function(pageNo) {
	var url = "/permission/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = Permission.config();
	AckSystem.postReq(data, url, function(obj) {
		if (obj) {
			option.data = obj.result;
			AckSystem.table.show(option);
		}
	});
}

Permission.eidtUI = function(id){
	var url = "";
	if(id){
	   url = "/permission/edit/ui/"+id;
	} else {
	   url = "/permission/add/ui";
	}
	Permission.modal.open(url);
}

/**
 * 编辑操作
 */

Permission.eidt = function(flag) {
	var url = "";
	//添加
	if("0" == flag){
		url = "/permission/add"
	}
	if("1" == flag){
		url = "/permission/edit"
	}
	
	var data = $("#ack-add-form", Permission.document).serialize();
	AckSystem.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Permission.modal.close();
			//刷新当前页面
			Permission.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Permission.modal.close();
		}
		
	});
	
}
/**
 * 删除
 * 
 * */
Permission.del = function(id){
	var url = "/permission/del/"+id;
	var modalUrl = "";
	var option = {fun : {}};
	option.header = "确认操作";
	option.headerCss = "ack-medal-header-yellow";
	option.content = "确认删除该条记录?";
	option.fun.selector = ".ack-modal-ok-btn";
	var data = {};
	//点击弹框"确定"的回调操作
	option.fun.callback = function(){
		AckSystem.postReq(data, url, function(obj) {
			if (obj == 1) {
				//关闭modal
				Permission.modal.close();
				//刷新当前页面
				Permission.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Permission.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
	
}

Permission.bind = function() {
	var ackModal = $("#ack-modal", Permission.document);
	//修改
	$("#tab-body").on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		Permission.eidtUI(id);
	});
	//删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	Permission.del(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", Permission.document);
		var flag = fp.val();
		Permission.eidt(flag);
	});
}