var Employee = window.Employee || {};

Employee.modal = parent.AckSystem.modal;

Employee.document = window.parent.document || window.document;

Employee.init = function() {
	// 展示列表
	Employee.showList();
	//绑定事件
	Employee.bind();
}

Employee.eidtUI = function (id){
	alert("修改操作 : " + id);
}

Employee.del = function (id){
	alert("删除操作 : " + id);
}
Employee.setRoleUI = function(id, self){
	var url = "/user/role2user/ui";
	var data = {};
	data.reqData = {};
	var callback = function(){
		var user = {};
		user.id = id;
		//查询用户角色
		AckTool.postReq(user,"/user/roles/list",function(roles){
			var role = {};
			var roleUrl = "/role/list";
			//注意这里form是在父页面,也就是说在index页面
			var form = $("#ack-modal-form",Employee.document);
			var input = $('<input type="hidden" value="'+id+'" name="id"/>');
			form.append(input);
			AckTool.postReq(role,roleUrl,function(obj){
				if(obj){
					//展示数据
					var div = $("<div class=''></div>");
					for(var i = 0; i < obj.length; i++){
						var role = obj[i];
						var checkBox = AckTool.optionButton.checkBox(role.roleName, "rid", role.id);
						for(var j = 0; j < roles.length; j++){
							var r = roles[j];
							if(role.id == r.id){
								checkBox.find("input").attr("checked","checked");
								break;
							}
						}
						div.append(checkBox);
					}
					form.append(div);
				}
			});
		});
	};
	Employee.modal.open(url, data, callback);
}

Employee.eidt = function (flag) {
	var url = "";
	var data = $("#ack-add-form", Employee.document).serialize();
	//添加
	if("0" == flag){
		url = "/user/add"
	}
	if("1" == flag){
		url = "/user/edit"
	}
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
	//设置角色
	table.on("click",".ack-simple-btn-user-role",function(){
		var id = $(this).parents("tr").attr("id");
		Employee.setRoleUI(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#roleOptionFlag", Employee.document);
		var flag = fp.val();
		Employee.eidt(flag);
	});
	//设置角色
	ackModal.on("click",".ack-modal-user-role-save-btn", function(){
		Employee.setRoles();
	});
}

Employee.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var fmt = "yyyy-MM-dd hh:mm:ss";
	var tr = $("<tr></tr>");
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	// 
	var surname = $("<td>" + data.surname+ "</td>");
	tr.append(surname);
	// 
	var name = $("<td>" + data.name+ "</td>");
	tr.append(name);
	// 
	var status = data.status;
	var str = "";
	if(status == 0){
		str = '<span class="label label-sm label-success">正常</span>';
		
	}else if(status == 1){
		str = '<span class="label label-sm label-inverse arrowed-in">禁用</span>';
	} else {
		str = '<span class="label label-sm label-inverse arrowed-in">error</span>';
	}
	var status = $("<td>" + str+ "</td>");
	tr.append(status);
	// 
	var time = AckTool.date(data.createTime, fmt);
	var createTime = $("<td>" + time+ "</td>");
	tr.append(status);
	
	var optionTd = $("<td></td>");
	optionTd.append(AckSystem.optionButton.defaultOption);
	tr.append(optionTd);
	return tr;
}

Employee.config = function (){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Employee.getOneTr;
	return option;
}

Employee.setRoles = function(){
   var url = "/user/role2user";
   var form = $("#ack-modal-form",Employee.document);
   var data = form.serialize();
   /*
   url = "/user/setrole";
	data = {};
	var checkBox = $(".ace-check");
	if(checkBox){
		var len = checkBox.length;
		var s = "";
		for(var i = 0; i < len; i++ ){
			var item = checkBox[i];
			var ch = item.checked;
			if(ch == "checked"){
				s = $(item).val() + ",";
			}
		}
		s = s.substring(0, s.length - 1);
		data.ids = s;
    }
    */
   AckTool.postReq(data, url, function(obj){
	   Employee.modal.close();
   });
}



Employee.showList = function() {
	var option = Employee.config();
	var data = {};
	var opt = {};
	opt.prefix = "user";
	 $('#ack-dynamic-table').DataTable( {
     	 "processing": true,
         "serverSide": true,
         "ajax": {
             "url" : "/user/table",
             "dataSrc" : "data",
             "type" : "POST"
         },
         "search" : {
        	"regex" : true 
         },
         "rowId" : "id",//将数据中的id绑定到tr上
         "columns": [
             { "data": "loginName" },
             { "data": "surname" },
             { "data": "name" },
             { "data": "status",
               "render" : function(data, type, full, meta){
            	   var enabled = '<span class="label label-sm label-success">正常</span>';
            	   var disabled = '<span class="label label-sm label-inverse arrowed-in">禁用</span>'; 
            	   if(data == 0){
            		   return enabled;
            	   }
            	   return disabled;
               }
             },
             { "data": "createTime",
               "render" : function(data, type, full, meta){
            	   var fmt = "yyyy-MM-dd hh:mm:ss";
            	   var time = AckTool.date(data, fmt);
            	   return time;
               }
             },
             {
             	"data" : "id",
             	"orderable" : false,
             	"render" : function(data, type, full, meta){
             		 var buttons = AckTool.optionButton.getTrAuthButtons(opt);
             		 var str = buttons[0].outerHTML;
             		 //var btn = '<button onclick="Employee.userRoleUI('+ data +', this);">设置角色</button>';
             		 return str;
                 }
             }
         ]
         
     } );
}

Employee.list = function(pageNo){
	
	var url = "/user/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = Employee.config();
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
