package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.OwnedGameKey;

public interface OwnedGameMapper {
    int deleteByPrimaryKey(OwnedGameKey key);

    int insert(OwnedGame record);

    int insertSelective(OwnedGame record);

    OwnedGame selectByPrimaryKey(OwnedGameKey key);

    int updateByPrimaryKeySelective(OwnedGame record);

    int updateByPrimaryKey(OwnedGame record);
}