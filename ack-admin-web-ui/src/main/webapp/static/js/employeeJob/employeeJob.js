var EmployeeJob = window.EmployeeJob || {};

EmployeeJob.modal = parent.AckSystem.modal;

EmployeeJob.document = window.parent.document || window.document;

EmployeeJob.init = function() {
	//加载项目任务
	EmployeeJob.showProjectTask();
	//加载本身
	EmployeeJob.bind();
	
	var option = {};
	option.calendarId = "calendar";
	option.selectEvent = EmployeeJob.selectEvent;
	option.drop = EmployeeJob.drop;
	option.eventDrop = EmployeeJob.eventDrop;
	
	option.events= {
		 url: '/job/log/list',
	     type: 'POST',
	     data: {},
	     error: function() {
	        alert('there was an error while fetching events!');
	     },	
	};
	
	EmployeeJob.calendar.init(option);
	// load cache
	EmployeeJob.log.showCache();
}


EmployeeJob.showProjectTask = function(){
	var data = {};
	var url = "/job/project";
	var select = $("#projectId");
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			for(var i = 0; i < obj.length; i++){
				var item = obj[i];
				var option = $("<option></option>");
				option.attr("value",item.id);
				option.html(item.name);
				select.append(option);
			}
		}
		
	});
}
/**
 * 修改
 * 
 * */
EmployeeJob.editLog = function(calEvent, jsEvent, view){
	var id = $("#id", EmployeeJob.document).val();
	var content = $("#event-content", EmployeeJob.document).val();
	var url = "/job/log/update";
	var data = {};
	data.id = id;
	data.content = content;
	
	AckTool.postReq(data, url, function(obj) {
		if(obj){
			calEvent.title = content;
			EmployeeJob.calendar._self.fullCalendar('updateEvent', calEvent);
			EmployeeJob.modal.close();
		}
	});
	
}
/**
 * 删除
 * 
 * */
EmployeeJob.delLog = function(){
	console.log("删除测试11111111111111111");
	var id = $("#id", EmployeeJob.document).val();
	var eventId = $("#_id", EmployeeJob.document).val();
	var data = {};
	var url = "/job/log/del/"+id;
	AckTool.postReq(data, url, function(obj) {
		if (obj == 1) {
			EmployeeJob.calendar._self.fullCalendar('removeEvents' , function(ev){
				return (ev._id == eventId);
			});
			EmployeeJob.modal.close();
		} else {
			alert("删除失败");
		}
		
	});
}

/**
 * 删除事件
 * 
 * */
EmployeeJob.selectEvent = function(calEvent, jsEvent, view){
	var id = calEvent.id;
	var title = calEvent.title;
	var url = "/job/log/edit/ui";
	var ackModal = $("#ack-modal", EmployeeJob.document);
	var data = {};
	EmployeeJob.modal.open(url,data,function(){
		 $("#id", EmployeeJob.document).val(id);
		 $("#_id", EmployeeJob.document).val(calEvent._id);
		 $("#event-content", EmployeeJob.document).val(title);
	});
	//这里可能因为 日期控件的原因造成按钮事件重复绑定
	ackModal.off("click",".ack-modal-save-btn");
	//保存
	ackModal.on("click",".ack-modal-save-btn", function(e){
		e.preventDefault();
		var val = $('input:radio[name="event-option-flag"]:checked', EmployeeJob.document).val();
		if("0" == val){//删除
			EmployeeJob.delLog();
		}
		if("1" == val){//修改
			EmployeeJob.editLog(calEvent, jsEvent, view);
		}
		
	});
	
	
	/*
	var id = calEvent.id;
	var option = {fun : {}};
	option.header = "确认操作";
	option.headerCss = "ack-medal-header-yellow";
	option.content = "确认删除该条记录?";
	option.fun.selector = ".ack-modal-ok-btn";
	//点击弹框"确定"的回调操作
	option.fun.callback = function(){
		var data = {};
		var url = "/job/log/del/"+id;
		AckTool.postReq(data, url, function(obj) {
			if (obj == 1) {
				EmployeeJob.calendar._self.fullCalendar('removeEvents' , function(ev){
					return (ev._id == calEvent._id);
				});
			} else {
				alert("删除失败");
			}
			
		});
		
	};
	var modal = EmployeeJob.modal.modalTemplate(option);
	modal.modal('show');
	*/
}

EmployeeJob.eventDrop = function(event, delta, revertFunc){
	var id = event.id;
	var url = "/job/log/update";
	var data = {};
	data.id = id;
	data.flag = 1;
	data.st = event.start.format();
	var ed = event.end;
	data.et = ed ? ed.format() : event.start.format();
	AckTool.postReq(data, url, function(obj) {
		if(!obj){
			revertFunc();
		}
	});
}

