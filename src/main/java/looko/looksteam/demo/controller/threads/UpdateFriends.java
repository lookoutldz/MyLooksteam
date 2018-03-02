package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.api.CheckVisibilityState;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.service.FriendService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

public class UpdateFriends extends Thread {

    private Friend friend;

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    private static FriendService friendService = ApplicationContextHelper.getBean(FriendService.class);

    @Override
    public void run() {

        friend.setExtraInt(new CheckVisibilityState().check(friend.getFriendsteamid()).getCommunityvisibilitystate());
        friendService.updateFriends(friend);
    }
}
