package looko.looksteam.demo.ajax;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.model.Game_Friends;
import looko.looksteam.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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

        List<Game_Friends> gameFriends = new ArrayList<>();

        //获取好友列表
        List<Friend> friends = friendService.getMyFriends(steamid);

        //循环遍历好友列表，剔除资料私密的好友和没有此游戏的好友，并将剩余好友加入列表
        if (friends != null && friends.size() > 0){
            int visible;
            int ach_all = playerachService.countAllAchByGame(steamid,appid);
            Game_Friends gameFriend;
            OwnedGame ownedGame;
            Player p;
            for (Friend friend : friends){
                String friend_steamid = friend.getFriendsteamid();
                visible = friend.getExtraInt();
                if (visible == 3){
                    ownedGame = ownedgameService.getOwnedgame(friend_steamid,appid);
                    if (ownedGame != null){
                        gameFriend = new Game_Friends();
                        p = playerService.selectPlayer(friend_steamid);

                        gameFriend.setSteamid(p.getSteamid());
                        gameFriend.setAvatar(p.getAvatar());
                        gameFriend.setPersonaname(p.getPersonaname());
                        gameFriend.setPlay2week(ownedGame.getPlaytime2week());
                        gameFriend.setPlayforever(ownedGame.getPlaytimeForever());
                        gameFriend.setAchieved_count(playerachService.countAchievedByGame(friend_steamid,appid));
                        gameFriend.setAchievement_all(ach_all);

                        gameFriends.add(gameFriend);
                    }
                }
            }
            //加入自己的资料
            gameFriend = new Game_Friends();
            p = playerService.selectPlayer(steamid);
            ownedGame = ownedgameService.getOwnedgame(steamid,appid);

            gameFriend.setSteamid(p.getSteamid());
            gameFriend.setAvatar(p.getAvatar());
            gameFriend.setPersonaname(p.getPersonaname());
            gameFriend.setPlay2week(ownedGame.getPlaytime2week());
            gameFriend.setPlayforever(ownedGame.getPlaytimeForever());
            gameFriend.setAchieved_count(playerachService.countAchievedByGame(steamid,appid));
            gameFriend.setAchievement_all(ach_all);

            gameFriends.add(gameFriend);
        }
        int count = gameFriends.size();

        long time1 = System.currentTimeMillis();
        Collections.sort(gameFriends);
        long time2 = System.currentTimeMillis();

        System.out.println((time2-time1) + "ms");
        list.add(gameFriends);
        list.add(count);

        return list;
    }
}
