/**
 * ack系统js
 * 
 */
var AckSystem = window.AckSystem || {};
//权限
AckSystem.authMenus = {};

AckSystem.user = {
	
	init : function(){
		AckSystem.user.task();
		AckSystem.user.message();
		AckSystem.user.email();
		AckSystem.user.info();
        AckSystem.user.setting();
	},
	/**用户设置*/
	setting : function(){
        $("#reset-password-a").click(function(){
        	var url = "/user/password/update/ui";
            AckSystem.iframe(url);
		});
	},
	/**TODO 代办信息**/
	task : function(){
		console.log("用户代办任务信息初始化");
	},
	/**TODO 消息信息*/
	message : function(){
		console.log("用户任务到期信息初始化");
	},
	/**TODO 邮件信息*/
	email : function(){
		console.log("用户邮件信息初始化");
	},
	/**获得登录用户信息*/
	info : function(){
		var data = {};
		var url = "/user/current";
		AckTool.postReq(data, url, function(user){
			var userInfo = "<small>Welcome,</small>" + user.loginName
			$("#login-user-info").html(userInfo);
		});
	},
	_tmp : "",
    loginName : "",//TODO
	permissions : function(){
	   AckSystem.user._tmp = null;
	   AckSystem.user.calcPermissions();
	   var s = AckSystem.user._tmp;
	   if(!s){
		  return [];
	   }
	   s = s.substring(0, s.length - 1);
	   var array = s.split(",");
	   return array;
	},
	calcPermissions : function(m){
		var menuNodes = m || AckSystem.authMenus.children;
		if(!menuNodes || menuNodes.length == 0){
			return "";
		}
		var len = menuNodes.length;
		for(var i = 0; i < len; i++){
			var node = menuNodes[i];
			var item = node.value;
			AckSystem.user._tmp += item.permission + ",";
			var childMenus = node.children;
			if(!childMenus || childMenus.length == 0){
				continue;
			}
			AckSystem.user.calcPermissions(childMenus);
		}
	}
};

AckSystem.init = function() {
	AckSystem.user.init();
	AckSystem.menu.init();
	
}

function iFrameHeight() {
	var ifm = document.getElementById("mainFrame");
	var subWeb = null;
	if(document.frames){
		subWeb = document.frames["mainFrame"].document;
	} else {
		subWeb = ifm.contentDocument;
	}
	if (ifm != null && subWeb != null) {
		var subH = subWeb.body.scrollHeight;
		var subH1 = subWeb.documentElement.scrollHeight;
		if(subH1 > subH){
			subH = subH1;
		}
		if (ifm.height != subH) {
			ifm.height = subH
		}
	}
}
/**
 * 获得权限字符串
 * */
AckSystem.getPermissionStr = function(m){
	
}

/**
 * iframe modal问题处理
 */
AckSystem.modal = {
	dialogDiv : $("<div class='modal-dialog'></div>"),

	contentDiv : $("<div class='modal-content'></div>"),

	headerDiv : $("<div class='modal-header no-padding'></div>"),

	bodyDiv : $("<div class='modal-body no-padding'></div>"),

	footerDiv : $("<div class='modal-footer no-margin-top'></div>"),
	// 确认后的操作
	callback : this.close,

	closeAndOk : $(""),

	ok : $("<button class='btn btn-sm btn-info pull-right ack-modal-ok-btn' data-dismiss='modal'><i class='ace-icon fa fa-times '></i> 确定</button>"),
	/**
	 * @param url 要打开的url
	 * @param data 参数封装 {selfData :{}, reqData{}}
	 * @param callback 回调
	 * 
	 * */
	open : function (url, data, callback) {
		var ackModal = $("#ack-modal");
		var reqData = data.reqData || {};
		ackModal.empty().load(url, reqData, function() {
			$(this).modal('show');
			$(this).off('shown.bs.modal');
			if( typeof(callback) ===  'function') {
				$(this).on('shown.bs.modal', callback);
			}
		});
	},
	// 关闭
	close : function(b) {
		var flag = b || true;
		$("#ack-modal").modal('hide');
		if (b) {
			$("#ack-modal").empty();
		}

	},
	html : function(option){
        AckSystem.modal.modalTemplate(option);
	},
	// (用户只填充需要展示的内容)
	modalTemplate : function(option) {
		// 弹框标题
		var header = option.header || "弹框";
		// 弹框颜色
		var headerCss = option.headerCss || "table-header";
		// 弹框内容
		var content = option.content || "";
		var ct = $("<div class='ack-modal-body-content col-sm-12'> " + content
				+ " </div>");

		// 弹框底部按钮
		var footerBtn = option.footerBtn || this.ok;
		// 回显操作
		var callback = option.fun.callback;

		var selector = option.fun.selector || ".ack-modal-ok-btn";

		var headerContentDiv = $("<div class='" + headerCss + "'></div>");
		var xBtn = $("<button type='button' class='close' data-dismiss='modal' aria-hidden='true'><span class='white'>×</span></button>");

		headerContentDiv.append(xBtn);
		headerContentDiv.append(header);

		this.headerDiv.empty().append(headerContentDiv);
		this.bodyDiv.empty().append(ct);
		this.footerDiv.empty().append(footerBtn);

		this.contentDiv.append(this.headerDiv);
		this.contentDiv.append(this.bodyDiv);
		this.contentDiv.append(this.footerDiv);

		this.dialogDiv.append(this.contentDiv);

		$("#ack-modal").empty().append(this.dialogDiv);
		// 绑定事件
		$(selector).unbind().on("click", callback);

		return $("#ack-modal");
	}

};

