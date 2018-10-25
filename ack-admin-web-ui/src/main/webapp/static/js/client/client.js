/**
 * 客户js
 */
var Client = window.Client || {};

Client.modal = parent.AckSystem.modal;

Client.document = window.parent.document || window.document;

Client.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Client.getOneTr;
    return option;
};

Client.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //客户名称
    var ClientName = $("<td>"+data.name+"</td>");
    tr.append(ClientName);
    //账号余额
    var coinVal = 0.0;
    if(data.account){
        coinVal = data.account.coin;
    }
    var coin = $("<td>"+coinVal+"</td>");
    tr.append(coin);
    //客户地址
    var address = $("<td>"+data.address+"</td>");
    tr.append(address);
    //美导老师
    var userName = data.user.surname + data.user.name;
    var user = $("<td>"+userName+"</td>");
    tr.append(user);
    //客户手机
    var phone = $("<td>"+data.phone+"</td>");
    tr.append(phone);
    //客户qq
    var qq = $("<td>"+data.qq+"</td>");
    tr.append(qq);
    //客户微信
    var weiXin = $("<td>"+data.weiXin+"</td>");
    tr.append(weiXin);
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
    opt.prefix = "client";
    var buttons = AckTool.optionButton.getTrAuthButtons(opt);
    optionTd.append(buttons);
    tr.append(optionTd);
    return tr;
};

/**
 *
 * 初始化显示
 * */
Client.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Client.config();
    var data = {};
    var url = "/client/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Client.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
};
Client.list = function(pageNo){
    var url = "/client/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Client.config();
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
Client.showWheelMan = function(uid){
    var url = "/client/wheelman";
    var data = {};
    AckTool.postReq(data,url,function(obj){
        var select = $("#userId",Client.document).empty();
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
 * @param flag 0 : 导航客户 , 1 : 功能客户
 * */
Client.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/client/edit/ui/"+id;
        var ClientDataUrl = "/client/id/" + id;
        //这里需要有个请求回显数据
        Client.modal.open(url,data,function(){
            AckTool.postReq({},ClientDataUrl,function(obj){
                $("#optionFlag",Client.document).val("1");
                $("#id",Client.document).val(obj.id);
                Client.showWheelMan(obj.user.id);
                $("#name",Client.document).val(obj.name);
                $("#address",Client.document).val(obj.address);
                $("#phone",Client.document).val(obj.phone);
                $("#qq",Client.document).val(obj.qq);
                $("#weiXin",Client.document).val(obj.weiXin);
                $("#remark",Client.document).val(obj.remark);
            });
        });
    } else {
        url = "/client/add/ui";
        Client.modal.open(url,data,function(){
            $("#optionFlag",Client.document).val("0");
            Client.showWheelMan();
        });
    }
};

/**
 * 编辑操作
 */

Client.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/client/add"
    }
    if("1" == flag){
        url = "/client/edit"
    }

    var data = $("#ack-add-form", Client.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Client.modal.close();
            // 刷新当前页面
            Client.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Client.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Client.modal.close();
        }

    });

};
/**
 * 删除
 *
 * */
Client.del = function(id){
    var url = "/client/del/"+id;
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
                Client.modal.close();
                //刷新当前页面
                Client.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Client.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

};
/**recharge ui
 *@param id
 */
Client.rechargeUI = function(id ,name){
    var url = "/client/recharge/ui";
    var data = {};
    data.id = id;
    data.name = name;
    Client.modal.open(url,data,function(){
        $("#clientId",Client.document).val(id);
        $("#id",Client.document).val(id);
        $("#name",Client.document).val(name);
    });
};
/**recharge
 *@param id
 */
Client.recharge = function(id){
    var url = "/client/recharge/";
    var data = $("#ack-add-form", Client.document).serialize();
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
            Client.modal.html(option);
            // 关闭modal
            //Client.modal.close();
            // 刷新当前页面
            //Client.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Client.document, obj.message);
        }else {
            option.headerCss  = "ack-medal-header-red";
            option.content = "充值失败请联系管理员";
            Client.modal.html(option);
            // 关闭modal
            //Client.modal.close();
        }

    });

};
/**
 * 显示物流
 * @param clientId
 */
