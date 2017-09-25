var EmployeeJobList = window.EmployeeJobList || {};

EmployeeJobList.modal = parent.AckSystem.modal;

EmployeeJobList.document = window.parent.document || window.document;

EmployeeJobList.init = function() {
	// 展示列表
	EmployeeJobList.showList();
	//绑定事件
	EmployeeJobList.bind();
}

EmployeeJobList.showProject = function(pid){
	var deptId = $("#index-user-dept-id", EmployeeJobList.document).val();
	var url = "/project/dept/"+deptId;
	var data = {};
	var select = $("#projectId", EmployeeJobList.document);
	AckTool.postReq(data, url, function(obj){
		if(obj){
			var len = obj.length;
			for(var i = 0; i < len; i++){
				var item = obj[i];
				var id = item.id;
				var projectName = item.name;
				var option = $("<option value='"+ id +"'>" + projectName + "</option>");
				if(id == pid){
					option.attr("selected", "selected");
				}
				select.append(option);
			}
		}
	});
}

EmployeeJobList.setEmployeeJobListsCooperator = function(){
   var url = "/ptask/cooperator/add";
   var data = {};
   data.projectTaskId = $("#projectTaskId", EmployeeJobList.document).val();
   data.userIds = AckMultipleListBox.getResult(EmployeeJobList.document);
   AckTool.postReq(data, url, function(obj){
	    // 关闭modal
		EmployeeJobList.modal.close();
   });
}


EmployeeJobList.showExistProjectCooperator = function(id){
	var url = "/ptask/cooperators";
	var data = {};
	data.id = id;
	data.flag = 1;
	var select = AckMultipleListBox.target.select(EmployeeJobList.document);
	select.empty();
	AckTool.postReq(data, url, function(obj) {
		if(obj){
			var len = obj.length;
			for(var i = 0; i < len; i++){
				var item = obj[i];
				var name = item.surname + item.name;
				var option = $("<option value='"+ item.id+"'>" + name + "</option>");
				select.append(option);
			}
		}
	});
}


EmployeeJobList.showAllProjectCooperator = function(id){
	var url = "/ptask/cooperators";
	var data = {};
	data.id = id;
	data.flag = 0;
	var select = AckMultipleListBox.src.select(EmployeeJobList.document);
	select.empty();
	AckTool.postReq(data, url, function(obj) {
		if(obj){
			var len = obj.length;
			for(var i = 0; i < len; i++){
				var item = obj[i];
				var name = item.surname + item.name;
				var option = $("<option value='"+ item.id+"'>" + name + "</option>");
				select.append(option);
			}
		}
	});
}

EmployeeJobList.eidtUI = function (id){
	var url = "";
	var data = {};
	data.reqData = {};
	if (id) {
		url = "/ptask/edit/ui/" + id;
		var projectDataUrl = "/ptask/id/" + id;
		//这里需要有个请求回显数据
		EmployeeJobList.modal.open(url, data, function() {
			AckTool.postReq({}, projectDataUrl, function(obj) {
				$("#optionFlag", EmployeeJobList.document).val("1");
				$("#id", EmployeeJobList.document).val(obj.id);
				$("#task", EmployeeJobList.document).val(obj.task);
				//load project
				var pid = obj.projectId;
				EmployeeJobList.showProject(pid);
				//load priority
				var priority = obj.priority;
				var options = $("#priority", EmployeeJobList.document).find("option");
				var len = options.length;
				for(var i = 0; i < len; i++){
					var opt = $(options[i]);
					var val = opt.attr("value");
					if(priority == val){
						opt.attr("selected", "selected");
						break ;
					}
				}
			});
		});
	} else {
		url = "/ptask/add/ui";
		EmployeeJobList.modal.open(url, data, function() {
			$("#optionFlag", EmployeeJobList.document).val("0");
			// 加载当前部门的所有项目
			EmployeeJobList.showProject();
		});
	}
}