AckSystem.iframe = function(url) {
	$("#mainFrame").attr("src", url);
	// 页面适应
	iFrameHeight();
}

AckSystem.navigation = {
	li : function(){
		return $("<li></li>");
	},
	checkSubMenu : function(childMenus){
		var tag = true;
		if(!childMenus){
		   return tag;
		} else {
			for(var i in  childMenus){
				var menu = childMenus[i];
				if(menu.menuLevel && menu.menuLevel > 1){
					tag = false;
					break;
				}
			}
	    }
		return tag;
	},
	//显示导航的dom
	ul : $("#ack-navigation").empty(),
	//展示
	show : function(menu){
		this.ul.empty();
		//排除有子菜单的
		if(this.checkSubMenu(menu.childMenus) && menu.menuLevel == 1){//1级菜单
			var li = $("<li></li>");
			//添加图标
			var i = $("<i class='home-icon " + menu.css + "'></i>");
			li.append(i);
			//添加内容
			li.append(menu.menuName);
			this.ul.append(li);
			
		} else if(menu.menuLevel == 2) {// 2级菜单
			//显示1级菜单
			var pLi = $("#leave-list").parents("li");
			var oneLevel = $("<li></li>");
			var oneI = $("<i></i>");
			var oneA = $("<a></a>");
			var oneText = pLi.find("span").text();
			var oneCss = pLi.find("i").attr("class") + " home-icon";
			oneI.addClass(oneCss);
			oneA.append(oneText);
			oneLevel.append(oneI);
			oneLevel.append(oneA);
			this.ul.append(oneLevel);
			//显示本身
			var twoLevel = $("<li></li>");
			var towText = $("#" + menu.domId).text();
			twoLevel.addClass("active");
			twoLevel.append(towText);
			this.ul.append(twoLevel);
			
		} else if(menu.menuLevel == 3) {// 3级菜单
			//显示1级菜单
			//显示2级菜单
			//显示本身
		} else {
			
		}
		
	}
};

AckSystem.navigationLi = function(nav, clazz, menuName, dom){
	if(clazz) {
		nav.empty();
	}
	
	var li = $("<li></li>");
	var a = $("<a href='javascript:void(0);' class='ack-nav-a-1'></a>");
	nav.on("click",".ack-nav-a-1",function(){
		dom.click();
	});
	a.append(menuName);
	if(clazz){
		var i = $("<i class='home-icon " + clazz + "'></i>");
		li.append(i);
	}
	li.append(a);
	return li;
}
/**
 * 绑定事件
 * */
AckSystem.event =  {
	//菜单事件
	menu : function(m, parent){
		var ul = parent || $("#menu-list");
		var id = "#" + m.domId;
		var url = m.url;
		//绑定事件
		ul.on("click", id, function() {
			var _self = $(this);
			var childMenus = _self.childMenus;
			if(!childMenus || childMenus.length == 0){
				$("#menu-list>li").removeClass("active");
				_self.parent().addClass("active");
			}
			//更换iframe内容
			AckSystem.iframe(url);
			//显示导航
			AckSystem.navigation.show(m);
		});
	}
	
};

/**
 * 展示菜单
 */
