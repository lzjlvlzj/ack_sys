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
}

Client.getOneTr = function(n, data, option) {
    var tr = $("<tr></tr>");
    tr.attr("id",data.id);
    //序号
    var num = $("<td class='center'>" + n + "</td>");
    tr.append(num);
    //客户名称
    var ClientName = $("<td>"+data.name+"</td>");
    tr.append(ClientName);
    //客户地址
    var address = $("<td>"+data.address+"</td>");
    tr.append(address);
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
    optionTd.append(AckTool.optionButton.simpleOption);
    tr.append(optionTd);
    return tr;
}

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
}

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
}
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
}
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
}

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

}
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

}

/**
 * 绑定事件
 * */
Client.bind = function() {
    var ackModal = $("#ack-modal", Client.document);
    //修改
    $("#tab-body").on("click",".ack-simple-btn-edit",function(){
        var id = $(this).parents("tr").attr("id");
        Client.eidtUI(id);
    });
    //删除
    $("#tab-body").on("click",".ack-simple-btn-del",function(){
        var id = $(this).parents("tr").attr("id");
        Client.del(id);
    });
    //保存
    ackModal.on("click",".ack-modal-save-btn", function(){
        var fp = $("#optionFlag", Client.document);
        var flag = fp.val();
        Client.eidt(flag);
    });
}

Client.init = function (){
    Client.showList();
    Client.bind();
}


