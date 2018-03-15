//jquery扩展
$.extend({
    cookie : function(name, val) {
        if (!val) {
            var a = document.cookie.match(RegExp("(^|\s*)" + name + "=([^;]*)(;|$)"));
            return a ? decodeURIComponent(a[2]) : null;
        } else {
            //document.cookie = name + "=" + escape(val);
            document.cookie = name + "=" + encodeURI(val);
        }
    },
    removeCookie : function(name) {
        var expires = new Date();
        expires.setTime(expires.getTime() - 1);
        document.cookie = name + "=;expires=" + expires.toGMTString();
    }
});

// ------------------------------login.html-----------------------------------------

//读取Cookie直接登录
$(function (){

    var steamid = $.cookie("steamid");
    var vanityurl = $.cookie("vanityurl");

    if (steamid != null){
        $.ajax({
            type : "get",
            url : "/steamidcheck",
            data : "steamid=" + steamid,
            success : function (result) {

                $('#steamid').val(steamid);
                var btn = $('#steamid_btn');
                var form = $('#steamid_form');
                var remember = $('#steamid_remember');
                checkout(result, form, btn, remember);
            },
            error : function (jqXHR, textStatus, errorMsg) {
                alert("哎呀!");
                alert(jqXHR.status);
            }
        })
    }
    else if (vanityurl != null){
        $.ajax({
            type : "get",
            url : "/steamidcheck",
            data : "vanityurl=" + vanityurl,
            success : function (result) {
                $('#vanityurl').val(vanityurl);
                var form = $('#vanityurl_form');
                var btn = $('#vanityurl_btn');
                var remember = $('#vanityurl_remember');
                checkout(result, form, btn, remember);
            },
            error : function (jqXHR, textStatus, errorMsg) {
                alert("哎呀!");
                alert(jqXHR.status);
            }
        })
    }

});

//无Cookie则输入信息登录
$(document).ready(function () {

    //通过steamid64登录的验证
    $('#steamid_btn').click(function () {
        var steamid = $('#steamid').val();
        var regex = /\d{17}/;
        if (regex.test(steamid)){
            var loading = $("<div class=\"col-sm-offset-4 col-sm-8\" style='margin-top: 20px;' id='loadingmsg'>加载中...</div>");
            $('#steamid_btn').parent().parent().append(loading);
            $.ajax({
                type : "get",
                url : "/steamidcheck",
                data : "steamid=" + steamid,
                success : function (result) {
                    $('#loadingmsg').remove();
                    var btn = $('#steamid_btn');
                    var form = $('#steamid_form');
                    var remember = $('#steamid_remember');
                    checkout(result, form, btn, remember);
                },
                error : function (jqXHR, textStatus, errorMsg) {
                    alert("哎呀!");
                    alert(jqXHR.status);
                }
            })
        }
        else{
            alert("哎呀！输入有误！");
        }
    })

    //通过vanityurl登录的验证
    $('#vanityurl_btn').click(function () {
        var vanityurl = $('#vanityurl').val();

        var regex = /\S+/;
        if (regex.test(vanityurl)){
            var loading = $("<div class=\"col-sm-offset-4 col-sm-8\" style='margin-top: 20px;' id='loadingmsg'>加载中...</div>");
            $('#vanityurl_btn').parent().parent().append(loading);
            $.ajax({
                type : "get",
                url : "/steamidcheck",
                data : "vanityurl=" + vanityurl,
                success : function (result) {
                    $('#loadingmsg').remove();
                    var form = $('#vanityurl_form');
                    var btn = $('#vanityurl_btn');
                    var remember = $('#vanityurl_remember');
                    checkout(result, form, btn, remember);
                },
                error : function (jqXHR, textStatus, errorMsg) {
                    alert("哎呀!");
                    alert(jqXHR.status);
                }
            })
        }
        else {
            alert("哎呀！输入有误！");
        }
    })
});

function checkout(player, form, btn, remember) {

    var visible_status = player.communityvisibilitystate;
    if (visible_status==1){
        // 该用户资料私密
        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料私密</div>");
    }
    else if (visible_status==2){
        // 该用户资料仅好友可见
        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料仅好友可见</div>");
    }
    else if (visible_status==3){
        //验证通过，处理cookie，提交表单
        if (remember.prop("checked")){
            if (form.prop("id")=="steamid_form"){
                var steamid = $('#steamid').val();
                $.cookie("steamid", steamid);
            }
            else {
                var vanityurl = $('#vanityurl').val();
                $.cookie("vanityurl", vanityurl);
            }
        }
        var alarmmsg = $("<div class=\"col-sm-offset-4 col-sm-8\" style='margin-top: 20px;' id='loadingmsg'>更新个人数据中，请稍候...</div>")
        btn.parent().parent().append(alarmmsg);
        form.submit();
    }
    else if (visible_status==-1) {
        // 该用户不存在
        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户不存在</div>");
    }
    else{
        // 连接失败
        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>连接失败</div>");
    }
    btn.parent().parent().append(alarmmsg);
}
