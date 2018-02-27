package looko.looksteam.demo.ajax;

import looko.looksteam.demo.controller.threads.UpdateFriendsGame;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.service.FriendService;
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

    @RequestMapping("updateFriendsGame_bg")
    @ResponseBody
    public void friendsGameUpdateing(HttpServletRequest request){

        String steamid = request.getParameter("steamid");
        List<Friend> friends;
        friends = friendService.getMyFriends(steamid);
        UpdateFriendsGame updateFriendsGame;
       for (Friend friend : friends){
           updateFriendsGame = new UpdateFriendsGame();
           updateFriendsGame.setFriend(friend);
           updateFriendsGame.start();
       }

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
