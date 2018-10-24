var UserPass = window.UserPass || {};

UserPass.modal = parent.AckSystem.modal;

UserPass.document = window.parent.document || window.document;

UserPass.init = function() {
    //绑定事件
    UserPass.bind();

};


UserPass.reset = function(){
    var url = "/user/password/update";
    var data = {};
    var msgDiv = $("#form-validate-msg");
    var msg = "";
    var oldPass = $("#oldPassword").val();
    var pass1 = $("#password1").val();
    var pass2 = $("#password2").val();
    if(!oldPass){
        msg = "老密码不能为空";
    }
    if(!pass1 || !pass2){
        msg = "新密码不能为空";
    }
    if(pass1 != pass2){
        msg = "两次输入的密码不一致";
    }
    if(msg){
        msgDiv.html(msg);
        msgDiv.show();
        return;
    }
    data.oldPassword = oldPass;
    data.password = pass1;

    AckTool.postReq(data, url, function(obj) {
        if (obj == 1) {
           alert("修改成功");
        } else if(obj == 2){
            alert("修改失败,请检查老密码输入是否正确");
        }else {
            alert("修改失败");
        }

    });
};

/**
 * 绑定事件
 * */
UserPass.bind = function() {
    var form = $("#ack-user-pass-reset-form");
    $("#user-pass-rest-btn").unbind().click(function(){
        UserPass.reset();
    });

    form.on("keyup","input[type='password']", function(){
        var msgDiv = $("#form-validate-msg");
        msgDiv.empty();
        msgDiv.hide()
    });


};
