var EmployeeJobStatistics = window.EmployeeJobStatistics || {};

EmployeeJobStatistics.modal = parent.AckSystem.modal;

EmployeeJobStatistics.document = window.parent.document || window.document;

EmployeeJobStatistics.init = function() {
	// 展示列表
	EmployeeJobStatistics.showList();
	//绑定事件
	EmployeeJobStatistics.bind();
}
/**
 * 导出excel
 * 
 * */
EmployeeJobStatistics.exportExcel = function(){
	var data = {};
	var url = "/job/exportExcel";
	window.location.href=url;
}

/**
 * 绑定事件
 * */
EmployeeJobStatistics.bind = function() {
	
}



EmployeeJobStatistics.config = function (){
	var tb = $("#tab-body");
	var option = {};
	option.tab = tb;
	option.excludeFileds = [];
	option.getOneTr = EmployeeJobStatistics.getOneTr;
	return option;
}

EmployeeJobStatistics.showList = function() {
	var option = EmployeeJobStatistics.config();
	var data = {};
	var opt = {};
	opt.prefix = "log";
	//如果已经有datatable那么点击刷新页面
	if($.fn.dataTable.isDataTable( '#ack-dynamic-table' )){
	    var table = $('#ack-dynamic-table').DataTable();
	    table.draw();
	} else {
	    $('#ack-dynamic-table').DataTable( {
     	 "processing": true,
         "serverSide": true,
         "ajax": {
             "url" : "/job/log/statistics",
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
             { "data": "departmentName"},
             { "data": "projectName"},
             { "data": "content"},
             { "data": "cacheStatus",
               "render" : function(data, type, full, meta){
            	   var span = '<span class="status-span" style="display:none">' + data + '</span>';
              	   var enabled = AckTool.optionButton.statusButton("缓存") + span;
              	   var disabled = AckTool.optionButton.statusButton("正常") + span; 
              	   if(data == 0){
              		   return enabled;
              	   }
              	   return disabled;
               }
             },
             { "data": "createTime",
                 "render" : function(data, type, full, meta){
                	  var fmt = "yyyy-MM-dd hh:mm:ss";
                	  var d = AckTool.date(data, fmt);
                	  return d;
                  } 
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

EmployeeJobStatistics.list = function(pageNo){
	
	var url = "/user/page";
	var data = {};
	data.currentPage = pageNo;
	data.loginName = $("#loginName").val();
	var option = EmployeeJobStatistics.config();
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
