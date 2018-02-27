package looko.looksteam.demo.ajax;

import looko.looksteam.demo.api.CheckVisibilityState;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameAjax {

    @Autowired
    OwnedgameService ownedgameService;
    @Autowired
    AppService appService;
    @Autowired
    FriendService friendService;
    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerachService playerachService;

    @RequestMapping("/loadgamepic")
    @ResponseBody
    public List<Object> loadGamePics(HttpServletRequest request){

        List<Object> list = new ArrayList<>();
//        String steamid = request.getParameter("steamid");
        int appid = Integer.parseInt(request.getParameter("appid"));
        App app = appService.searchByID(appid);
//        OwnedGame ownedGame = ownedgameService.getOwnedgame(steamid,appid);

        List<String> piclist = new ArrayList<>();
        if (app.getPic1() != null)
            piclist.add(app.getPic1());
        if (app.getPic2() != null)
            piclist.add(app.getPic2());
        if (app.getPic3() != null)
            piclist.add(app.getPic3());
        if (app.getPic4() != null)
            piclist.add(app.getPic4());
        if ((app.getPic5() != null))
            piclist.add(app.getPic5());
        int pic_count = piclist.size();
//        String appname = app.getAppname();
//        int playtime2week = ownedGame.getPlaytime2week();
//        int playtimeforever = ownedGame.getPlaytimeForever();
        list.add(piclist);
        list.add(pic_count);

        return list;
    }


    @RequestMapping("/loadfriends")
    @ResponseBody
    public List<Object> loadFriendsBlock(HttpServletRequest request){

        System.out.println("here is ok");

        List<Object> list = new ArrayList<>();
        String steamid = request.getParameter("steamid");
        int appid = Integer.parseInt(request.getParameter("appid"));

        List<Player> friends_player = new ArrayList<>();
        List<Integer> play2week = new ArrayList<>();
        List<Integer> playforever = new ArrayList<>();
        List<Integer> achieved_count = new ArrayList<>();
        int ach_all = playerachService.countAllAchByGame(steamid,appid);

        List<Friend> friends = friendService.getMyFriends(steamid);
        boolean flag = true;
        //剔除资料私密的好友和没有此游戏的好友，并将剩余好友加入列表
        if (friends != null && friends.size() > 0){
            int count_ach = playerachService.countAllAchByGame(steamid,appid);
            System.out.println("count achievements = " + count_ach);
            for (Friend friend : friends){
                String friend_steamid = friend.getFriendsteamid();
                int visible = new CheckVisibilityState().check(friend_steamid).getCommunityvisibilitystate();
                OwnedGame ownedGame = ownedgameService.getOwnedgame(friend_steamid,appid);

                if (visible != 3 || ownedGame == null || count_ach == 0){
                    System.out.println(friend_steamid+" : "+visible);
                    flag = false;
                }
                if (flag){
                    friends_player.add(playerService.selectPlayer(friend_steamid));
                    play2week.add(ownedGame.getPlaytime2week());
                    playforever.add(ownedGame.getPlaytimeForever());
                    achieved_count.add(playerachService.countAchievedByGame(friend_steamid,appid));
                }
            }
        }
        int count = friends_player.size();

        list.add(friends_player);
        list.add(play2week);
        list.add(playforever);
        list.add(achieved_count);
        list.add(ach_all);
        list.add(count);

        return list;
    }
}
