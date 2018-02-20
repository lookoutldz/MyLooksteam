package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.Friend;

import java.util.List;

public interface FriendService {

    int updateFriends(String steamid);

    List<Friend> getMyFriends(String steamid);

    List<Friend> getMyFriendsByTime(String steamid);

}
