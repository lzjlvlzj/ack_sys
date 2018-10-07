var User = window.User || {};

User.modal = parent.AckSystem.modal;

User.document = window.parent.document || window.document;

User.init = function() {
	// 展示列表
	User.showList();
	//绑定事件
	User.bind();
}

User.eidtUI = function (id){
	alert("修改操作 : " + id);
}

User.del = function (id){
	alert("删除操作 : " + id);
}

User.setRole = function(id){
	User.userRoleUI(id);
}

/**
 * 绑定事件
 * */
User.bind = function() {
	var ackModal = $("#ack-modal", User.document);
	var table = $("#ack-dynamic-table");
	//修改
	table.on("click",".ack-simple-btn-edit",function(){
		var id = $(this).parents("tr").attr("id");
		User.eidtUI(id);
	});
	//删除
	table.on("click",".ack-simple-btn-del",function(){
    	var id = $(this).parents("tr").attr("id");
    	User.del(id);
	});
	//设置角色
	table.on("click",".ack-simple-btn-user-role",function(){
		var id = $(this).parents("tr").attr("id");
		User.setRole(id);
	});
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#roleOptionFlag", Role.document);
		var flag = fp.val();
		User.eidt(flag);
	});
	//设置角色
	ackModal.on("click",".ack-modal-user-role-save-btn", function(){
		User.setRoles();
	});
}


User.config = function (){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = User.getOneTr;
	return option;
}

User.setRoles = function(){
   var url = "/user/role2user";
   var form = $("#ack-modal-form",User.document);
   var data = form.serialize();
   AckTool.postReq(data, url, function(obj){
	   User.modal.close();
   });
}

User.userRoleUI = function(id, self){
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
			var form = $("#ack-modal-form",User.document);
			
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
	User.modal.open(url, data, callback);
}


User.showList = function() {
	var option = User.config();
	var data = {};
	var opt = {};
	opt.prefix = "user";
	 $('#ack-dynamic-table').DataTable( {
     	 "processing": true,
         "serverSide": true,
         "ajax": {
             "url" : "/user/table",
             "dataSrc" : "data",
             "type" : "POST",
             "complete" : function(){
            	 //自适应iframe
            	 AckTool.iFrameHeight();
             }
         },
         "search" : {
        	"regex" : true 
         },
         "rowId" : "id",//将数据中的id绑定到tr上
         "columns": [
             { "data": "loginName" },
             { "data": "surname" },
             { "data": "name" },
             { "data": "status" },
             { "data": "createTime" },
             {
             	"data" : "id",
             	"render" : function(data, type, full, meta){
             		 var buttons = AckTool.optionButton.getTrAuthButtons(opt);
             		 var str = buttons[0].outerHTML;
             		 //var btn = '<button onclick="User.userRoleUI('+ data +', this);">设置角色</button>';
             		 return str;
                 }
             }
         ]
         
     } );
}

User.list = function(pageNo){
	
	var url = "/user/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = User.config();
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
