/**
 * 交易js
 */
var Trade = window.Trade || {};

Trade.modal = parent.AckSystem.modal;

Trade.document = window.parent.document || window.document;

Trade.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Trade.getOneTr;
    return option;
};

Trade.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //流水号
    var number = $("<td>"+data.number+"</td>");
    tr.append(number);
    //客户名称
    var clientName = $("<td>"+data.client.name+"</td>");
    tr.append(clientName);
    //状态
    var status = data.status;
    var statusStr = "";
    if(status == 0){
        statusStr = "创建成功";
    } else if(status == 1){
        statusStr = "已提交库房,等待发货";
    } else if( status == 2){
        statusStr = "库房已发货";
    }
    var statusTr = $("<td>"+statusStr+"</td>");
    tr.append(statusTr);
    //修改时间
    var ut = AckTool.date(data.updateTime, "yyyy-MM-dd hh:mm:ss");
    var updateTime = $("<td>"+ut+"</td>");
    tr.append(updateTime);
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
    opt.prefix = "trade";
    var onlyBtn = []
    if(status == 0){
        onlyBtn.push("upstock");
    }
    if(status == 1){
        onlyBtn.push("print");
    }
    opt.onlyBtn = onlyBtn;
    var buttons = AckTool.optionButton.getTrAuthButtons(opt);
    optionTd.append(buttons);
    tr.append(optionTd);
    return tr;
};

/**
 *
 * 初始化显示
 * */
Trade.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Trade.config();
    var data = {};
    var url = "/trade/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Trade.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
};
Trade.list = function(pageNo){
    var url = "/trade/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Trade.config();
    AckTool.postReq(data,url,function(obj){
        if( obj ){
            option.data = obj.result;
            AckTool.table.show(option);
        }
    });
};
/**
 * 查询所以销售人员
 */
Trade.showWheelMan = function(uid){
    var url = "/trade/wheelman";
    var data = {};
    AckTool.postReq(data,url,function(obj){
        var select = $("#userId",Trade.document).empty();
        if(!obj){
            return false;
        }
        for(var i = 0; i < obj.length; i++){
            var user = obj[i];
            var username = user.surname + user.name;
            var option = $("<option value='"+user.id+"'>"+username+"</option>");
            if(user.id == uid){
                option = $("<option value='" + uid + "' selected='selected'>" + username + "</option>");
            }
            select.append(option);
        }
    });
};
/**
 * 编辑页面
 * @param id 数据id
 * @param flag 0 : 导航交易 , 1 : 功能交易
 * */
Trade.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/trade/edit/ui/"+id;
        var TradeDataUrl = "/trade/id/" + id;
        //这里需要有个请求回显数据
        Trade.modal.open(url,data,function(){
            AckTool.postReq({},TradeDataUrl,function(obj){
                $("#optionFlag",Trade.document).val("1");
                $("#id",Trade.document).val(obj.id);
                Trade.showWheelMan(obj.user.id);
                $("#name",Trade.document).val(obj.name);
                $("#address",Trade.document).val(obj.address);
                $("#phone",Trade.document).val(obj.phone);
                $("#qq",Trade.document).val(obj.qq);
                $("#weiXin",Trade.document).val(obj.weiXin);
                $("#remark",Trade.document).val(obj.remark);
            });
        });
    } else {
        url = "/trade/add/ui";
        Trade.modal.open(url,data,function(){
            $("#optionFlag",Trade.document).val("0");
            Trade.showWheelMan();
        });
    }
};

/**
 * 编辑操作
 */

Trade.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/trade/add"
    }
    if("1" == flag){
        url = "/trade/edit"
    }

    var data = $("#ack-add-form", Trade.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Trade.modal.close();
            // 刷新当前页面
            Trade.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Trade.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Trade.modal.close();
        }

    });

};
/**
 * 删除
 *
 * */
Trade.del = function(id){
    var url = "/trade/del/"+id;
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
                Trade.modal.close();
                //刷新当前页面
                Trade.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Trade.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

};
/**recharge ui
 *@param id
 */
Trade.rechargeUI = function(id ,name){
    var url = "/trade/recharge/ui";
    var data = {};
    data.id = id;
    data.name = name;
    Trade.modal.open(url,data,function(){
        $("#tradeId",Trade.document).val(id);
        $("#id",Trade.document).val(id);
        $("#name",Trade.document).val(name);
    });
};
/**recharge
 *@param id
 */
Trade.recharge = function(id){
    var url = "/trade/recharge/";
    var data = $("#ack-add-form", Trade.document).serialize();
    var option = {fun : {}};
    option.header = "充值结果";
    option.fun.selector = ".ack-modal-ok-btn";


    AckTool.postReq(data, url, function(obj) {
        var headerCss = "";
        if (obj.code >= 1) {
            option.headerCss = "ack-medal-header-green";
            var balance = obj.data.balance;
            var coin = obj.data.coin;
            var str = "账户余额:" + balance + "</br> 产品点券:" + coin;
            option.content = str;
            Trade.modal.html(option);
            // 关闭modal
            //Trade.modal.close();
            // 刷新当前页面
            //Trade.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Trade.document, obj.message);
        }else {
            option.headerCss  = "ack-medal-header-red";
            option.content = "充值失败请联系管理员";
            Trade.modal.html(option);
            // 关闭modal
            //Trade.modal.close();
        }

    });

};
/**
 * 查看销售单
 */
Trade.findDetail = function(id){
    alert("查看销售单: "+id);
};
/**
 * 提交仓库
 */
Trade.upToStock = function(id){
    alert(id);
    var url = "/trade/2stock";
    var option = {fun : {}};
    option.header = "确认操作";
    option.headerCss = "ack-medal-header-yellow";
    option.content = "确定将该条销售信息提交仓库发货吗?";
    option.fun.selector = ".ack-modal-ok-btn";
    var data = {};
    data.id = id;
    //点击弹框"确定"的回调操作
    option.fun.callback = function(){
        AckTool.postReq(data, url, function(obj) {
            if (obj.code == 1) {
                //关闭modal
                Trade.modal.close();
                //刷新当前页面
                Trade.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Trade.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');
};

/**
 * 绑定事件
 * */
Trade.bind = function() {
    var ackModal = $("#ack-modal", Trade.document);
    var tab = $("#tab-body");
    //修改
    tab.on("click",".ack-simple-btn-edit",function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        Trade.eidtUI(id);
    });
    //删除
    tab.on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Trade.del(id);
    });
    //查看销售单
    tab.on("click",".ack-simple-btn-trade-view", function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        Trade.findDetail(id);
    });
    //提交仓库
    tab.on("click",".ack-simple-btn-trade-2stock", function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        Trade.upToStock(id);
    });
    //充值页面
    $("#tab-body").on("click",".ack-simple-btn-trade-recharge",function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        var name = tr.find("td").eq(1).text();
        Trade.rechargeUI(id, name);
    });
    //充值操作
    ackModal.on("click",".ack-recharge-save-btn", function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("tradeId");
        Trade.recharge(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Trade.document);
        var flag = fp.val();
        Trade.eidt(flag);
    });

};

Trade.init = function (){
    Trade.showList();
    Trade.bind();
};


