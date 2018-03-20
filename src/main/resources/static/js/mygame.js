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
            alert("orz");
        }
    })

    $.ajax({
        type : "get",
        url : "/loadfriends",
        data : "steamid=" + steamid + "&appid=" + appid,
        success : function (result) {
            var gameFriends = result[0];
            var count = result[1];

            loadfriends(gameFriends,count);
        },
        error : function () {
            alert("orzorz");
        }
    })

})

function loadgamepic(piclist, pic_count) {

    var olpart = '';
    var divpart = '';
    var all = '';
    if (pic_count == 0){
        olpart += '<ol class="carousel-indicators"><li data-slide-to="0" data-target="#carousel-161390"></li></ol>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="/image/null.png" src="/image/null.png" /><div class="carousel-caption"><h4></h4></div></div></div>';
    }
    else if (pic_count == 1){
        olpart += '<ol class="carousel-indicators"><li data-slide-to="0" data-target="#carousel-161390"></li></ol>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="/image/null.png" src="'+piclist[0]+'" /><div class="carousel-caption"><h4></h4></div></div></div>';
    }
    else{
        olpart += '<ol class="carousel-indicators"><li class="active" data-slide-to="0" data-target="#carousel-161390"></li>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="/image/null.png" src="'+piclist[0]+'" /><div class="carousel-caption"><h4></h4></div></div>';
        for (var i = 1; i < pic_count; i++){
            olpart += '<li data-slide-to="'+i+'" data-target="#carousel-161390"></li>';
            divpart += '<div class="item"><img alt="/image/null.png" src="'+piclist[i]+'" /><div class="carousel-caption"><h4></h4></div></div>';
        }
    }
    olpart += '</ol>';
    divpart += '</div>';
    var apart = '<a class="left carousel-control" href="#carousel-161390" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-161390" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>';
    all += olpart + divpart + apart;
    $('#carousel-161390').empty();
    $('#carousel-161390').html(all);
}

function loadfriends(gameFriends,count) {

    var tr = '';
    if (count == 0){
        alert("really?")
    }
    else if (count < 10){
        for (var i = 0; i < count; i++){
            tr += '<tr><td>'+(i+1)+'</td><td><div class="friend_avatar"><img src="'+gameFriends[i].avatar+'" alt=""/></div></td><td>'+gameFriends[i].personaname+'</td>';
            tr += '<td>'+gameFriends[i].play2week+'h / '+gameFriends[i].playforever+'h'+'</td><td>'+gameFriends[i].achieved_count+'/'+gameFriends[i].achievement_all+'</td></tr>';
        }
    }
    else{
        for (var i = 0; i < 10; i++){
            tr += '<tr><td>'+(i+1)+'</td><td><div class="friend_avatar"><img src="'+gameFriends[i].avatar+'" alt=""/></div></td><td>'+gameFriends[i].personaname+'</td>';
            tr += '<td>'+gameFriends[i].play2week+'h / '+gameFriends[i].playforever+'h'+'</td><td>'+gameFriends[i].achieved_count+'/'+gameFriends[i].achievement_all+'</td></tr>';        }
    }
    $('#friendsplay').empty();
    $('#friendsplay').html(tr);
}
