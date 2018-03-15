package looko.looksteam.demo.ajax;

import looko.looksteam.demo.controller.threads.UpdateFriendsGame;
import looko.looksteam.demo.controller.threads.UpdatePlayer;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.service.FriendService;
import looko.looksteam.demo.service.OwnedgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BackgroundUpdating {

    @Autowired
    FriendService friendService;
    @Autowired
    OwnedgameService ownedgameService;

    @RequestMapping("updateFriendsGame_bg")
    @ResponseBody
    public void friendsGameUpdating(HttpServletRequest request){

        System.out.println("friendsGameUpdating is ok");
        //新登录则要更新后台信息
        String fromPage = request.getParameter("fromPage");
        if (fromPage != null && fromPage.equals("login")){
            String steamid = request.getParameter("steamid");
            List<Friend> friends;
            friends = friendService.getMyFriends(steamid);
            if (friends != null && friends.size() > 0){
                UpdateFriendsGame updateFriendsGame;
                for (Friend friend : friends){
                    updateFriendsGame = new UpdateFriendsGame();
                    updateFriendsGame.setFriend(friend);
                    updateFriendsGame.start();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    @RequestMapping("updateFriendsPlayer_bg")
    @ResponseBody
    public void friendsPlayerUpdating(HttpServletRequest request){

        System.out.println("updatePlayerForFriends is ok");
        //新登录则要更新后台信息
        String fromPage = request.getParameter("fromPage");
        if (fromPage != null && fromPage.equals("login")) {
            String steamid = request.getParameter("steamid");
            List<Friend> friends = friendService.getMyFriends(steamid);
            if (friends != null && friends.size() > 0) {
                //System.out.println("size = "+friends.size());
                UpdatePlayer updatePlayer;
                for (Friend friend : friends) {
                    updatePlayer = new UpdatePlayer();
                    updatePlayer.setSteamid(friend.getFriendsteamid());
                    updatePlayer.start();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
}