EmployeeJob.drop = function(eventObj, date, allDay, ui, resourceId){
	var input = eventObj.find("input");
	var logId = input.attr("value");
	
	var data = {};
	data.id = logId;
	data.time = date._i;
	data.flag = 0;
	var url = "/job/log/update";
	
	AckTool.postReq(data, url, function(obj){
		if(obj){
			
			var originalEventObject = eventObj.data('eventObject');

		    // we need to copy it, so that multiple events don't have a reference to the same object
		    var copiedEventObject = $.extend({}, originalEventObject);

		    // assign it the date that was reported
		    copiedEventObject.id = logId;
		    copiedEventObject.start           = date;
		    copiedEventObject.allDay          = allDay;
		    copiedEventObject.backgroundColor = eventObj.css('background-color');
		    copiedEventObject.borderColor     = eventObj.css('border-color');

		    $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
		    
		    //拖动到日志控件后直接删除面面板上的
		    eventObj.remove();
		    //$('#calendar').fullCalendar('render');
		}
	});
}

/**
 * 添加
 * */
EmployeeJob.addLog = function(e){
	var data = {};
	data = $("#add-log-form").serialize();
	var url = "/job/log/cache/add";
	AckTool.postReq(data, url, function(obj) {
		if (obj) {
			e.preventDefault();
			//向上方div插入一个log dom
			var currColor = EmployeeJob.log.color;
			var val = $('#new-event').val();
		    if (val.length == 0) {
		       return;
		    }
			var data = {};
			data.id = obj;
		    data.color = currColor;
		    data.content = val;
			EmployeeJob.log.insertOneLog(data);
		} else {
			EmployeeJob.log.showMsg("新建日志出错");
		}
	});
}

EmployeeJob.log = {
		
	color : "",
	
	showMsg : function (msg){
		$("#ack-add-log-msg").css({"display":"block"}).html(msg);
	},
	/**
	 * 表示下周或者将来要做的
	 * */
	showCache : function(){
		var data = {};
		var url = "/job/cache/list";
		AckTool.postReq(data, url, function(obj) {
			if (obj) {
				var data = obj.result;
				var len = data.length;
				for(var i = 0; i < len; i++){
					var item = data[i];
					EmployeeJob.log.insertOneLog(item);
				}
			} else {
			}
		});
	},
	
	logItemEditEvent : function(){
		var ackModal = $("#ack-modal", EmployeeJob.document);
		ackModal.on("click","#ack-log-del",function(){
			$("#ack-del-msg", EmployeeJob.document).show();
			$("#ack-edit-msg", EmployeeJob.document).hide();
		});
		ackModal.on("click","#ack-log-edit",function(){
			$("#ack-edit-msg", EmployeeJob.document).show();
			$("#ack-del-msg", EmployeeJob.document).hide();
		});
	},
	
	logItemEvent : function(event){
		
		var eventDiv = event|| $('#external-events label.external-event');
		/*$("#external-events").on("drag",".external-event", function(){
			alert(123);
		});*/
		eventDiv.each(function(){
			var eventObject = {
			    title: $.trim($(this).text()) // use the element's text as the event title
			};
		    // store the Event Object in the DOM element so we can get to it later
		    $(this).data('eventObject', eventObject);

		    // make the event draggable using jQuery UI
		    $(this).draggable({
		      zIndex        : 1070,
		      revert        : true, // will cause the event to go back to its
		      revertDuration: 0  //  original position after the drag
		    });
		});
		
	},
	
	getOneLogLabel : function(obj){
		var label = $("<label />");
		label.css({
		   'background-color': obj.color,
		   'border-color'    : obj.color,
		   'color'           : '#fff'
		}).addClass('external-event');
		var checkBox = $("<input type='checkbox' class='ace' value='' />");
		checkBox.attr("value", obj.id);
		var span = $("<span class='lbl'/>");
		span.html(obj.content);
		
		label.append(checkBox);
		label.append(span);
		
		return label;
	   
	},
	
	insertOneLog : function(item){
		/*
		var currColor = EmployeeJob.log.color;
		var val = $('#new-event').val();
	    if (val.length == 0) {
	       return;
	    }
	    */
	    var event = EmployeeJob.log.getOneLogLabel(item);
	    $('#external-events').prepend(event);
	    EmployeeJob.log.logItemEvent(event);
	    //Remove event from text input
	    $('#new-event').val('');
	},
	
	deleteLogItem : function(){
		$("#del-event").click(function(e){
			 var data = {};
			 var url = "/job/log/del";
			 var inputs = $('#external-events input:checked');
			 if(inputs && inputs.length > 0){
				 var ids = "";
				 for(var i =0; i < inputs.length; i++){
					 var input = $(inputs[i]);
					 ids = ids + input.val() + ","
				 }
				 ids = ids.substring(0, ids.length - 1);
				 data.logIds = ids;
			 }
			 
			 AckTool.postReq(data, url, function(obj) {
				 if(obj){
					inputs.each(function(){
						$(this).parents("label").remove(); 
					});
				 } 
			 });
			 
			 /*
			 $('#external-events input').each(function(){
				 alert($(this).val());
				 var checked = $(this).is(':checked');
				 if(checked){
					 
				 }
			 });
			 */
		});
	},
	
	create : function(){
		//绑定点击颜色块事件
		var currColor = '#3c8dbc'; //Red by default
		//add
		$("#color-chooser").on("click","a",function(e){
			 e.preventDefault();
		      //Save color
		     currColor = $(this).css('color');
		      //Add color effect to button
		     $('#add-new-event').css({ 'background-color': currColor, 'border-color': currColor });
		     $("#ack-log-event-color").val(currColor);
		     EmployeeJob.log.color = currColor;
		});
		//绑定点击按钮事件
		$('#add-new-event').click(function (e) {
			var color = $('#add-new-event').css('background-color');
			var color1 = $('#add-new-event').css('border-color');
			console.log(color);
			console.log(color1);
			$("#ack-log-event-color").val(color);
			EmployeeJob.addLog(e);
		});
	}
}

