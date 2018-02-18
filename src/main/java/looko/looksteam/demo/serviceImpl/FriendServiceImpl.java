package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.dao.FriendMapper;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FriendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public int updateFriends(List<Friend> friends) {
        int row = 0;
        if (friends != null)
            for (Friend friend : friends){
                row += friendMapper.replace(friend);
            }
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
