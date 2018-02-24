package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.PlayerAch;
import looko.looksteam.demo.entity.PlayerAchGameKey;
import looko.looksteam.demo.entity.PlayerAchKey;

import java.util.List;

public interface PlayerAchMapper {
    int deleteByPrimaryKey(PlayerAchKey key);

    int insert(PlayerAch record);

    int insertSelective(PlayerAch record);

    PlayerAch selectByPrimaryKey(PlayerAchKey key);

    List<PlayerAch> selectByGame(PlayerAchGameKey key);

    int countHasAchGame(String steamid);

    int countUnlockAch(String steamid);

    int countAllAch(String steamid);

    List<Integer> selectFullAchAppid(String steamid);

    int updateByPrimaryKeySelective(PlayerAch record);

    int updateByPrimaryKey(PlayerAch record);
}