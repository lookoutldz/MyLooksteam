package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.entity.FriendKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendMapper {
    int deleteByPrimaryKey(FriendKey key);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(FriendKey key);

    List<Friend> selectMyFriends(String steamid);

    List<Friend> selectMyFriendsByTime(String steamid);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}