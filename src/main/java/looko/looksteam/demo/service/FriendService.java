package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.Friend;

import java.util.List;

public interface FriendService {

    int updateFriends(String steamid);

    int updateFriends(List<Friend> friends);

    int updateFriends(Friend friend);

    List<Friend> getMyFriends(String steamid);

    List<Friend> getMyFriendsByTime(String steamid);

}
