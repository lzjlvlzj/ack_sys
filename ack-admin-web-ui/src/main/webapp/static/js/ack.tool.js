/**
 * ack工具js
 * 
 */
var AckTool = window.AckTool || {};


AckTool.document = window.parent.document || window.document;


AckTool.optionButton = {
		
	/**
	 * 状态信息按钮
	 * 
	 * */
	statusButton : function(msg){
		var btn = '<span class="label label-sm label-success">' + msg + '</span>';
		return btn;
	}, 
		
	/**
	 * checkbox
	 * */	
	checkBox : function(name, inputName, value){
		var n = name || "复选框";
		var itn = inputName || "form-field-checkbox";
		var val = value || "";
		var div = $("<div class='checkbox'></div>");
		var label = $("<label></label>");
		var input = $('<input name="' + itn + '" type="checkbox" class="ace ace-check" value=" '+ value + '">');
		var span = $('<span class="lbl">' + n + '</span>');
		label.append(input);
		label.append(span);
		div.append(label);
		return div;
	},
	/**
	 * 默认按钮
	 * */
	defaultButton : function (titleName, className, faIcon) {
		var cn = className || "";
		var fa = faIcon || "";
		var btn = "<button class='" + className + "' data-toggle='tooltip' title='" + titleName + "'><i class='ace-icon fa " + fa + " bigger-120'></i></button>"
	    return $(btn);
	},
		
	/**
	 * 添加
	 * */
	addButton : function(){
		
	},
	/**
	 * 修改
	 * */
	editButton : "<button class='btn btn-xs btn-info ack-simple-btn-edit' data-toggle='tooltip' title='修改操作'><i class='ace-icon fa fa-pencil bigger-120'></i></button>",
	
	//editButton : AckTool.optionButton.defaultButton('修改操作', 'ack-simple-btn-edit', 'fa-pencil')[0],
	
	/**
	 * 删除
	 * */
	deleteButton : '<button class="btn btn-xs btn-danger ack-simple-btn-del" data-toggle="tooltip" title="删除操作"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>',
	/**
	 * 给用户添加角色按钮
	 * 
	 * */
	authRole2UserButton : '<button class="btn btn-xs btn-danger ack-simple-btn-del" data-toggle="tooltip" title="授权角色"><i class="ace-icon fa fa-cog bigger-120"></i></button>',
	/**
	 * only update and delete
	 * 
	 */
	defaultOption : function() {
		var divsm = '<div class="hidden-sm hidden-xs action-buttons"><a class="green" href="#" data-toggle="tooltip" title="修改操作"><i class="ace-icon fa fa-pencil bigger-130"></i></a><a class="red" href="#" data-toggle="tooltip" title="删除操作"><i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
		var divmd = "<div class='hidden-md hidden-lg'>"
				+ "<div class='inline pos-rel'>"
				+ '<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto" aria-expanded="false"><i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button>'
				+ '<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'
				+ '<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit"><span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li>'
				+ '<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete"><span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li>'
				+ "</ul>" + "</div>" + "</div>";
		var data = divsm + divmd;
		return $(data);
	},
	/**
	 * 按钮容器
	 * 
	 * */
	buttonsContainer : $("<div class='hidden-sm hidden-xs btn-group'>"),
	/**
	 * update and delete
	 * 
	 */
	simpleOption : function() {
		var div = $("<div class='hidden-sm hidden-xs btn-group'>");
		var edit = $(AckTool.optionButton.editButton);
		var del = $(AckTool.optionButton.deleteButton);
		div.append(edit);
		div.append(del);
		return div;
	},
	/**TODO 这块其实应该放到后端
	 * 按钮拥有的权限字符(权限操作和按钮对应关系)
	 * */
	authButtonPermissionsConfig : function(prefix){
		var update = prefix + ":update";//修改
		var del = prefix + ":delete";//删除
		var userRole = "user:role";//用户添加角色
		var roleMenu = "role:menu";//用户添加角色
		var ptaskAllocate = "project:allocate"//
		var employeeComplete = "employee:complete";
		
		var array = new Array();
		array[update] = AckTool.optionButton.defaultButton('修改操作', 'btn btn-xs btn-info ack-simple-btn-edit', 'fa-pencil');
		array[del] = AckTool.optionButton.defaultButton('删除操作', 'btn btn-xs btn-danger ack-simple-btn-del', 'fa-trash-o');
		// 特殊处理 TODO 菜单按钮最好存数据库
		if("user" == prefix){
			array[userRole] = AckTool.optionButton.defaultButton('添加角色', 'btn btn-xs btn-info ack-simple-btn-user-role', 'fa-circle-o');	
		}
		if("role" == prefix){
			array[roleMenu] = AckTool.optionButton.defaultButton('添加菜单', 'btn btn-xs btn-info ack-simple-btn-role-menu', 'fa-circle-o');
		}
		/*if("ptask" == prefix){
			array[ptaskAllocate] = AckTool.optionButton.defaultButton('分配人员', 'btn btn-xs btn-info ack-simple-btn-ptask-user', 'fa-users');
		}*/
		if("project" == prefix){
			array[ptaskAllocate] = AckTool.optionButton.defaultButton('分配人员', 'btn btn-xs btn-info ack-simple-btn-ptask-user', 'fa-users');
		}
		if("employee" == prefix){
			array[employeeComplete] = AckTool.optionButton.defaultButton('结束项目', 'btn btn-xs btn-info ack-simple-btn-employee-complete', 'fa-coffee');
		}
		
		return array;
	},
	/**
	 * 每个列表按钮缓存
	 * */
	trAuthCacheBtns : [],
	/**
	 * 这个是每个tr生成的权限按钮(只有修改和删除)
	 * */
	getTrAuthButtons : function(option) {
		var prefix = option.prefix;
		var div = AckTool.optionButton.trAuthCacheBtns[prefix];
		if(div){
			return div;
		}
		div = $("<div class='hidden-sm hidden-xs btn-group'></div>");
		var array = AckTool.optionButton.authButtonPermissionsConfig(prefix);
		var permissons = option.permissions || parent.AckSystem.user.permissions();
		
		for(var i in array){
			var btn = array[i];
			for(var j = 0; j < permissons.length; j++){
				var item = permissons[j];
				if(i == item){
				   div.append(btn);
				}
			}
		}
		return div;
	}

}