Client.showLogistics = function(clientId){
    var url = "/client/logistics/"+ clientId;
    var select = $("#logisticsId",Client.document).empty();
    AckTool.postReq({}, url, function(obj) {
        if(obj){
            var len = obj.length;
            for(var i =0 ; i < len; i++){
                var item = obj[i];
                var id = item.id;
                var vehicle = item.vehicle;
                var phone = item.phone;
                var str = vehicle + "==" + phone;
                var option = $("<option  value='"+id+"' >"+str+"</option>");
                if(i == 0){
                    option = $("<option  value='"+id+"' selected='selected'>"+str+"</option>");
                }
                select.append(option);
            }
        }
    });

};

Client.addProduct = function(tab, tr){
    var productTrLength = tab.children('tr').length;
    if(productTrLength > 20){
        alert("一次最多添加20个产品，超过请分多个单子。");
        return false;
    }
    var id = tr.attr("id");
    var name = tr.find("td").eq(1).text();
    var unitPrice = tr.find("td").eq(2).text();
    var input = tr.find("td").eq(3).find("input");
    var totalAmount = tr.find("td").eq(4).text();
    var type = 0;
    if(input.attr("checked") == "checked" || input.prop('checked')){
        type = 1;
    } else{
        type = 0;
    }
    var amount = tr.find("td").eq(5).find("input").val();
    if(amount=='' || amount <=0){
        alert("数量必须大于0");
        return ;
    }else if(amount > 99999){
        alert("数量太大，请分多次添加");
        return ;
    } else if(amount > totalAmount){
        alert("库存不足，到仓储管理添加库存");
        return ;
    }
    var remark = tr.find("td").eq(6).find("input").val();
    var totalPrice = unitPrice * amount;
    totalPrice = totalPrice.toFixed(2);


    var pTr = $("<tr id='"+id+"'></tr>");
    var nameTd = $("<td>"+name+"</td>");
    var uniPriceTd = $("<td>"+unitPrice+"</td>");
    var amountTd = $("<td>"+amount+"</td>");
    var totalPriceTd = $("<td>"+totalPrice+"</td>");
    var isZp = "是";
    if(type != 1){
        isZp = "否";
    }
    var ipt = $("<input type='hidden' value='"+type+"'/>");
    var zPTd = $("<td>"+isZp+"</td>");
    zPTd.append(ipt);
    var remarkTd = $("<td>"+remark+"</td>");
    var opt = $("<button class='product-del-btn'>删除</button>");
    var optTd = $("<td></td>");
    optTd.append(opt);
    pTr.append(nameTd);
    pTr.append(amountTd);
    pTr.append(uniPriceTd);
    pTr.append(totalPriceTd);
    pTr.append(zPTd);
    pTr.append(remarkTd);
    pTr.append(optTd);

    tab.append(pTr);



};
/**
 * 显示产品
 */
Client.showProductList = function(data){
    var tab = $("#product-tab-body",Client.document).empty();
    var len = data.length;
    for(var i = 0; i < len; i++){
        var item = data[i];
        var num = i+1;
        var tr = $("<tr></tr>");
        tr.attr("id", item.id);
        var numTd = $("<td>" + num + "</td>");
        var productNameTd = $("<td>" + item.name + "</td>");
        var unitPriceTd = $("<td>" + item.unitPrice + "</td>");
        var div = "";
        if(item.type == 0){
            div = $('<input type="checkbox"  name="productType"/>');
        } else {
            div = $('<input type="checkbox"  name="productType" checked/>');
        }
        var isZpTd = $("<td></td>");
        isZpTd.append(div);
        //库存
        var amountTd = $("<td>"+item.amount+"</td>");
        //要添加的数量
        var count = $('<input type="number" style="width: 60px;" onkeyup="AckTool.formValidator.number(this)" onafterpaste="AckTool.formValidator.number(this)">');
        var countTd = $("<td></td>");
        countTd.append(count);
        var remark = $("<input type='text' name='remark'/>");
        var remarkTd = $("<td></td>");
        remarkTd.append(remark);
        var btn = $("<button class='product-add-btn'>添加</button>");
        var option = $("<td></td>");
        option.append(btn);

        tr.append(numTd);
        tr.append(productNameTd);
        tr.append(unitPriceTd);
        tr.append(isZpTd);
        tr.append(amountTd);
        tr.append(countTd);
        tr.append(remarkTd);
        tr.append(option);
        tab.append(tr);

    }

};
Client.productList = function(pageNo){
    var url = "/product/page";
    var data = {};
    data.currentPage = pageNo;
    data.name = $("#productName",Client.document).val();
    var option = Product.config();
    AckTool.postReq(data,url,function(obj){
        if( obj ){
            var result =  obj.result;
            Client.showProductList(result);
        }
    });
};
/**
 * 显示产品
 */
