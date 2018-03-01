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


var steamid;
var ownedgames;
var gameCount;
var totalPages;
var currentPage = 1;

var apps;
var count;


$(function () {
    //页面加载后马上分页加载游戏库
    steamid = $('#steamid').val();
    $.ajax({
        type : "get",
        url : "/loadmylib",
        data : "steamid=" + steamid,
        success : function (result) {
            ownedgames = result[0];
            gameCount = result[1];
            totalPages = result[2];
            //alert("totalPages="+totalPages);
            gameblock_load(ownedgames,gameCount,currentPage,totalPages);
            pagebar_load(currentPage,totalPages);
            loadpagemsg(currentPage,totalPages);
            clickthing();
        },
        error : function () {
            alert("oh my dog!")
        }
    })

    $.ajax({
        type : "get",
        url : "/loadmyfavorite",
        data : "steamid=" + steamid,
        success : function (result) {
            apps = result[0];
            count = result[1];
            //alert("count="+count);
            loadmyfavorite(apps,count);
        }
    })

    //更新好友游戏
    $.ajax({
        type : "get",
        url : "/updateFriendsGame_bg",
        data : "steamid=" + steamid,
    })

})

//绑定点击事件
function clickthing() {
    $('.pagination li').click(function () {

        var pageid = $(this).attr("id");
        if (pageid != null){
            if (pageid == "the_first_page"){
                currentPage = 1;
                gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                pagebar_load(currentPage,totalPages);
            }
            else if (pageid == "prev_page"){
                if (currentPage != 1){
                    currentPage = currentPage - 1;
                    gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                    pagebar_load(currentPage,totalPages);
                }
            }
            else if (pageid == "next_page"){
                if (currentPage != totalPages){
                    currentPage = currentPage*1 + 1;
                    gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                    pagebar_load(currentPage,totalPages);
                }
            }
            else if (pageid == "the_last_page"){
                currentPage = totalPages;
                gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                pagebar_load(currentPage,totalPages);
            }
            else{
                currentPage = pageid;
                gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                pagebar_load(currentPage,totalPages);
            }
        }
        loadpagemsg(currentPage,totalPages);
    })
}
//加载各个游戏div
function gameblock_load(ownedgames, gameCount, currentPage, totalPages) {

    var gameblock = '';

    if (totalPages > 0){
        if (currentPage == totalPages){
            var max = gameCount - (totalPages-1)*12;
            for (var i = 0; i < max; i++){
                gameblock += '<div class="col-md-3"><div class="thumbnail"><a href="/gameController?steamid='+steamid+'&appid='+ownedgames[12*(currentPage-1)+i].appid+'"><img alt="300x200" src="'+ownedgames[12*(currentPage-1)+i].imgLogoUrl+'" title="'+ownedgames[12*(currentPage-1)+i].appid+'" /></a>';
                gameblock += '<div class="caption"><marquee behavior="alternate" direction="left" scrollamount="2"><p>'+ownedgames[12*(currentPage-1)+i].appname+'</p></marquee>';
                gameblock += '<p>'+ownedgames[12*(currentPage-1)+i].playtime2week+'h/ '+ownedgames[12*(currentPage-1)+i].playtimeForever+'h<span class="pull-right">NO.'+(12*currentPage+i-11)+'</span></p></div></div></div></div>';
            }
        }
        else{
            for (var i = 0; i < 12; i++){
                gameblock += '<div class="col-md-3"><div class="thumbnail"><a href="/gameController?steamid='+steamid+'&appid='+ownedgames[12*(currentPage-1)+i].appid+'"><img alt="300x200" src="'+ownedgames[12*(currentPage-1)+i].imgLogoUrl+'" title="'+ownedgames[12*(currentPage-1)+i].appid+'" /></a>';
                gameblock += '<div class="caption"><marquee behavior="alternate" direction="left" scrollamount="2"><p>'+ownedgames[12*(currentPage-1)+i].appname+'</p></marquee>';
                gameblock += '<p>'+ownedgames[12*(currentPage-1)+i].playtime2week+'h/ '+ownedgames[12*(currentPage-1)+i].playtimeForever+'h<span class="pull-right">NO.'+(12*currentPage+i-11)+'</span></p></div></div></div></div>';
            }
        }

    }
    else{
        gameblock = '<p align="center">空空如也~</p>';
    }
    $('#game_show').empty();
    $('#game_show').html(gameblock);
}

