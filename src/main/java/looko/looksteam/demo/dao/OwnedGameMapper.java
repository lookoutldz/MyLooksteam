package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.MyGameKey;
import looko.looksteam.demo.entity.MyGameTimeKey;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.OwnedGameKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnedGameMapper {
    int deleteByPrimaryKey(OwnedGameKey key);

    int insert(OwnedGame record);

    int insertSelective(OwnedGame record);

    OwnedGame selectByPrimaryKey(OwnedGameKey key);

    List<OwnedGame> selectBySteamid(String steamid);

    List<OwnedGame> selectBySteamidAppname(MyGameKey key);

    List<OwnedGame> selectFavorite(String steamid);

    List<OwnedGame> selectNotPlay(String steamid);

    int updateByPrimaryKeySelective(OwnedGame record);

    int updateByPrimaryKey(OwnedGame record);
}