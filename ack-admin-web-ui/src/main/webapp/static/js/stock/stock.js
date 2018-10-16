/**
 * 仓储js
 */
var Stock = window.Stock || {};

Stock.modal = parent.AckSystem.modal;

Stock.document = window.parent.document || window.document;

Stock.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Stock.getOneTr;
    return option;
}

Stock.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //品牌名称
    var brandName = $("<td>"+data.brand.name+"</td>");
    tr.append(brandName);
    //产品名称
    var productName = $("<td>"+data.product.name+"</td>");
    tr.append(productName);
    //产品数量
    var amount = $("<td>"+data.amount+"</td>");
    tr.append(amount);
    //质检员
    var inspectorName = data.inspector.surname + data.inspector.name;
    var inspector = $("<td>"+inspectorName+"</td>");
    tr.append(inspector);
    //操作员
    var operatorName = data.operator.surname + data.operator.name;
    var operator = $("<td>"+operatorName+"</td>");
    tr.append(operator);
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
Stock.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Stock.config();
    var data = {};
    var url = "/stock/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Stock.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
}

Stock.list = function(pageNo){
    var url = "/stock/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Stock.config();
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
 * @param flag 0 : 导航仓储 , 1 : 功能仓储
 * */
Stock.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/stock/edit/ui/"+id;
        var StockDataUrl = "/stock/id/" + id;
        //这里需要有个请求回显数据
        Stock.modal.open(url,data,function(){
            AckTool.postReq({},StockDataUrl,function(obj){
                $("#optionFlag",Stock.document).val("1");
                $("#id",Stock.document).val(obj.id);
                $("#name",Stock.document).val(obj.name);
                $("#address",Stock.document).val(obj.address);
                $("#phone",Stock.document).val(obj.phone);
                $("#remark",Stock.document).val(obj.remark);
            });
        });
    } else {
        url = "/stock/add/ui";
        Stock.modal.open(url,data,function(){
            $("#optionFlag",Stock.document).val("0");
        });
    }
}

/**
 * 编辑操作
 */

Stock.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/stock/add"
    }
    if("1" == flag){
        url = "/stock/edit"
    }

    var data = $("#ack-add-form", Stock.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Stock.modal.close();
            // 刷新当前页面
            Stock.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Stock.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Stock.modal.close();
        }

    });

}
/**
 * 删除
 *
 * */
Stock.del = function(id){
    var url = "/stock/del/"+id;
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
                Stock.modal.close();
                //刷新当前页面
                Stock.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Stock.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

}

/**
 * 绑定事件
 * */
Stock.bind = function() {
    var ackModal = $("#ack-modal", Stock.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var id = $(this).parents("tr").attr("id");
        Stock.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Stock.del(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Stock.document);
        var flag = fp.val();
        Stock.eidt(flag);
    });
}


Stock.init = function (){
    Stock.showList();
    Stock.bind();
}