EmployeeJobList.eidt = function(flag) {
	var url = "";
	// 添加
	if ("0" == flag) {
		url = "/ptask/add";
	}
	if ("1" == flag) {
		url = "/ptask/edit";
	}
	var data = $("#ack-add-form", EmployeeJobList.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			// 关闭modal
			EmployeeJobList.modal.close();
			// 刷新当前页面
			EmployeeJobList.showList();
		} else {
			alert("系统错误");
			// 关闭modal
			EmployeeJobList.modal.close();
		}
	});

}


EmployeeJobList.del = function (id){
	var url = "/ptask/del/"+id;
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
				EmployeeJobList.modal.close();
				//刷新当前页面
				EmployeeJobList.showList();
			} else {
				alert("系统错误");
				//关闭modal
				EmployeeJobList.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
}

EmployeeJobList.setCooperatorUI = function(id, btn){
	//如果状态为 1说明该项目任务已经完成,不需要再分配人员.
	var span = btn.parents("tr").find("span[class='status-span']");
	var status = span.text();
	if(status == 1){
		var option = {fun : {}};
		option.header = "消息提示";
		option.headerCss = "ack-medal-header-default";
		option.content = "该项目任务已经完结，不能再分配人员.";
		option.fun.selector = ".ack-modal-ok-btn";
		var modal = this.modal.modalTemplate(option);
		modal.modal('show');
	} else {
		var data = {};
		var url = "/ptask/cooperators/" + id;
		EmployeeJobList.modal.open(url, data, function() {
			$("#projectTaskId", EmployeeJobList.document).val(id);
			// 加载当前项目合作的所有人员
			EmployeeJobList.showAllProjectCooperator(id);
			// 加载已有合作人员
			EmployeeJobList.showExistProjectCooperator(id);
			// 初始化事件
			AckMultipleListBox.init(EmployeeJobList.document);
		});
	}
	
}

/**
 * 绑定事件
 * */
EmployeeJobList.bind = function() {
	var ackModal = $("#ack-modal", EmployeeJobList.document);
	var table = $("#ack-dynamic-table");
	//修改
	table.on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		EmployeeJobList.eidtUI(id);
	});
	//删除
	table.on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	EmployeeJobList.del(id);
	});
	//项目任务分配
	table.on("click",".ack-simple-btn-ptask-user",function(){
		var id = $(this).parents("tr").attr("id");
		EmployeeJobList.setCooperatorUI(id, $(this));
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", EmployeeJobList.document);
		var flag = fp.val();
		EmployeeJobList.eidt(flag);
	});
	//设置项目任务
	ackModal.on("click",".ack-modal-task-cooperator-btn", function(){
		EmployeeJobList.setEmployeeJobListsCooperator();
	});
}



EmployeeJobList.config = function (){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = EmployeeJobList.getOneTr;
	return option;
}

EmployeeJobList.showList = function() {
	var option = EmployeeJobList.config();
	var data = {};
	var opt = {};
	opt.prefix = "ptask";
	//如果已经有datatable那么点击刷新页面
	if($.fn.dataTable.isDataTable( '#ack-dynamic-table' )){
	    var table = $('#ack-dynamic-table').DataTable();
	    table.draw();
	} else {
	    $('#ack-dynamic-table').DataTable( {
     	 "processing": true,
         "serverSide": true,
         "ajax": {
             "url" : "/ptask/table",
             "dataSrc" : "data",
             "type" : "POST"
         },
         "search" : {
        	"regex" : true 
         },
         "order": [[ 4, "desc" ]], // default sort column
         "rowId" : "id",//将数据中的id绑定到tr上
         "columns": [
             { "data": "realName"},
             { "data": "projectName"},
             { "data": "content"},
             { "data": "createTime"
             },
             {
             	"data" : "id",
             	"render" : function(data, type, full, meta){
             		 var buttons = AckTool.optionButton.getTrAuthButtons(opt);
             		 var str = buttons[0].outerHTML;
             		 return str;
                 },
                "order" : false
             }
         ]
         
        });
	}    
}

EmployeeJobList.list = function(pageNo){
	
	var url = "/user/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = EmployeeJobList.config();
	$.ajax({
		url:url,
		type:"post", 
		data:data,
		dataType:'json',
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		success : function(obj) {
			if( obj ){
				option.data = obj.result;
				AckSystem.table.show(option);
			}
		}
	});
};
