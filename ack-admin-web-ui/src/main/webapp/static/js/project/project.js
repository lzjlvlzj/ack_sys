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
// 解决js报错(top页面 window.parent 为null)
Project.document = window.parent.document || window.document;

/**
 * 获得一个tr
 */
Project.getOneTr = function(n, data, option) {
	var excludeFields = option.excludeFields;
	var tr = $("<tr></tr>");
	var fmt = "yyyy-MM-dd hh:mm:ss";
	tr.attr("id", data.id);
	// 序号
	var num = $("<td class='center'>" + n + "</td>");
	tr.append(num);
	// 项目名称
	var projectName = $("<td class='center'>" + data.name + "</td>");
	tr.append(projectName);
	// 部门名称
	var departmentName = $("<td class='center'>" + data.departmentName
			+ "</td>");
	tr.append(departmentName);
	// 项目负责人
	var managerName = $("<td class='center menu-ids'>" + data.managerName
			+ "</td>");
	tr.append(managerName);
	// 项目状态
	var sataus = (data.status == 0 ? "打开" : "关闭");
	var projectStatus = $("<td class='center menu-ids'>" + sataus + "</td>");
	tr.append(projectStatus);
	// 项目类型
	var type = (data.type == 0 ? "私有" : "公开");
	var projectType = $("<td class='center menu-ids'>" + type + "</td>");
	tr.append(projectType);
	// 开始时间
	var startTime = $("<td class='center'>" + AckTool.date(data.startTime, fmt)
			+ "</td>");
	tr.append(startTime);
	// 结束时间
	var endTime = $("<td class='center'>" + AckTool.date(data.endTime, fmt)
			+ "</td>");
	tr.append(endTime);
	// 评论
	var remark = $("<td class='center'>" + data.remark + "</td>");
	tr.append(remark);
	// 操作
	// 获得操作按钮
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

Project.showMananger = function(id) {
	var url = "/user/managers";
	var data = {};
	var select = $("#managerId", Project.document).empty();
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				var item = obj[i];
				var option = $("<option></option>");
				var managerName = item.surname + item.name;
				if (id == item.id) {
					option.attr("selected", "selected");
				}
				option.attr("value", item.id);
				option.html(managerName);
				select.append(option);
			}
		}
	});
}
/**
 * 查询所有部门
 */
Project.showALLDepartment = function(ids) {
	var url = "/dept/all";
	var data = {};
	var select = $("#cooperativeSector", Project.document);
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				var item = obj[i];
				var option = $("<option value='" + item.id + "'>"
						+ item.departmentName + "</option>");
				// 回显操作
				if (ids) {
					var idsLen = ids.length;
					for (var j = 0; j < idsLen; j++) {
						var deptId = ids[j];
						if (deptId == item.id) {
							option.attr("selected", "selected");
						}
					}
				}
				select.append(option);
			}
		}
	});
}

Project.showCurrentDepartment = function(id) {
	// deptId 在 index页面
	var deptId = id || $("#index-user-dept-id", Project.document).val();
	var deptUrl = "/dept/id/" + deptId;
	var deptData = {};
	var select = $("#departmentName", Project.document).empty();
	AckTool.postReq(deptData, deptUrl, function(obj) {
		if (obj) {
			$("#departmentName", Project.document).val(obj.departmentName);
			$("#departmentId", Project.document).val(obj.id);
			$("#deptName", Project.document).val(obj.departmentName);
		}
	});
}

/**
 * 页面
 */

Project.eidtUI = function(id) {
	// alert(id);
	var url = "";
	var data = {};
	data.reqData = {};
	if (id) {
		url = "/project/edit/ui/" + id;
		var projectDataUrl = "/project/id/" + id;
		// 这里需要有个请求回显数据
		Project.modal.open(url, data, function() {
			AckTool.postReq({}, projectDataUrl, function(obj) {
				$("#optionFlag", Project.document).val("1");
				$("#id", Project.document).val(obj.id);
				$("#name", Project.document).val(obj.name);
				$("#departmentId", Project.document).val(obj.departmentId);
				$("#departmentName", Project.document).val(obj.departmentName);
				var options = $("#type", Project.document).find("option");
				options.each(function(index, element) {
					var opt = $(this);
					var val = opt.attr("value");
					if (val == obj.type) {
						opt.attr("selected", "selected");
					}
				});
				$("#type", Project.document).val(obj.type);
				$("#deptName", Project.document).val(obj.departmentName);
				$("#remark", Project.document).val(obj.remark);
				// 加载项目经理
				Project.showMananger(obj.managerId);
				// 加载所有部门并回显
				var deptIds = "";
				var idArray = [];
				var cooperativeSectors = obj.cooperativeSectors;
				for ( var n in cooperativeSectors) {
					var dept = cooperativeSectors[n];
					deptIds += dept.id + ",";
				}
				if (deptIds) {
					deptIds = deptIds.substring(0, deptIds.length - 1);
					idArray = deptIds.split(",");
				}
				Project.showALLDepartment(idArray);
			});
		});
	} else {
		url = "/project/add/ui";
		Project.modal.open(url, data, function() {
			$("#optionFlag", Project.document).val("0");
			// 加载当前部门
			Project.showCurrentDepartment();
			// 加载全部部门
			Project.showALLDepartment();
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
	// 添加
	if ("0" == flag) {
		url = "/project/add";
		var val = $("#managerId", Project.document).val();
		var option = $("#managerId", Project.document).find(
				"option[value='" + val + "']");
		var text = option.text();
		$("#managerName", Project.document).val(text);
	}
	if ("1" == flag) {
		url = "/project/edit";
	}

	var data = $("#ack-add-form", Project.document).serialize();
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			// 关闭modal
			Project.modal.close();
			// 刷新当前页面
			Project.showList();
		} else {
			alert("系统错误");
			// 关闭modal
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
				// 关闭modal
				Project.modal.close();
				// 刷新当前页面
				Project.showList();
			} else {
				alert("系统错误");
				// 关闭modal
				Project.modal.close();
			}

		});
	};
	var modal = this.modal.modalTemplate(option);
	modal.modal('show');

}

/**
 * 绑定事件
 */
Project.bind = function() {
	var ackModal = $("#ack-modal", Project.document);
	// 修改
	$("#simple-table").on("click", ".ack-simple-btn-edit", function() {
		var id = $(this).parents("tr").attr("id");
		Project.eidtUI(id);
	});
	// 删除
	$("#simple-table").on("click", ".ack-simple-btn-del", function() {
		var id = $(this).parents("tr").attr("id");
		Project.del(id);
	});
	// 项目菜单
	$("#simple-table").on("click", ".ack-simple-btn-Project-menu", function() {
		var td = $(this).parents("tr").find("td").eq(2);
		var menuIds = td.text();
		var id = $(this).parents("tr").attr("id");
		// ztree();
		Project.menu2ProjectUI(menuIds, id);
	});
	// 项目菜单保存
	ackModal.on("click", ".ack-modal-Project-menu-save-btn", function() {
		Project.menu2Project();
	});
	// 保存
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
	// 绑定事件
	Project.bind();
	// 展示数据
	Project.showList();
}
