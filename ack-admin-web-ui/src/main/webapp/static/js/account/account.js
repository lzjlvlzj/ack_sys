/**
 * 账簿js
 */
var Account = window.Account || {};

Account.modal = parent.AckSystem.modal;

Account.document = window.parent.document || window.document;

Account.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Account.getOneTr;
    return option;
}

Account.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //客户名称
    var clientName = $("<td>"+data.client.name+"</td>");
    tr.append(clientName);
    //客户电话
    var clientPhone = $("<td>"+data.client.phone+"</td>");
    tr.append(clientPhone);
    //账号余额
    var balance = $("<td>"+data.balance+"</td>");
    tr.append(balance);
    //产品实际扣除
    var flow = $("<td>"+data.flow+"</td>");
    tr.append(flow);
    //产品点券
    var coin = $("<td>"+data.coin+"</td>");
    tr.append(coin);
    //产品点券收支
    var coinFlow = $("<td>"+data.coinFlow+"</td>");
    tr.append(coinFlow);
    //交易流水号
    var tradeNumber = $("<td>"+data.tradeNumber+"</td>");
    tr.append(tradeNumber);
    //账号金额流转原因
    var flowCase = $("<td>"+data.flowCase+"</td>");
    tr.append(flowCase);
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
    opt.prefix = "account";
    var buttons = AckTool.optionButton.getTrAuthButtons(opt);
    optionTd.append(buttons);
    tr.append(optionTd);
    return tr;
}

/**
 *
 * 初始化显示
 * */
Account.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Account.config();
    var data = {};
    var url = "/account/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Account.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
}

Account.list = function(pageNo){
    var url = "/account/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Account.config();
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
 * @param flag 0 : 导航账簿 , 1 : 功能账簿
 * */
Account.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/account/edit/ui/"+id;
        var AccountDataUrl = "/account/id/" + id;
        //这里需要有个请求回显数据
        Account.modal.open(url,data,function(){
            AckTool.postReq({},AccountDataUrl,function(obj){
                $("#optionFlag",Account.document).val("1");
                $("#id",Account.document).val(obj.id);
                $("#tradeNumber",Account.document).val(obj.tradeNumber);
                $("#clientId",Account.document).val(obj.clientId);
                $("#balance",Account.document).val(obj.balance);
                $("#clientName",Account.document).val(obj.client.name);
                $("#flow",Account.document).val(obj.flow);
                $("#flowCase",Account.document).val(obj.flowCase);
                $("#userId",Account.document).val(obj.userId);
                $("#remark",Account.document).val(obj.remark);
            });
        });
    } else {
        url = "/account/add/ui";
        Account.modal.open(url,data,function(){
            $("#optionFlag",Account.document).val("0");
        });
    }
}

/**
 * 编辑操作
 */

Account.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/account/add"
    }
    if("1" == flag){
        url = "/account/edit"
    }

    var data = $("#ack-add-form", Account.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Account.modal.close();
            // 刷新当前页面
            Account.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Account.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Account.modal.close();
        }

    });

}
/**
 * 删除
 *
 * */
Account.del = function(id){
    var url = "/account/del/"+id;
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
                Account.modal.close();
                //刷新当前页面
                Account.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Account.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

}

/**
 * 绑定事件
 * */
Account.bind = function() {
    var ackModal = $("#ack-modal", Account.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var id = $(this).parents("tr").attr("id");
        Account.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Account.del(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Account.document);
        var flag = fp.val();
        Account.eidt(flag);
    });
}

Account.init = function (){
    Account.showList();
    Account.bind();
}


