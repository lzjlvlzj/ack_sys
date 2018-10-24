/**
 * 品牌js
 */
var Brand = window.Brand || {};

Brand.modal = parent.AckSystem.modal;

Brand.document = window.parent.document || window.document;

Brand.config = function(){
    var tb = $("#tab-body");
    var option = {};
    option.tab = tb;
    option.excludeFileds = [];
    option.getOneTr = Brand.getOneTr;
    return option;
}

Brand.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //品牌名称
    var BrandName = $("<td>"+data.name+"</td>");
    tr.append(BrandName);
    //品牌地址
    var address = $("<td>"+data.address+"</td>");
    tr.append(address);
    //品牌手机
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
Brand.showList = function() {
    //主要option在AckTool.table里面有默认值
    var option = Brand.config();
    var data = {};
    var url = "/brand/page";
    AckTool.postReq(data,url,function(obj) {
        if (obj) {
            option.data = obj.result;
            var conf = {totalPage:obj.totalPage, pageNumSize : 5, callback : Brand.list};
            $("#page").paginator(conf);
            AckTool.table.show(option);

        }
    });
}

Brand.list = function(pageNo){
    var url = "/brand/page";
    var data = {};
    data.currentPage = pageNo;
    data.loginName = $("#loginName").val();
    var option = Brand.config();
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
Brand.eidtUI = function(id) {

    var url = "";
    var data = {};
    data.reqData = {};
    if(id){
        url = "/brand/edit/ui/"+id;
        var BrandDataUrl = "/brand/id/" + id;
        //这里需要有个请求回显数据
        Brand.modal.open(url,data,function(){
            AckTool.postReq({},BrandDataUrl,function(obj){
                $("#optionFlag",Brand.document).val("1");
                $("#id",Brand.document).val(obj.id);
                $("#name",Brand.document).val(obj.name);
                $("#address",Brand.document).val(obj.address);
                $("#phone",Brand.document).val(obj.phone);
                $("#remark",Brand.document).val(obj.remark);
            });
        });
    } else {
        url = "/brand/add/ui";
        Brand.modal.open(url,data,function(){
            $("#optionFlag",Brand.document).val("0");
        });
    }
};

/**
 * 编辑操作
 */

Brand.eidt = function(flag) {
    var url = "";
    //添加
    if("0" == flag){
        url = "/brand/add"
    }
    if("1" == flag){
        url = "/brand/edit"
    }

    var data = $("#ack-add-form", Brand.document).serialize();
    AckTool.postReq(data, url, function(obj) {
        if (obj.code >= 1) {
            // 关闭modal
            Brand.modal.close();
            // 刷新当前页面
            Brand.showList();

        } else if(obj.code == 0){
            AckTool.formValidator.validate("#ack-add-form", Brand.document, obj.message);
        }else {
            alert("系统错误");
            // 关闭modal
            Brand.modal.close();
        }

    });

};
/**
 * 删除
 *
 * */
Brand.del = function(id){
    var url = "/brand/del/"+id;
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
                Brand.modal.close();
                //刷新当前页面
                Brand.showList();
            } else {
                alert("系统错误");
                //关闭modal
                Brand.modal.close();
            }

        });
    };
    var modal = this.modal.modalTemplate(option);
    modal.modal('show');

}

/**
 * 绑定事件
 * */
Brand.bind = function() {
    var ackModal = $("#ack-modal", Brand.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var id = $(this).parents("tr").attr("id");
        Brand.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Brand.del(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Brand.document);
        var flag = fp.val();
        Brand.eidt(flag);
    });
}


Brand.init = function (){
    Brand.showList();
    Brand.bind();
}


