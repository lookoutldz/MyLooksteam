package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.MyGame;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.OwnedGameKey;

import java.util.List;

public interface OwnedGameMapper {
    int deleteByPrimaryKey(OwnedGameKey key);

    int insert(OwnedGame record);

    int insertSelective(OwnedGame record);

    int replace(OwnedGame record);

    OwnedGame selectByPrimaryKey(OwnedGameKey key);

    List<OwnedGame> selectBySteamid(String steamid);

    List<OwnedGame> selectBySteamidAppname(MyGame key);

    int updateByPrimaryKeySelective(OwnedGame record);

    int updateByPrimaryKey(OwnedGame record);
}