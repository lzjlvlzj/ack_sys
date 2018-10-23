/**
 * 退货js
 */
var Returns = window.Returns || {};

Returns.modal = parent.AckSystem.modal;

Returns.document = window.parent.document || window.document;

Returns.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Returns.getOneTr;
    return option;
}

Returns.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //客户名称
    var ReturnsName = $("<td>"+data.client.name+"</td>");
    tr.append(ReturnsName);
    //电话
    var clientPhone = $("<td>"+data.client.phone+"</td>");
    tr.append(clientPhone);
    //产品编码
    var productCode = $("<td>"+data.product.code+"</td>");
    tr.append(productCode);
    //产品编码
    var productName = $("<td>"+data.product.name+"</td>");
    tr.append(productName);
    //退货数量
    var productAmount = $("<td>"+data.amount+"</td>");
    tr.append(productAmount);
    //美导老师
    var seller = data.seller.surname + data.seller.name;
    var sellerName = $("<td>"+seller+"</td>");
    tr.append(sellerName);
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
    opt.prefix = "trademark";
    var buttons = AckTool.optionButton.getTrAuthButtons(opt);
    optionTd.append(buttons);
    tr.append(optionTd);
    return tr;
}

/**
 *
 * 初始化显示
 * */
Returns.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Returns.config();
    var data = {};
    var url = "/returns/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Returns.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
}

Returns.list = function(pageNo){
    var url = "/returns/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Returns.config();
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
 * @param flag 0 : 导航退货 , 1 : 功能退货
 * */
Returns.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/returns/edit/ui/"+id;
        var ReturnsDataUrl = "/returns/id/" + id;
        //这里需要有个请求回显数据
        Returns.modal.open(url,data,function(){
            AckTool.postReq({},ReturnsDataUrl,function(obj){
                $("#optionFlag",Returns.document).val("1");
                $("#id",Returns.document).val(obj.id);
                $("#coin",Returns.document).val(obj.coin);
                $("#oldAmount",Returns.document).val(obj.amount);
                $("#amount",Returns.document).val(obj.amount);
                $("#productId",Returns.document).val(obj.productId);
                $("#productName",Returns.document).val(obj.product.name);
                $("#productCode",Returns.document).val(obj.product.code);
                $("#clientId",Returns.document).val(obj.clientId);
                $("#clientPhone",Returns.document).val(obj.client.phone);
                $("#clientName",Returns.document).val(obj.client.name);
                $("#remark",Returns.document).val(obj.remark);
            });
        });
    } else {
        url = "/returns/add/ui";
        Returns.modal.open(url,data,function(){
            $("#optionFlag",Returns.document).val("0");
        });
    }
}

/**
 * 编辑操作
 */

Returns.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/returns/add"
    }
    if("1" == flag){
        url = "/returns/edit"
    }

    var data = $("#ack-add-form", Returns.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Returns.modal.close();
            // 刷新当前页面
            Returns.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Returns.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Returns.modal.close();
        }

    });

}
/**
 * 删除
 *
 * */
Returns.del = function(id){
    var url = "/returns/del/"+id;
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
                Returns.modal.close();
                //刷新当前页面
                Returns.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Returns.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

};

/**
 * 显示用户
 * @param val
 */
Returns.showClient = function(val){
    if(!val){
        return;
    }
    var url = "/returns/client/"+val;
    var data = {};
    AckTool.postReq(data, url, function(obj) {
        if(obj){
           $("#clientId", Returns.document).val(obj.id);
           $("#clientName", Returns.document).val(obj.name);
        }

    });
};

/**
 * 显示商品
 * @param val
 */
Returns.showProduct = function(val){
    if(!val){
        return;
    }
    var url = "/returns/product/"+val;
    var data = {};
    AckTool.postReq(data, url, function(obj) {
        if(obj){
            $("#productId", Returns.document).val(obj.id);
            $("#productName", Returns.document).val(obj.name);
        }

    });
};

/**
 * 绑定事件
 * */
Returns.bind = function() {
    var ackModal = $("#ack-modal", Returns.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var id = $(this).parents("tr").attr("id");
        Returns.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Returns.del(id);
    });
    //查询客户
    ackModal.on("input","#clientPhone", function(){
        var val = $(this).val();
        Returns.showClient(val);
    });
    //查询产品
    ackModal.on("input","#productCode", function(){
        var val = $(this).val();
        Returns.showProduct(val);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Returns.document);
        var flag = fp.val();
        Returns.eidt(flag);
    });
}


Returns.init = function (){
    Returns.showList();
    Returns.bind();
}


