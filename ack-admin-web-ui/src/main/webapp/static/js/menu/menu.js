/**
 * 菜单js
 */
var Menu = window.Menu || {};

Menu.modal = parent.AckSystem.modal;

Menu.document = window.parent.document || window.document;

Menu.config = function(){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Menu.getOneTr;
	return option;
}

Menu.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var item;
	var tr = $("<tr></tr>");
	tr.attr("id",data.id);
	//序号
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	//菜单名称
	var menuName = $("<td>"+data.menuName+"</td>");
	tr.append(menuName);
	//菜单url
	var url = $("<td>"+data.url+"</td>");
	tr.append(url);
	//菜单类型
	var menuType = $("<td>"+data.menuType+"</td>");
	tr.append(menuType);
	//菜单等级
	var menuLevel = $("<td>"+data.menuLevel+"</td>");
	tr.append(menuLevel);
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
	optionTd.append(AckTool.optionButton.simpleOption);
	tr.append(optionTd);
	return tr;
}

/**
 * 
 * 初始化显示
 * */
Menu.showList = function() {
	//主要option在AckTool.table里面有默认值
	var option = Menu.config();
	var data = {};
	var url = "/menu/page";
	AckTool.postReq(data,url,function(obj) {
		if (obj) {
			option.data = obj.result;
			var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Menu.list};
			$("#page").paginator(conf);
			AckTool.table.show(option); 
		}
	});
}

Menu.list = function(pageNo){
	var url = "/menu/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = Menu.config();
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
Menu.eidtUI = function(id) {
	
	var url = "";
	var data = {};
	data.reqData = {};
	if(id){
	   url = "/menu/edit/ui/"+id;
	   var menuDataUrl = "/menu/id/" + id;
	   //这里需要有个请求回显数据
	   Menu.modal.open(url,data,function(){
		   AckTool.postReq({},menuDataUrl,function(obj){
			   $("#optionFlag",Menu.document).val("1");
			   $("#id",Menu.document).val(obj.id);
			   $("#menuName",Menu.document).val(obj.menuName);
			   $("#url",Menu.document).val(obj.url);
			   var inputs = $("#menuType",Menu.document).find("input");
			   inputs.each(function(){
				   var val = $(this).val();
				   if(val == obj.menuType){
					   $(this).attr("checked","checked");
				   }
			   });
			   $("#menulevel",Menu.document).val(obj.menulevel);
			   $("#permission",Menu.document).val(obj.permission);
			   $("#domId",Menu.document).val(obj.domId);
			   $("#css",Menu.document).val(obj.css);
			   $("#parentId",Menu.document).val(obj.parentId);
			   $("#comments",Menu.document).val(obj.comments);
		   });
	   });
	} else {
	   url = "/menu/add/ui";
	   Menu.modal.open(url,data,function(){
		   $("#optionFlag",Menu.document).val("0");
	   });
	}
}

/**
 * 编辑操作
 */

Menu.eidt = function(flag) {
	var url = "";
	//添加
	if("0" == flag){
		url = "/menu/add"
	}
	if("1" == flag){
		url = "/menu/edit"
	}
	
	var data = $("#ack-add-form", Menu.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Menu.modal.close();
			//刷新当前页面
			Menu.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Menu.modal.close();
		}
		
	});
	
}
/**
 * 删除
 * 
 * */
Menu.del = function(id){
	var url = "/menu/del/"+id;
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
				Menu.modal.close();
				//刷新当前页面
				Menu.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Menu.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
	
}

/**
 * 绑定事件
 * */
Menu.bind = function() {
	var ackModal = $("#ack-modal", Menu.document);
	//修改
	$("#tab-body").on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		Menu.eidtUI(id);
	});
	//删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	Menu.del(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", Menu.document);
		var flag = fp.val();
		Menu.eidt(flag);
	});
}

Menu.init = function (){
	Menu.showList();
	Menu.bind();
}


