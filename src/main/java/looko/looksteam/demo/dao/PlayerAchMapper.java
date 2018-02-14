package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.PlayerAch;
import looko.looksteam.demo.entity.PlayerAchKey;

public interface PlayerAchMapper {
    int deleteByPrimaryKey(PlayerAchKey key);

    int insert(PlayerAch record);

    int insertSelective(PlayerAch record);

    PlayerAch selectByPrimaryKey(PlayerAchKey key);

    int updateByPrimaryKeySelective(PlayerAch record);

    int updateByPrimaryKey(PlayerAch record);
}