//加载分页栏
function pagebar_load(currentPage, totalPage) {
    var pagebar = '';
    pagebar += '<ul class="pagination"><li id="the_first_page"><a href="#0"><i class="fas fa-angle-double-left"></i></a></li><li id="prev_page"><a href="#0"><i class="fas fa-angle-left"></i></a></li>';
    if (totalPage == 0){

    }
    else if (totalPage < 7){
        for (var i = 1; i <= totalPage; i++){
            pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
        }
    }
    else if (totalPage == 7){
        if (currentPage < 4){
            for (var i = 1; i <= (currentPage*1+2); i++){
                pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
            }
            pagebar += '<li><a href="#0">...</a></li>';
            pagebar += '<li id="'+totalPage+'"><a href="#0">'+totalPage+'</a></li>';
        }
        else if (currentPage == 4){
            for (var i = 1; i <= 7; i++){
                pagebar += '<li><a href="#0">'+i+'</a></li>';
            }
        }
        else if (currentPage > 4){
            pagebar += '<li id="1"><a href="#0">1</a></li>';
            for (var i = (currentPage*1-2); i <= 7; i++){
                pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
            }
        }
    }
    else{
        if (currentPage < 4){
            for (var i = 1; i <= 2+currentPage*1; i++){
                pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
            }
            pagebar += '<li><a href="#0">...</a></li>';
            pagebar += '<li id="'+totalPage+'"><a href="#0">'+totalPage+'</a></li>';
        }
        else if (currentPage == 4){
            for (var i = 1; i <= 6; i++){
                pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
            }
            pagebar += '<li><a href="#0">...</a></li>';
            pagebar += '<li id="'+totalPage+'"><a href="#0">'+totalPage+'</a></li>';
        }
        else if (currentPage > 4 && (currentPage*1+3) < totalPage){
            pagebar += '<li id="1"><a href="#0">1</a></li>';
            pagebar += '<li><a href="#0">...</a></li>';
            for (var i = (currentPage*1-2); i <= (currentPage*1+2); i++){
                pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
            }
            pagebar += '<li><a href="#0">...</a></li>';
            pagebar += '<li id="'+totalPage+'"><a href="#0">'+totalPage+'</a></li>';
        }
        else {
            pagebar += '<li id="1"><a href="#0">1</a></li>';
            pagebar += '<li><a href="#0">...</a></li>';
            for (var i = (currentPage*1-2); i <= totalPage; i++){
                pagebar += '<li id="'+i+'"><a href="#0">'+i+'</a></li>';
            }
        }
    }
    pagebar += '<li id="next_page"><a href="#0"><i class="fas fa-angle-right"></i></a></li><li id="the_last_page"><a href="#0"><i class="fas fa-angle-double-right"></i></a></li></ul>';
    $('#page_bar').html(pagebar);
    clickthing();
}

//页码信息加载
function loadpagemsg(currentPage,totalPages) {

    //$('#page_msg').remove();
    if (currentPage < 0){
        currentPage = 0;
    }
    if (currentPage > totalPages){
        currentPage = totalPages;
    }
    var page_msg = '';
    page_msg += '<p>当前第 '+currentPage+' 页，共 '+totalPages+'页</p>';
    $('#page_msg').html(page_msg);
}

//加载我最喜爱的游戏
function loadmyfavorite(apps,count) {

    var olpart = '';
    var divpart = '';
    var all = '';
    if (count == 0){
        alert("Go and buy a game!");
    }
    else if (count == 1){
        olpart += '<ol class="carousel-indicators"><li data-slide-to="0" data-target="#carousel-909456"></li></ol>';
        divpart += '<div class="carousel-inner"><div class="item"><img alt="" src="'+apps[0].pic2+'" /><div class="carousel-caption"><h4>'+apps[0].appname+'</h4></div></div></div>';
    }
    else if (count < 5){
        olpart += '<ol class="carousel-indicators"><li data-slide-to="0" data-target="#carousel-909456" class="active"></li>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="" src="'+apps[0].pic2+'" /><div class="carousel-caption"><h4>'+apps[0].appname+'</h4></div></div>';
        for (var i = 1; i < count; i++){
            olpart += '<li data-slide-to="'+i+'" data-target="#carousel-909456"></li>';
            divpart += '<div class="item"><img alt="" src="'+apps[i].pic2+'" /><div class="carousel-caption"><h4>'+apps[i].appname+'</h4></div></div>';
        }
        olpart += '</ol>';
        divpart += '</div>';
    }
    else{
        olpart += '<ol class="carousel-indicators"><li data-slide-to="0" data-target="#carousel-909456" class="active"></li>';
        divpart += '<div class="carousel-inner"><div class="item active"><img alt="" src="'+apps[0].pic2+'" /><div class="carousel-caption"><h4>'+apps[0].appname+'</h4></div></div>';
        for (var i = 1; i < 5; i++){
            olpart += '<li data-slide-to="'+i+'" data-target="#carousel-909456"></li>';
            divpart += '<div class="item"><img alt="" src="'+apps[i].pic2+'" /><div class="carousel-caption"><h4>'+apps[i].appname+'</h4></div></div>';
        }
        olpart += '</ol>';
        divpart += '</div>';
    }
    var apart = '<a class="left carousel-control" href="#carousel-909456" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a><a class="right carousel-control" href="#carousel-909456" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>';
    all = olpart + divpart + apart;
    $('#carousel-909456').empty();
    $('#carousel-909456').html(all);
}

//点击时加载游戏库
$('#my_game_lib').click(function () {
    steamid = $('#steamid').val();
    $.ajax({
        type : "get",
        url : "/loadmylib",
        data : "steamid=" + steamid,
        success : function (result) {
            ownedgames = result[0];
            gameCount = result[1];
            totalPages = result[2];
            //alert("totalPages="+totalPages);
            gameblock_load(ownedgames,gameCount,currentPage,totalPages);
            pagebar_load(currentPage,totalPages);
            loadpagemsg(currentPage,totalPages);
            clickthing();
        },
        error : function () {
            alert("oh my dog!")
        }
    })
})

//搜索游戏的ajax

$('#find').click(function () {
    steamid = $('#steamid').val();
    var keyword = $('#key_word').val();
    if (keyword != null){
        $.ajax({
            type : "get",
            url : "/searchmylib",
            data : "keyword=" + keyword + "&steamid=" + steamid,
            success: function (result) {
                ownedgames = result[0];
                gameCount = result[1];
                totalPages = result[2];
                if (gameCount > 0){
                    gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                    pagebar_load(currentPage,totalPages);
                    loadpagemsg(currentPage,totalPages);
                    clickthing();
                }
                else{
                    gameblock_load(ownedgames,gameCount,currentPage,totalPages);
                    pagebar_load(currentPage,totalPages);
                    loadpagemsg(currentPage,totalPages);
                }
            },
            error : function () {
                alert("oh my dog!")
            }
        })
    }
})