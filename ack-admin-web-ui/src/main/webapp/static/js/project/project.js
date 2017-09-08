/**
 * 项目操作
 */
var Project = window.Project || {};

Project.config = function() {
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = Project.getOneTr;
	return option;
}

Project.modal = parent.AckSystem.modal;
//解决js报错(top页面 window.parent 为null)
Project.document = window.parent.document || window.document;

/**
 * 获得一个tr
 */
Project.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var tr = $("<tr></tr>");
	var fmt = "yyyy-MM-dd hh:mm:ss";
	tr.attr("id", data.id);
	//序号
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	//项目名称
	var projectName = $("<td class='center'>" + data.name + "</td>");
	tr.append(projectName);
	//部门名称
	var departmentName = $("<td class='center'>" + data.departmentName
			+ "</td>");
	tr.append(departmentName);
	//项目负责人
	var managerName = $("<td class='center menu-ids'>" + data.managerName
			+ "</td>");
	tr.append(managerName);
	//项目状态
	var sataus = (data.status == 0 ? "打开" : "关闭");
	var projectStatus = $("<td class='center menu-ids'>" + sataus + "</td>");
	tr.append(projectStatus);
	//开始时间
	var startTime = $("<td class='center'>" + AckTool.date(data.startTime, fmt)
			+ "</td>");
	tr.append(startTime);
	//结束时间
	var endTime = $("<td class='center'>" + AckTool.date(data.endTime, fmt)
			+ "</td>");
	tr.append(endTime);
	//评论
	var remark = $("<td class='center'>" + data.remark + "</td>");
	tr.append(remark);
	//操作
	//获得操作按钮
	var optionTd = $("<td></td>");
	var d = option.data;
	var opt = {};
	opt.data = d;
	opt.prefix = "project";
	var buttons = AckTool.optionButton.getTrAuthButtons(opt);
	optionTd.append(buttons);
	tr.append(optionTd);
	return tr;
}

Project.list = function(pageNo) {
	var url = "/project/page";
	var data = {};
	data.currentPage = pageNo;
	var option = Project.config();
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			option.data = obj.result;
			AckTool.table.show(option);
		}
	});
}

/**
 * 显示Project列表
 * 
 */
Project.showList = function() {
	var option = Project.config();
	var data = {};
	var url = "/project/page";
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			option.data = obj.result;
			var conf = {
				totalPage : obj.totalPage,
				pageNumSize : 5,
				callback : Project.list
			};
			$("#page").paginator(conf);
			AckTool.table.show(option);
		}
	});
}

Project.eidt = function(flag) {
	var url = "";
	//添加
	if ("0" == flag) {
		url = "/project/add"
	}
	if ("1" == flag) {
		url = "/project/edit"
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

Project.showMananger = function() {
    var url = "/user/managers";
    var data = {};
    var select = $("#managerName", Project.document).empty();
    AckTool.postReq(data, url, function (obj){
    	if(obj){
    		var len = obj.length;
			for (var i = 0; i < len; i++) {
				var item = obj[i];
				var option = $("<option></option>");
				var managerName = item.surname + item.name;
				option.attr("id", item.id);
				option.html(managerName);
				select.append(option);
			}
    	}
    });
}

Project.showDepartment = function() {
	var deptUrl = "/dept/all";
	var deptData = {};
	var select = $("#departmentName", Project.document).empty();
	AckTool.postReq(deptData, deptUrl, function(obj) {
		if (obj) {
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				var item = obj[i];
				var option = $("<option></option>");
				var deptName = item.departmentName;
				option.attr("id", item.id);
				option.html(deptName);
				select.append(option);
			}
		}
	});
}

/**
 * 页面
 */

Project.eidtUI = function(id) {
	//alert(id);
	var url = "";
	var data = {};
	data.reqData = {};
	if (id) {
		url = "/project/edit/ui/" + id;
		var ProjectDataUrl = "/project/id/" + id;
		//这里需要有个请求回显数据
		Project.modal.open(url, data, function() {
			AckTool.postReq({}, ProjectDataUrl, function(obj) {
				$("#optionFlag", Project.document).val("1");
				$("#id", Project.document).val(obj.id);
				$("#ProjectName", Project.document).val(obj.ProjectName);
				$("#menuIds", Project.document).val(obj.menuIds);
				$("#comments", Project.document).val(obj.comments);
			});
		});
	} else {
		url = "/project/add/ui";
		Project.modal.open(url, data, function() {
			$("#optionFlag", Project.document).val("0");
			// 加载部门
			//Project.showDepartment();
            // 加载项目经理
			Project.showMananger();
		});
	}

}
/**
 * 新建
 */

Project.eidt = function(flag) {
	var url = "";
	//添加
	if ("0" == flag) {
		url = "/project/add"
	}
	if ("1" == flag) {
		url = "/project/edit"
	}

	var data = $("#ack-add-form", Project.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			//关闭modal
			Project.modal.close();
			//刷新当前页面
			Project.showList();
		} else {
			alert("系统错误");
			//关闭modal
			Project.modal.close();
		}

	});

}

Project.del = function(id) {
	var url = "/project/del/" + id;
	var modalUrl = "";
	var option = {
		fun : {}
	};
	option.header = "确认操作";
	option.headerCss = "ack-medal-header-yellow";
	option.content = "确认删除该条记录?";
	option.fun.selector = ".ack-modal-ok-btn";
	var data = {};
	option.fun.callback = function() {
		AckTool.postReq(data, url, function(obj) {
			if (obj == 1) {
				//关闭modal
				Project.modal.close();
				//刷新当前页面
				Project.showList();
			} else {
				alert("系统错误");
				//关闭modal
				Project.modal.close();
			}

		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');

}

/**
 * 绑定事件
 * */
Project.bind = function() {
	var ackModal = $("#ack-modal", Project.document);
	//修改
	$("#simple-table").on("click", ".ack-simple-btn-edit", function() {
		var id = $(this).parents("tr").attr("id");
		Project.eidtUI(id);
	});
	//删除
	$("#simple-table").on("click", ".ack-simple-btn-del", function() {
		var id = $(this).parents("tr").attr("id");
		Project.del(id);
	});
	//项目菜单
	$("#simple-table").on("click", ".ack-simple-btn-Project-menu", function() {
		var td = $(this).parents("tr").find("td").eq(2);
		var menuIds = td.text();
		var id = $(this).parents("tr").attr("id");
		//ztree();
		Project.menu2ProjectUI(menuIds, id);
	});
	//项目菜单保存
	ackModal.on("click", ".ack-modal-Project-menu-save-btn", function() {
		Project.menu2Project();
	});
	//保存
	ackModal.on("click", ".ack-modal-save-btn", function() {
		var fp = $("#optionFlag", Project.document);
		var flag = fp.val();
		Project.eidt(flag);
	});
}

/**
 * 
 * Project 初始化
 */
Project.init = function() {
	//绑定事件
	Project.bind();
	//展示数据
	Project.showList();
}
