var Employee = window.Employee || {};

Employee.modal = parent.AckSystem.modal;

Employee.document = window.parent.document || window.document;

Employee.init = function() {
	// 展示列表
	Employee.showList();
	//绑定事件
	Employee.bind();
}
/**
 * 结束项目
 * */
Employee.complete = function(id, btn){
	var url = "/employee/complete/"+id;
	var modalUrl = "";
	var option = {fun : {}};
	option.header = "确认操作";
	option.headerCss = "ack-medal-header-yellow";
	option.content = "确认结束该任务吗?(表示工作已完成)结束后其他参与此任务的人员将不能再操作.";
	option.fun.selector = ".ack-modal-ok-btn";
	var data = {};
	option.fun.callback = function(){
		AckTool.postReq(data, url, function(obj) {
			if (obj == 1) {
				//关闭modal
				Employee.modal.close();
				//刷新当前页面
				Employee.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Employee.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
}

Employee.eidtUI = function (id){
	var url = "";
	var data = {};
	data.reqData = {};
	if (id) {
		url = "/employee/edit/ui/" + id;
		var projectDataUrl = "/employee/id/" + id;
		//这里需要有个请求回显数据
		Employee.modal.open(url, data, function() {
			AckTool.postReq({}, projectDataUrl, function(obj) {
				$("#optionFlag", Employee.document).val("1");
				$("#id", Employee.document).val(obj.id);
				$("#task", Employee.document).val(obj.task);
				//load project
				var pid = obj.projectId;
				Employee.showProject(pid);
				//load priority
				var priority = obj.priority;
				var options = $("#priority", Employee.document).find("option");
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
		url = "/employee/add/ui";
		Employee.modal.open(url, data, function() {
			$("#optionFlag", Employee.document).val("0");
			// 加载当前部门的所有项目
			//Employee.showProject();
		});
	}
}

Employee.eidt = function(flag) {
	var url = "";
	// 添加
	if ("0" == flag) {
		url = "/employee/add";
	}
	if ("1" == flag) {
		url = "/employee/edit";
	}
	var data = $("#ack-add-form", Employee.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			// 关闭modal
			Employee.modal.close();
			// 刷新当前页面
			Employee.showList();
		} else {
			alert("系统错误");
			// 关闭modal
			Employee.modal.close();
		}
	});

}


Employee.del = function (id){
	var url = "/employee/del/"+id;
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
				Employee.modal.close();
				//刷新当前页面
				Employee.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Employee.modal.close();
			}
			
		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');
}



/**
 * 绑定事件
 * */
Employee.bind = function() {
	var ackModal = $("#ack-modal", Employee.document);
	var table = $("#ack-dynamic-table");
	//修改
	table.on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		Employee.eidtUI(id);
	});
	//删除
	table.on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	Employee.del(id);
	});
	//项目任务分配
	table.on("click",".ack-simple-btn-employee-complete",function(){
		var id = $(this).parents("tr").attr("id");
		Employee.complete(id, $(this));
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", Employee.document);
		var flag = fp.val();
		Employee.eidt(flag);
	});
	//设置项目任务
	ackModal.on("click",".ack-modal-task-cooperator-btn", function(){
		Employee.setEmployeesCooperator();
	});
}



Employee.config = function (){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	return option;
}

Employee.showList = function() {
	var option = Employee.config();
	var data = {};
	var opt = {};
	opt.prefix = "employee";
	//如果已经有datatable那么点击刷新页面
	if($.fn.dataTable.isDataTable( '#ack-dynamic-table' )){
	    var table = $('#ack-dynamic-table').DataTable();
	    table.draw();
	} else {
	    $('#ack-dynamic-table').DataTable( {
     	 "processing": true,
         "serverSide": true,
         "ajax": {
             "url" : "/employee/table",
             "dataSrc" : "data",
             "type" : "POST"
         },
         "search" : {
        	"regex" : true 
         },
         "order": [[ 4, "desc" ]], // default sort column
         "rowId" : "id",//将数据中的id绑定到tr上
         "columns": [
             { "data": "projectName"},
             { "data": "task"},
             { "data": "status",
               "render" : function(data, type, full, meta){
            	   var span = '<span class="status-span" style="display:none">' + data + '</span>';
              	   var enabled = AckTool.optionButton.statusButton("打开") + span;
              	   var disabled = AckTool.optionButton.statusButton("关闭") + span; 
              	   if(data == 0){
              		   return enabled;
              	   }
              	   return disabled;
                } 
             },
             { "data": "priority"},
             { "data": "startTime"},
             { "data": "endTime" },
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
