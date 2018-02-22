var ownedgames;
var gameCount;
var totalPages;
var currentPage = 1;


$(function () {
    //页面加载后马上分页加载游戏库
    var steamid = $('#steamid').val();
    //alert(steamid);


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
            clickthing();
        },
        error : function () {
            alert("oh my dog!")
        }
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

    if (currentPage == totalPages){
        var max = gameCount - (totalPages-1)*12;
        for (var i = 0; i < max; i++){
            gameblock += '<div class="col-md-3"><div class="thumbnail"><img alt="300x200" src="'+ownedgames[12*(currentPage-1)+i].imgLogoUrl+'" title="'+ownedgames[12*(currentPage-1)+i].appid+'" />';
            gameblock += '<div class="caption"><marquee behavior="alternate" direction="left" scrollamount="2"><p>'+ownedgames[12*(currentPage-1)+i].appname+'</p></marquee>';
            gameblock += '<p>'+ownedgames[12*(currentPage-1)+i].playtime2week+'h/'+ownedgames[12*(currentPage-1)+i].playtimeForever+'h<span class="pull-right">54%</span></p></div></div></div></div>';
        }
    }
    else{
        for (var i = 0; i < 12; i++){
            gameblock += '<div class="col-md-3"><div class="thumbnail"><img alt="300x200" src="'+ownedgames[12*(currentPage-1)+i].imgLogoUrl+'" title="'+ownedgames[12*(currentPage-1)+i].appid+'" />';
            gameblock += '<div class="caption"><marquee behavior="alternate" direction="left" scrollamount="2"><p>'+ownedgames[12*(currentPage-1)+i].appname+'</p></marquee>';
            gameblock += '<p>'+ownedgames[12*(currentPage-1)+i].playtime2week+'h/'+ownedgames[12*(currentPage-1)+i].playtimeForever+'h<span class="pull-right">54%</span></p></div></div></div></div>';
        }
    }
    $('#game_show div').remove();
    $('#game_show').html(gameblock);
}

//加载分页栏
function pagebar_load(currentPage, totalPage) {
    var pagebar = '';
    pagebar += '<ul class="pagination"><li id="the_first_page"><a href="#0"><i class="fas fa-angle-double-left"></i></a></li><li id="prev_page"><a href="#0"><i class="fas fa-angle-left"></i></a></li>';
    if (totalPage < 7){
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
    //$('#page_bar ul').remove();
    $('#page_bar').html(pagebar);
    clickthing();
}

//页码信息加载
function loadpagemsg(currnetPage,totalPages) {

    //$('#page_msg').remove();
    var page_msg = '';
    page_msg += '<p>当前第 '+currentPage+' 页，共 '+totalPages+'页</p>';
    $('#page_msg').html(page_msg);
}