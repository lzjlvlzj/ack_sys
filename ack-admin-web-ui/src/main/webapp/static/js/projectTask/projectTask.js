var ProjectTask = window.ProjectTask || {};

ProjectTask.modal = parent.AckSystem.modal;

ProjectTask.document = window.parent.document || window.document;

ProjectTask.init = function() {
	// 展示列表
	ProjectTask.showList();
	//绑定事件
	ProjectTask.bind();
}

ProjectTask.showProject = function(){
	var deptId = $("#index-user-dept-id", ProjectTask.document).val();
	var url = "/project/dept/"+deptId;
	var data = {};
	var select = $("#projectId", ProjectTask.document);
	AckTool.postReq(data, url, function(obj){
		if(obj){
			var len = obj.length;
			for(var i = 0; i < len; i++){
				var item = obj[i];
				var id = item.id;
				var projectName = item.name;
				var option = $("<option value='"+ id +"'>" + projectName + "</option>");
				select.append(option);
			}
		}
	});
}

ProjectTask.eidtUI = function (id){
	var url = "";
	var data = {};
	data.reqData = {};
	if (id) {
		url = "/ptask/edit/ui/" + id;
		var projectDataUrl = "/ptask/id/" + id;
		//这里需要有个请求回显数据
		Project.modal.open(url, data, function() {
			AckTool.postReq({}, projectDataUrl, function(obj) {
				$("#optionFlag", ProjectTask.document).val("1");
				$("#id", ProjectTask.document).val(obj.id);
				$("#name", ProjectTask.document).val(obj.name);
				$("#departmentId", ProjectTask.document).val(obj.departmentId);
				$("#departmentName", ProjectTask.document).val(obj.departmentName);
				$("#deptName", ProjectTask.document).val(obj.departmentName);
				$("#remark", ProjectTask.document).val(obj.remark);
				Project.showMananger(obj.managerId);
			});
		});
	} else {
		url = "/ptask/add/ui";
		ProjectTask.modal.open(url, data, function() {
			$("#optionFlag", ProjectTask.document).val("0");
			// 加载当前部门的所有项目
			ProjectTask.showProject();
		});
	}
}

ProjectTask.del = function (id){
	alert("删除操作 : " + id);
}

ProjectTask.setRole = function(id){
	alert("设置角色操作 : " + id);
}

/**
 * 绑定事件
 * */
ProjectTask.bind = function() {
	var ackModal = $("#ack-modal", ProjectTask.document);
	var table = $("#ack-dynamic-table");
	//修改
	table.on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		ProjectTask.eidtUI(id);
	});
	//删除
	table.on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	ProjectTask.del(id);
	});
	//设置角色
	table.on("click",".ack-simple-btn-user-role",function(){
		var id = $(this).parents("tr").attr("id");
		ProjectTask.setRole(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#roleOptionFlag", Role.document);
		var flag = fp.val();
		ProjectTask.eidt(flag);
	});
	//设置角色
	ackModal.on("click",".ack-modal-user-role-save-btn", function(){
		ProjectTask.setRoles();
	});
}



ProjectTask.config = function (){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = ProjectTask.getOneTr;
	return option;
}

ProjectTask.showList = function() {
	var option = ProjectTask.config();
	var data = {};
	var opt = {};
	opt.prefix = "ptask";
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
         "rowId" : "id",//将数据中的id绑定到tr上
         "columns": [
             { "data": "projectName"},
             { "data": "task" },
             { "data": "workerId" },
             { "data": "status" },
             { "data": "priority" },
             { "data": "startTime" },
             { "data": "endTime" },
             {
             	"data" : "id",
             	"render" : function(data, type, full, meta){
             		 return "";
                 },
                 "order" : false
             }
         ]
         
     } );
}

ProjectTask.list = function(pageNo){
	
	var url = "/user/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = ProjectTask.config();
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