EmployeeJob.bind = function(){
	EmployeeJob.log.logItemEvent();
	EmployeeJob.log.logItemEditEvent();
	EmployeeJob.log.create();
	EmployeeJob.log.deleteLogItem();
}

EmployeeJob.calendar = {
	_self : {},
	init : function(option){
		var calendar = EmployeeJob.calendar.showCalendar(option);
		EmployeeJob.calendar._self = calendar;
	},
	defaultConfig : function(option){
		var config = {
			//themeSystem : 'bootstrap3',
			//dateAlignment :'month',
			//timezone : 'local',
			//weekNumbersWithinDays:true,
			/*
			customButtons: {
		        ackToday: {
		            text: '今天',
		            click: function() {
		            	EmployeeJob.calendar._self.fullCalendar('today');
		            	
		            	var duration = {days : -5};
		            	$('#calendar').fullCalendar( 'incrementDate', duration );
		            	var moment = $('#calendar').fullCalendar('getDate');
		            	alert("The current date of the calendar is " + moment.format());
		            }
		        },
		        ackPrev: {
		            click: function() {
		            	EmployeeJob.calendar._self.fullCalendar('prev');
		            }
		        },
		        ackNext: {
		            click: function() {
		            	EmployeeJob.calendar._self.fullCalendar('next');
		            }
		        },
		        
		    },
		    buttonIcons : {
		    	ackPrev: 'left-single-arrow',
		    	ackNext: 'right-single-arrow',
		    },
			*/
			header : {
				left:   'title',
			    center: '',
			    right:  'today, prev, next '
			},
			
			locale : 'zh-cn',
			buttonHtml: {
				prev: '<i class="ace-icon fa fa-chevron-left"></i>',
				next: '<i class="ace-icon fa fa-chevron-right"></i>'
			},
			events : option.events,
			//eventColor: '#378006',
			editable : false,
		    eventDrop: function(event, delta, revertFunc) {
		    	option.eventDrop(event, delta, revertFunc);
		    	//console.log(event);
		    	
		    	//console.log("start : " + event.start.format());
		    	//console.log("end : " + event.end);
		    	//var days = delta._days;
		    	//console.log(delta);
		    	//console.log(delta.asMilliseconds());
		    	//var duration = {};
		    	//duration.days = days;
		    	//$('#calendar').fullCalendar( 'incrementDate', duration );
		    	/*
		        alert(event.title + " was dropped on " + event.start.format());
                
		        if (!confirm("Are you sure about this change?")) {
		            revertFunc();
		        }
		        */

		    },
			eventLimit : true,
			droppable : true,
			//drop: function(date, allDay) { 
			drop: function(date, jsEvent, ui, resourceId) { 
				var eventObj = $(this);
				option.drop(eventObj, date, jsEvent, ui, resourceId);
				
			},
			eventClick: function(calEvent, jsEvent, view){
				option.selectEvent(calEvent, jsEvent, view);
				/*
				EmployeeJob.calendar._self.fullCalendar('removeEvents' , function(ev){
					return (ev._id == calEvent._id);
				})
				*/
			},
			/*selectable: true,
			selectHelper: true,
	        select: function(start, end, allDay) {
	            alert("这里可以为日期添加事件");
			},*/
			dayClick: function(date, jsEvent, view) {
		        //alert('Clicked on: ' + date.format());

		        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);

		        //alert('Current view: ' + view.name);

		        // change the day's background color just for fun
		       // $(this).css('background-color', 'red');

		    },
			defaultView: 'month',
			
			/*validRange: function(nowDate){
				return {
				  start: nowDate,
	              end: nowDate.clone().add(1, 'months')
				};
		    }*/
			dayRender : function(date, cell){
				//console.log(date);
				//console.log(cell);
			}
		    
		};
		return config;
	},
	//初始加载
	showCalendar : function(config){
		var conf = EmployeeJob.calendar.defaultConfig(config);
		var calendar = $('#calendar').fullCalendar(conf);
		return calendar;
	},
	//重新加载
	reLoadEvent : function(option){
		var data = {}
		var url = "/job/log/list";
		AckTool.postReq(data, url, function(resObj) {
			if(resObj){
				var obj = resObj.result;
				var len = obj.length;
				var events = [];
				for(var i = 0; i < len; i++){
					var item = obj[i];
					var event = {};
					event.title = item.content;
					event.start = item.startTime;
					event.end = item.endTime;
					event.id = item.id;
					event.description = "";
					events.push(event);
				}
				$('#calendar').fullCalendar('renderEvents', events, true);
			}
		});
	}
}