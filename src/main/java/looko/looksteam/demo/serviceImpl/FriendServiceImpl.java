package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.api.GetFriendList;
import looko.looksteam.demo.dao.FriendMapper;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.entity.FriendKey;
import looko.looksteam.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FriendService")
public class FriendServiceImpl  implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public int updateFriends(String steamid) {

        List<Friend> friends = new GetFriendList().getAsFriends(steamid);
        return updateFriends(friends);
    }

    @Override
    public int updateFriends(List<Friend> friends) {

        int row = 0;
        if (friends != null && friends.size() >0) {

            for (Friend friend : friends){
                row += updateFriends(friend);
            }
        }
        return row;
    }

    @Override
    public int updateFriends(Friend friend) {

        int row = 0;
        FriendKey key = new FriendKey();
        key.setSteamid(friend.getSteamid());
        key.setFriendsteamid(friend.getFriendsteamid());
        if (null != friendMapper.selectByPrimaryKey(key))
            row += friendMapper.updateByPrimaryKey(friend);
        else
            row += friendMapper.insert(friend);

        return row;
    }

    @Override
    public List<Friend> getMyFriends(String steamid) {

        return friendMapper.selectMyFriends(steamid);
    }

    @Override
    public List<Friend> getMyFriendsByTime(String steamid) {

        return friendMapper.selectMyFriendsByTime(steamid);
    }
}
