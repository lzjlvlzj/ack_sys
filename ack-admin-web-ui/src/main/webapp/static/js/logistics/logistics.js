/**
 * 品牌js
 */
var Logistics = window.Logistics || {};

Logistics.modal = parent.AckSystem.modal;

Logistics.document = window.parent.document || window.document;

Logistics.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Logistics.getOneTr;
    return option;
}

Logistics.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //客户名称
    var clientName = $("<td>"+data.client.name+"</td>");
    tr.append(clientName);
    //车辆地址
    var vehicle = $("<td>"+data.vehicle+"</td>");
    tr.append(vehicle);
    //司机名称
    var postMan = $("<td>"+data.postMan+"</td>");
    tr.append(postMan);
    //司机电话
    var phone = $("<td>"+data.phone+"</td>");
    tr.append(phone);
    //备注
    var comments = $("<td>"+data.remark+"</td>");
    tr.append(comments);
    //创建时间
    var tm = AckTool.date(data.createTime, "yyyy-MM-dd hh:mm:ss");
    var createTime = $("<td>"+tm+"</td>");
    tr.append(createTime);
    //操作按钮
    var optionTd = $("<td></td>");
    var d = option.data;
    var opt = {};
    opt.data = d;
    opt.prefix = "logistics";
    var buttons = AckTool.optionButton.getTrAuthButtons(opt);
    optionTd.append(buttons);
    tr.append(optionTd);
    return tr;
}

/**
 *
 * 初始化显示
 * */
Logistics.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Logistics.config();
    var data = {};
    var url = "/logistics/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Logistics.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
}

Logistics.list = function(pageNo){
    var url = "/logistics/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Logistics.config();
    AckTool.postReq(data,url,function(obj){
        if( obj ){
            option.data = obj.result;
            AckTool.table.show(option);
        }
    });
}
/**
 * 编辑页面
 * @param id 数据id
 * @param flag 0 : 导航品牌 , 1 : 功能品牌
 * */
Logistics.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/logistics/edit/ui/"+id;
        var LogisticsDataUrl = "/logistics/id/" + id;
        //这里需要有个请求回显数据
        Logistics.modal.open(url,data,function(){
            AckTool.postReq({},LogisticsDataUrl,function(obj){
                $("#optionFlag",Logistics.document).val("1");
                $("#id",Logistics.document).val(obj.id);
                $("#clientId",Logistics.document).val(obj.clientId);
                $("#clientPhone",Logistics.document).val(obj.client.phone);
                $("#clientPhone",Logistics.document).attr("readonly", "readonly");
                $("#vehicle",Logistics.document).val(obj.vehicle);
                $("#postMan",Logistics.document).val(obj.postMan);
                $("#phone",Logistics.document).val(obj.phone);
                $("#address",Logistics.document).val(obj.address);
                $("#postalCode",Logistics.document).val(obj.postalCode);
                $("#remark",Logistics.document).val(obj.remark);
            });
        });
    } else {
        url = "/logistics/add/ui";
        Logistics.modal.open(url,data,function(){
            $("#optionFlag",Logistics.document).val("0");
        });
    }
}

/**
 * 编辑操作
 */

Logistics.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/logistics/add"
    }
    if("1" == flag){
        url = "/logistics/edit"
    }

    var data = $("#ack-add-form", Logistics.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Logistics.modal.close();
            // 刷新当前页面
            Logistics.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Logistics.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Logistics.modal.close();
        }

    });

}
/**
 * 删除
 *
 * */
Logistics.del = function(id){
    var url = "/logistics/del/"+id;
    var modalUrl = "";
    var option = {fun : {}};
    option.header = "确认操作";
    option.headerCss = "ack-medal-header-yellow";
    option.content = "确认删除该条记录?";
    option.fun.selector = ".ack-modal-ok-btn";
    var data = {};
    //点击弹框"确定"的回调操作
    option.fun.callback = function(){
        AckTool.postReq(data, url, function(obj) {
            if (obj == 1) {
                //关闭modal
                Logistics.modal.close();
                //刷新当前页面
                Logistics.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Logistics.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

}

/**
 * 绑定事件
 * */
Logistics.bind = function() {
    var ackModal = $("#ack-modal", Logistics.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var id = $(this).parents("tr").attr("id");
        Logistics.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Logistics.del(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Logistics.document);
        var flag = fp.val();
        Logistics.eidt(flag);
    });
}


Logistics.init = function (){
    Logistics.showList();
    Logistics.bind();
}


