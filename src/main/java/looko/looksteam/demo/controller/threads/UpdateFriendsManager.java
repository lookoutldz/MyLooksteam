package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.api.GetFriendList;
import looko.looksteam.demo.entity.Friend;

import java.util.List;

public class UpdateFriendsManager {

    private GetFriendList getFriendList;

    public void goUpdate(String seamid){

        getFriendList = new GetFriendList();
        List<Friend> friends = getFriendList.getAsFriends(seamid);
        UpdateFriends updateFriends;
        for (Friend friend : friends){
            updateFriends = new UpdateFriends();
            updateFriends.setFriend(friend);
            updateFriends.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
