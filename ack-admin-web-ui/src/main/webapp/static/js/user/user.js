var User = window.User || {};

User.modal = parent.AckSystem.modal;

User.document = window.parent.document || window.document;

User.init = function() {
	// 展示列表
	User.showList();
	//绑定事件
	User.bind();
};

User.eidtUI = function (id){
    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/user/edit/ui/"+id;
        var UserDataUrl = "/user/id/" + id;
        //这里需要有个请求回显数据
        User.modal.open(url,data,function(){
            AckTool.postReq({},UserDataUrl,function(obj){
                $("#optionFlag",User.document).val("1");
                $("#id",User.document).val(obj.id);
                $("#loginName",User.document).val(obj.loginName);
                $("#surname",User.document).val(obj.surname);
                $("#name",User.document).val(obj.name);
                $("#address",User.document).val(obj.address);
                $("#phone",User.document).val(obj.phone);
            });
        });
    } else {
        url = "/user/add/ui";
        User.modal.open(url,data,function(){
            $("#optionFlag",User.document).val("0");
        });
    }
};


/**
 * 编辑操作
 */

User.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/user/add"
    }
    if("1" == flag){
        url = "/user/edit"
    }

    var data = $("#ack-add-form", User.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            User.modal.close();
            // 刷新当前页面
            //User.showList();
			$("#user-mem", User.document).click();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", User.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            User.modal.close();
        }

    });

};

User.del = function (id){
	alert("删除操作 : " + id);
};

User.setRole = function(id){
	User.userRoleUI(id);
};

User.checkLoginName = function(val){
	var url = "/user/findByLoginName";
	var data = {};
	data.loginName = val;
	var msgDiv = $("#msgDiv", User.document);
    msgDiv.empty();
    msgDiv.hide();
    AckTool.postReq(data,url,function(obj){
        if(obj == 1){
            msgDiv.html("用户已存在");
            msgDiv.show();
		}
    });
};
/**
 * 重置密码
 * @param id
 */
User.resetPassword = function(id){
	if(!id){
		return ;
	}
    var url = "/user/password/reset";
    var data = {};
    data.id = id;
    var option = {fun : {}};
    option.header = "重置结果";
    //
    option.content = "重置成功";
    //option.fun.selector = ".ack-modal-ok-btn";
    //点击弹框"确定"的回调操作
    //option.fun.callback = function(){};
    AckTool.postReq(data, url, function(obj) {
        if (obj == 1) {
            option.content = "重置成功";
            option.headerCss = "ack-medal-header-green";
        } else {
            option.content = "重置失败,请联系管理员。";
            option.headerCss = "ack-medal-header-red";
        }
        var modal = User.modal.modalTemplate(option);
        modal.modal('show');
    });

};

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
    table.on("click",".ack-modal-user-reset-password-btn", function(){
        var id = $(this).parents("tr").attr("id");
        User.resetPassword(id);
    });
	//设置角色
	table.on("click",".ack-simple-btn-user-role",function(){
		var id = $(this).parents("tr").attr("id");
		User.setRole(id);
	});
	//保存用户
	ackModal.on("click",".ack-modal-save-btn", function(){
		var fp = $("#optionFlag", User.document);
		var flag = fp.val();
		User.eidt(flag);
	});
	//检查用户名
	ackModal.on("input","#loginName", function(){
		var val = $(this).val();
		User.checkLoginName(val);
	});
	//设置角色
	ackModal.on("click",".ack-modal-user-role-save-btn", function(){
		User.setRoles();
	});


};


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
             { "data": "phone" },
             { "data": "status" },
             { "data": "createTime" , render : function(data,type,full,meta){
                     var tm = AckTool.date(data, "yyyy-MM-dd hh:mm:ss");
                     return tm;
                }
             },
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
};

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
