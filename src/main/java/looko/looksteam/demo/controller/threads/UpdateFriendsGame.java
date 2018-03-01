package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.api.CheckVisibilityState;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerachService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateFriendsGame extends Thread {

    private OwnedgameService ownedgameService = ApplicationContextHelper.getBean(OwnedgameService.class);

    private Friend friend;

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    @Override
    public void run() {

        if (friend != null){
            CheckVisibilityState c = new CheckVisibilityState();
            int visible;
            visible = c.check(friend.getFriendsteamid()).getCommunityvisibilitystate();
            if (visible == 3)
                ownedgameService.updateOwnedgames(friend.getFriendsteamid());
        }
    }
}