AckSystem.menu = {
	//初始化请求菜单信息
	init : function(){
		
		var url = "/user/menus";
		var data = {};
		AckSystem.postReq(data, url, function(obj) {
			if (obj) {
				AckSystem.authMenus = obj;
				AckSystem.menu.show(obj.children);
				//默认显示
				$("#dashborad-mem").click();
			}
		});
		/*
		AckSystem.menu.sysMenu();
		AckSystem.menu.userMenu();
		*/
	},
	/**系统菜单(生成后样式有bug)*/
	sysMenu : function(obj){
		
		var btnDivWarp = $("#sidebar-shortcuts-large");
		var btnDivWarpMini = $("#sidebar-shortcuts-mini");
		var btn = $('<button></button>');
		
		var i = $('<i class="' + obj.css + '"></i>');
		var span = "";
		var btnCss = "";
		var domId = obj.domId;
		//用户
		if ( domId.indexOf('user-mem') >= 0 ) {
			btnCss = "btn btn-warning";
			span = $('<span class="' + btnCss + '"></span>');
		}
		if ( domId.indexOf('role-mem') >= 0 ) {
			btnCss = "btn btn-info";
			span = $('<span class="' + btnCss + '"></span>');
		}
		if ( domId.indexOf('menu-mem') >= 0 ) {
			btnCss = "btn btn-success";
			span = $('<span class="' + btnCss + '"></span>');
		}
		if ( domId.indexOf('dept-mem') >= 0 ) {
			btnCss = "btn btn-danger";
			span = $('<span class="' + btnCss + '"></span>');
		}
		if(btnCss && span){
			btn.attr("id",domId);
			btn.addClass(btnCss);
			btn.append(i);
			btnDivWarp.append(btn);
			btnDivWarp.append("&nbsp;");
			btnDivWarpMini.append(span);
			
			var id = "#" + domId;
			var url = obj.url;
			btnDivWarp.on('click', id, function(){
				AckSystem.iframe(url);
			});
			
		}
		
		/*
		$("#ack-sys-users-btn").unbind().on("click",function(){
			AckSystem.iframe("/user/list/ui");
		});
		$("#ack-sys-role-btn").unbind().on("click",function(){
			AckSystem.iframe("/role/list/ui");
		});
		$("#ack-sys-menu-btn").unbind().on("click",function(){
			AckSystem.iframe("/menu/list/ui");
		});
		$("#ack-sys-dept-btn").unbind().on("click",function(){
			AckSystem.iframe("/dept/list/ui");
		});
		*/
	},
	/**用户自定义菜单*/
	userMenu : function(){
		var url = "/user/menus";
		var data = {};
		AckSystem.postReq(data, url, function(obj) {
			if (obj) {
				AckSystem.authMenus = obj;
				AckSystem.menu.show(obj);
				//默认显示
				$("#dashborad-mem").click();
			}
		});
	},
	//二级菜单
	twoLevel : function(parent, nodes){
        var ul = $("<ul class='submenu nav-show'>");
	    var len = nodes.length;
	    var parentA = parent.find("a").eq(0);
	    var dropwonB = $("<b class='arrow fa fa-angle-down'></b>");
	    var flag = 0;
	    for(var i in nodes){
		   var node = nodes[i];
		   var menu = node.value;
		   //排除是按钮类型的子菜单
		   if(menu.menuType == 1){
			   continue;
		   }
		   flag++;
		   var li = $("<li class=''></li>");
		   var a = $('<a href="javascript:void(0);" id="' + menu.domId + '"></a>');
		   var i = $("<i class='menu-icon fa fa-caret-right'></i>");
		   var b = $("<b class='arrow'></b>");
		   a.append(i);
		   a.append(menu.menuName);
		   
		   li.append(a);
		   li.append(b);
		   ul.append(li);
		   //绑定事件
		   AckSystem.event.menu(menu, parent);
		   
	    }
	    if(flag >= 1){
	    	parentA.attr("class", "dropdown-toggle");
	    	parentA.append(dropwonB);
	    }
	    parent.append(ul);
	},	
	//一级菜单	
	oneLevel : function (node){
		var menu = node.value;
		var li = $("<li class=''></li>");
		var a = $("<a href='javascript:void(0);' id='" + menu.domId + "'></a>");
		var i = $("<i class='" + menu.css + "'></i>");
		var span = $("<span class='menu-text'>" + menu.menuName + "</span>");
		var b = $("<b class='arrow'></b>");
		a.append(i);
		a.append(span);
		li.append(a);
		li.append(b);
		
		var childMenus = node.children;
		if(childMenus && childMenus.length > 0){
			/*
			var dropwonB = $("<b class='arrow fa fa-angle-down'></b>");
			a.attr("class", "dropdown-toggle");
			a.append(dropwonB);
			*/
			this.twoLevel(li, childMenus);
		}
		//绑定事件
		AckSystem.event.menu(menu);
		return li;
	},
	//展示菜单
	show : function(obj) {
		var len = obj.length;
		var ul = $("#menu-list").empty();
		var sidebarShortcuts = $("#sidebar-shortcuts");
		var hasSysMenu = false;
		for (var i = 0; i < len; i++) {
			var node = obj[i];
			var m = node.value;
			//console.log("----------");
			//console.log(m.menuLevel);
			//展示系统菜单
			if(m.menuLevel == 0){
				hasSysMenu = true;
				AckSystem.menu.sysMenu(m);
			} else {// 展示用户菜单
				var li = this.oneLevel(node);
				ul.append(li);
			}
		}
		
		if(!hasSysMenu){
			sidebarShortcuts.remove();
		}
		
	}
};



/**
 * ajax request
 */
AckSystem.postReq = function(data, url, callback) {
	$.ajax({
		url : url,
		type : "post",
		data : data,
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(resObj) {
			//iframe
			callback(resObj);
		}
	});
};