/**
 * data change
 */
AckTool.date = function(time, format) {
	if(!time){
		return "";
	}
	var fmt = format || "yyyy-MM-dd";
	var d = new Date(time);
	var o = {
		"M+" : d.getMonth() + 1, // 月份
		"d+" : d.getDate(), // 日
		"h+" : d.getHours(), // 小时
		"m+" : d.getMinutes(), // 分
		"s+" : d.getSeconds(), // 秒
		"q+" : Math.floor((d.getMonth() + 3) / 3), // 季度
		"S" : d.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};
/**
 * ajax request
 */
AckTool.postReq = function(data, url, callback) {
	$.ajax({
		url : url,
		type : "post",
		data : data,
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(resObj) {
			callback(resObj);
		},
		complete : function(req, status){
			var ifm = AckTool.document.getElementById("mainFrame");
			var subWeb = AckTool.document.frames ? AckTool.document.frames["mainFrame"].document
					: ifm.contentDocument;
			if (ifm != null && subWeb != null) {
				var subH = subWeb.body.scrollHeight;
				if (ifm.height != subH) {
					ifm.height = subH
				}
			}
		}
	});
};

/**
 * show table
 */
AckTool.table = {
	/**
	 * 默认配置
	 */
	defaultOpt : function(cb) {
		var callback = cb || this.getOneTr;
		var tb = $("#tab-body");
		var option = {};
		option.tab = tb;// 表id
		option.excludeFileds = [];// 要排除的字段
		option.getOneTr = callback;// 生产一个tr的函数
		return option;
	},

	getOneTr : function(n, data, option) {
		var excludeFields = option.excludeFields;
		var item;
		var tr = $("<tr></tr>");
		var num = $("<td class='center'>" + n + "</td>");
		tr.append(num);
		for (item in data) {
			var tdData = data[item];
			var flag = false;
			if ("id" == item) {
				tr.attr("id", tdData);
				continue;
			}
			if (excludeFields && excludeFields > 0) {
				for (var i = 0; i < excludeFields.length; i++) {
					var ex = option.excludeFileds[i];
					if (item == ex) {
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				continue;
			}
			// 时间特殊处理
			if ("createTime" == item) {
				tdData = AckTool.date(tdData, 'yyyy-MM-dd hh:mm:ss');
			}
			// 空值处理
			if (!tdData) {
				continue;
			}
			var td = $("<td></td>");
			td.append(tdData);
			tr.append(td);
		}
		var optionTd = $("<td></td>");
		optionTd.append(AckTool.optionButton.defaultOption);
		tr.append(optionTd);
		return tr;
	},
	/**
	 * @param option
	 *            <p>
	 *            option.tab the id of table's body
	 *            <p>
	 *            option.excludeFields the exclude fields of your data
	 *            <p>
	 *            option.data your data
	 *            <p>
	 *            option getOneTr callback
	 */
	show : function(opt) {
		var option = opt || this.defaultOpt;
		// clear table
		option.tab.empty();
		// 获得一个tr的callback
		var getOneTr = option.getOneTr || this.getOneTr;
		// get the data
		var data = option.data;
		// console.log("length = " + data.length);
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			var tr = getOneTr(i + 1, item, option);
			tr.attr("role", "row");
			if (i % 2 == 0) {
				tr.attr("class", "odd");
			} else {
				tr.attr("class", "even");
			}
			option.tab.append(tr);
		}
	}
};