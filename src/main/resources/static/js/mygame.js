var steamid;
var appid;


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

// ------------------------------games.html-----------------------------------------

//登出处理
$('#log_out').click(function () {
    //alert("ok")
    if ($.cookie("steamid") != null){
        $.removeCookie("steamid");
    }
    if ($.cookie("vanityurl") != null){
        $.removeCookie("vanityurl");
    }
})




$(function () {
    //alert("success!");
    steamid = $('#steamid').val();
    appid = $('#appid').val();
    $.ajax({
        type : "get",
        url : "/loadgamepic",
        data : "steamid=" + steamid + "&appid=" + appid,
        success : function (result) {

            var piclist = result[0];
            var pic_count = result[1];

            loadgamepic(piclist, pic_count);
        },
        error : function () {
            alert("shit");
        }
    })

    $.ajax({
        type : "get",
        url : "/loadfriends",
        data : "steamid=" + steamid + "&appid=" + appid,
        success : function (result) {
            var friend_players = result[0];
            var play2week = result[1];
            var playforever = result[2];
            var achieved_count = result[3];
            var ach_all = result[4];
            var count = result[5];

            //alert(friend_players[0].steamid);
            alert(count);

            //loadfriends(friend_players,play2week,playforever,achieved_count,ach_all,count);
        },
        error : function () {
            alert("fuck");
        }
    })

})

function loadgamepic(piclist, pic_count) {

    var olpart = '';
    var divpart = '';
    var all = '';
    if (pic_count == 0){

    }
    else if (pic_count == 1){
        olpart += '<ol class="carousel-indicators"><li data-slide-to="0" data-target="#carousel-161390"></li></ol>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="/image/alt.jpg" src="'+piclist[0]+'" /><div class="carousel-caption"><h4></h4></div></div></div>';
    }
    else{
        olpart += '<ol class="carousel-indicators"><li class="active" data-slide-to="0" data-target="#carousel-161390"></li>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="/image/alt.jpg" src="'+piclist[0]+'" /><div class="carousel-caption"><h4></h4></div></div>';
        for (var i = 1; i < pic_count; i++){
            olpart += '<li data-slide-to="'+i+'" data-target="#carousel-161390"></li>';
            divpart += '<div class="item"><img alt="/image/alt.jpg" src="'+piclist[i]+'" /><div class="carousel-caption"><h4></h4></div></div>';
        }
    }
    olpart += '</ol>';
    divpart += '</div>';
    var apart = '<a class="left carousel-control" href="#carousel-161390" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-161390" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>';
    all += olpart + divpart + apart;
    $('#carousel-161390').empty();
    $('#carousel-161390').html(all);
}

function loadfriends(players,play2week,playforever,ach_counts,ach_all,count) {

    var tr = '';
    if (count == 0){
        alert("really?")
    }
    else if (count < 20){
        for (var i = 0; i < count; i++){
            tr += '<tr><td>'+i+'</td><td><div class="friend_avatar"><img src="'+players[i].avatar+'" alt=""/><span>'+players[i].personaname+'</span></div></td>';
            tr += '<td>'+play2week+'h/ '+playforever+'h'+'</td><td>'+ach_counts[i]+'/'+ach_all+'</td></tr>';
        }
    }
    else{
        for (var i = 0; i < 20; i++){
            tr += '<tr><td>'+i+'</td><td><div class="friend_avatar"><img src="'+players[i].avatar+'" alt=""/><span>'+players[i].personaname+'</span></div></td>';
            tr += '<td>'+play2week+'h/ '+playforever+'h'+'</td><td>'+ach_counts[i]+'/'+ach_all+'</td></tr>';
        }
    }
    $('#friendsplay').empty();
    $('#friendsplay').html(tr);
}
