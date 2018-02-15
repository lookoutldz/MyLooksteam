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

                //$('#loadingmsg').remove();
                if (result==null || result==""){
                    var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>连接失败</div>");
                }
                else{

                    var players = $.parseJSON(result);
                    if (jQuery.isEmptyObject(players)) {
                        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户不存在</div>");
                    }
                    else{
                        var visible_status = players[0].communityvisibilitystate;
                        if (visible_status==1){
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料私密</div>");
                        }
                        else if (visible_status==2) {
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料仅好友可见</div>");
                        }
                        else if (visible_status==3){

                            $('#steamid_form').submit();
                        }
                        else {
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料不可见!状态码:"+visible_status+"</div>");
                        }
                    }
                }
                $('#steamid_btn').parent().parent().append(alarmmsg);
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

                //$('#loadingmsg').remove();
                if (result==null || result==""){
                    var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>连接失败</div>");
                }
                else{

                    var players = $.parseJSON(result);
                    if (jQuery.isEmptyObject(players)) {
                        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户不存在</div>");
                    }
                    else{
                        var visible_status = players[0].communityvisibilitystate;
                        if (visible_status==1){
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料私密</div>");
                        }
                        else if (visible_status==2) {
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料仅好友可见</div>");
                        }
                        else if (visible_status==3){

                            $('#vanityurl_form').submit();

                        }
                        else {
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料不可见!状态码:"+visible_status+"</div>");
                        }
                    }
                }
                $('#vanityurl_btn').parent().parent().append(alarmmsg);
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
                    if (result==null || result==""){
                        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>连接失败</div>");
                    }
                    else{
                        var players = $.parseJSON(result);
                        if (jQuery.isEmptyObject(players)) {
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户不存在</div>");
                        }
                        else{
                            var visible_status = players[0].communityvisibilitystate;
                            if (visible_status==1){
                                var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料私密</div>");
                            }
                            else if (visible_status==2) {
                                var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料仅好友可见</div>");
                            }
                            else if (visible_status==3){

                                if ($('#steamid_remember').prop("checked")){
                                    $.cookie("steamid",steamid);
                                }
                                $('#steamid_form').submit();
                            }
                            else {
                                var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料不可见!状态码:"+visible_status+"</div>");
                            }
                        }
                    }
                    $('#steamid_btn').parent().parent().append(alarmmsg);
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
                    if (result==null || result==""){
                        var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>连接失败</div>");
                    }
                    else{
                        var players = $.parseJSON(result);
                        if (jQuery.isEmptyObject(players)) {
                            var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户不存在</div>");
                        }
                        else{
                            var visible_status = players[0].communityvisibilitystate;
                            if (visible_status==1){
                                var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料私密</div>");
                            }
                            else if (visible_status==2) {
                                var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料仅好友可见</div>");
                            }
                            else if (visible_status==3){

                                if ($("#vanityurl_remember").prop("checked")){
                                    $.cookie("vanityurl", vanityurl);
                                }
                                $('#vanityurl_form').submit();
                            }
                            else {
                                var alarmmsg = $("<div style=\"margin-top: 50px;\" align='center' id=\"alarmmsg\" class=\"alert alert-warning\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>对不起，该用户资料不可见!状态码:"+visible_status+"</div>");
                            }
                        }
                    }
                    $('#vanityurl_btn').parent().parent().append(alarmmsg);
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

