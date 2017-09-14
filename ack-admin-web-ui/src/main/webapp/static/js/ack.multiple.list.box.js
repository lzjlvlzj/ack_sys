var AckMultipleListBox = window.AckMultipleListBox || {}

var AckDocument = window.parent.document || window.document;

//源select
AckMultipleListBox.src = {
	select : function (doc){
		return $("#multiple-box-list-src", doc);
	},
	button : function (doc){
		return $("#multiple-box-list-src-btn", doc);
	}
}

//目标select
AckMultipleListBox.target = {
	select : function (doc){
		return $("#multiple-box-list-target", doc);
	},
	button : function (doc){
		return $("#multiple-box-list-target-btn", doc);
	}
};

/**
 * 初始化
 * 
 * */
AckMultipleListBox.init = function(doc, srcObj, targetObj){
	
	AckMultipleListBox.event(doc);
}

/**
 * 显示数据
 * 
 * @param srcObj
 *            左边box数据
 * @param targetObj
 *            右边box数据加载数据
 */
AckMultipleListBox.show = function() {
    
}

// 生产一个option
AckMultipleListBox.show.prototype.createOption = function(value, text){
	var option = $("<option></option>");
	option.attr("value", value);
	option.html(text);
	return option;
}
// 展示源
AckMultipleListBox.show.prototype.src = function(obj){
	
}
// 展示目标
AckMultipleListBox.show.prototype.target = function(obj){
	
}

/**
 * 绑定事件
 * 
 */
AckMultipleListBox.event = function(doc) {
	var srcSelect = AckMultipleListBox.src.select(doc);
	var srcButton = AckMultipleListBox.src.button(doc);
	var targetSelect = AckMultipleListBox.target.select(doc);
	var targetButton = AckMultipleListBox.target.button(doc);
	
	// option event
	srcSelect.on("click","option", function(){
		var self = $(this);
		//将选中的option移除本身添加到target
		self.remove();
		targetSelect.append(self);
	});
	
	// option event
	targetSelect.on("click","option", function(){
		var self = $(this);
		//将选中的option移除本身添加到src
		self.remove();
		srcSelect.append(self);
	});
	
	// button event
	srcButton.on("click",function(){
		var options = srcSelect.find("option");
		options.remove();
		targetSelect.append(options);
	});
	
	// button event
	targetButton.on("click",function(){
		var options = targetSelect.find("option");
		options.remove();
		srcSelect.append(options);
	});
}

/**
 * 返回结果
 * 
 */

AckMultipleListBox.getResult = function(doc){
	var targetSelect = AckMultipleListBox.target.select(doc);
	var options = targetSelect.find("option");
	var ids = "";
	if(options){
		var len = options.length;
		for(var i =0; i < len; i++){
			var opt = $(options[i]);
			var val = opt.attr("value");
			ids = ids + val + ",";
		}
		ids = ids.substring(0, ids.length - 1);
	}
	return ids;
}