Client.showProduct = function(){
    var data = {};
    var productName = $("#productName",Client.document).val();
    data.count = 5;
    data.name = productName;
    var url = "/product/page";

    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Client.productList};
            Client.showProductList(obj.result);
        }
    });
};

/**trade ui
 *@param id
 */
Client.tradeUI = function(id ,name, coin){
    var url = "/client/trade/ui";
    var data = {};
    data.id = id;
    data.name = name;
    Client.modal.open(url,data,function(){
        $("#clientId",Client.document).val(id);
        $("#name",Client.document).val(name);
        $("#accountCoin",Client.document).val(coin);
        //显示物流
        Client.showLogistics(id);
        //显示产品
        Client.showProduct();
    });
};

Client.trade = function(clientId){
    var url = "/client/trade";
    var trade = {};
    //客户id
    trade.clientId = clientId;
    trade.remark = $("#remark",Client.document).val();
    //物流
    var logistics = {};
    var logisticsId = $("#logisticsId",Client.document).val();
    logistics.id = logisticsId;
    if(!logisticsId){
        alert("物流信息不能为空,请给客添加物流信息。");
        return;
    }
    //产品明细
    var tradeItems = [];
    var tab = $("#trade-product-tab-body",Client.document);
    var trs = tab.find("tr");
    var len = trs.length;
    if(len == 0){
        return ;
    }
    //所以产品金额要小于账户余额
    var totalMoney = 0;
    var accountCoin = $("#accountCoin",Client.document).val();

    for(var i=0; i < len; i++){
        var tr = $(trs[i]);
        var tds = tr.find("td");
        //产品id
        var item = {};
        item.productId = tr.attr("id");
        //单价
        item.unitPrice = tds.eq(2).text();
        //数量
        item.amount = tds.eq(1).text();
        //总价
        item.totalPrice = tds.eq(3).text();
        totalMoney = parseFloat(item.totalPrice) + totalMoney;
        //是否是赠品
        item.type = tr.find("input").val();
        //备注
        item.remark = tds.eq(5).text();
        tradeItems.push(item);
    }
    if(totalMoney > accountCoin){
        alert("账户余额不足，请联系客户打款充账。");
        return;
    }
    trade.logistics = logistics;
    trade.tradeItems = tradeItems;
    var data = JSON.stringify(trade);
    AckTool.postReqJsonType(data, url, function(obj) {
        if(obj){
            alert("添加成功");
            //关闭modal
            Client.modal.close();

            // 跳转交易单
            /*var sellMem = $("#sell-mem", Client.document);
            sellMem.click();
            console.log(sellMem.html());*/
        }
    });


};

/**
 * 绑定事件
 * */
Client.bind = function() {
    var ackModal = $("#ack-modal", Client.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        Client.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Client.del(id);
    });

    //销售
    $("#tab-body").on("click",".ack-simple-btn-client-trade",function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        var name = tr.find("td").eq(1).text();
        var coin = tr.find("td").eq(2).text();
        Client.tradeUI(id, name, coin);
    });

    //充值页面
    $("#tab-body").on("click",".ack-simple-btn-client-recharge",function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("id");
        var name = tr.find("td").eq(1).text();
        Client.rechargeUI(id, name);
    });
    //充值操作
    ackModal.on("click",".ack-recharge-save-btn", function(){
        var tr = $(this).parents("tr");
        var id = tr.attr("clientId");
        Client.recharge(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Client.document);
        var flag = fp.val();
        Client.eidt(flag);
    });

    //创建订单
    ackModal.on("click",".ack-trade-save-btn", function(){
        var id = $("#clientId", Client.document).val();
        Client.trade(id);
    });

    //查询产品
    ackModal.on("click","#product-find", function(){
        Client.showProduct();
    });
    //添加商品到列表
    ackModal.on("click",".product-add-btn", function(){
        var tr = $(this).parents("tr");
        var tab = $("#trade-product-tab-body",Client.document);
        Client.addProduct(tab,tr);
    });

    //商品列表删除
    ackModal.on("click",".product-del-btn", function(){
        var tr = $(this).parents("tr");
        tr.remove();
    });



};

Client.init = function (){
    Client.showList();
    Client.bind();
};


