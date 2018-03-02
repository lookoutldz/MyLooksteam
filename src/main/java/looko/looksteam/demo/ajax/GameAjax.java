package looko.looksteam.demo.ajax;

import looko.looksteam.demo.ajax.threads.CheckVisibilityManager;
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
import java.util.concurrent.*;

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

        int appid = Integer.parseInt(request.getParameter("appid"));
        App app = appService.searchByID(appid);

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

        list.add(piclist);
        list.add(pic_count);

        return list;
    }


    /*
        需要数据库中有
        player（首次登录时更新）  friend（）  ownedgame   playerach
     */
    @RequestMapping("/loadfriends")
    @ResponseBody
    public List<Object> loadFriendsBlock(HttpServletRequest request){

        System.out.println("here is ok");

        List<Object> list = new ArrayList<>();
        String steamid = request.getParameter("steamid");
        int appid = Integer.parseInt(request.getParameter("appid"));

        List<Player> friends_player = new ArrayList<>();//每个好友的个人信息
        List<Integer> play2week = new ArrayList<>();//每个好友的游戏两周时长
        List<Integer> playforever = new ArrayList<>();//每个好友的游戏总时长
        List<Integer> achieved_count = new ArrayList<>();//用于存储每个好友在游戏中达成的成就数
        int ach_all = playerachService.countAllAchByGame(steamid,appid);//游戏总成就数
        System.out.println("count achievements = " + ach_all);

        //获取好友列表
        List<Friend> friends = friendService.getMyFriends(steamid);

        //循环遍历好友列表，剔除资料私密的好友和没有此游戏的好友，并将剩余好友加入列表
        if (friends != null && friends.size() > 0){
            int visible;
            for (Friend friend : friends){
                String friend_steamid = friend.getFriendsteamid();
                visible = friend.getExtraInt();
                //System.out.println(friend_steamid + " : " + visible);
                if (visible == 3){
                    OwnedGame ownedGame = ownedgameService.getOwnedgame(friend_steamid,appid);
                    if (ownedGame != null){
                        friends_player.add(playerService.selectPlayer(friend_steamid));
                        play2week.add(ownedGame.getPlaytime2week());
                        playforever.add(ownedGame.getPlaytimeForever());
                        achieved_count.add(playerachService.countAchievedByGame(friend_steamid,appid));
                    }
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
