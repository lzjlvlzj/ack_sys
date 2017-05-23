var roleMenuIds;
var zTreeId = "act_tree";
var setting = {
    check: {
		enable : true,
		chkboxType : {
			"Y" : "ps", 
			"N" : "ps"
		}
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	async : {
		enable : true,
		url : "/menu/tree",
		autoParam : [ "id", "pId", "value", "pid" ],
		type : "post",
		dataFilter : process
	},
	callback : {
		onClick : nodeOnclick,
		onAsyncSuccess: zTreeOnAsyncSuccess
	}
	
};

function zTreeOnAsyncSuccess(){
	var treeObj = $.fn.zTree.getZTreeObj(zTreeId);
	treeObj.expandAll(true);
}

function process(treeId, parentNode, responseData) {
	
	for (var i = 0; i < responseData.length; i++) {
		var node = responseData[i];
		if (roleMenuIds) {
			for (var j = 0 ; j < roleMenuIds.length; j++ ){
				var mid = roleMenuIds[j];
				if (node.id == mid) {
					node.checked = true;
				}
			}
		}
	}
	
    return responseData;
}

function getZTreeObj(){
	return $.fn.zTree.getZTreeObj(zTreeId);
}

function nodeOnclick(event, treeId, treeNode){
	alert(treeNode.id);
}

function getCheckedNodes() {
	var treeObj = $.fn.zTree.getZTreeObj(zTreeId);
	var nodes = treeObj.getCheckedNodes(true);
	//console.log(nodes.length);
	treeObj.expandAll(true);
	return nodes;
}

function zTreeInit(menuIds){
	roleMenuIds = menuIds.split(",");
	$.fn.zTree.init($("#"+zTreeId), setting);
}
