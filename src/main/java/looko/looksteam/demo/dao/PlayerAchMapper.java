package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.PlayerAch;
import looko.looksteam.demo.entity.PlayerAchGameKey;
import looko.looksteam.demo.entity.PlayerAchKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerAchMapper {
    int deleteByPrimaryKey(PlayerAchKey key);

    int insert(PlayerAch record);

    int insertSelective(PlayerAch record);

    PlayerAch selectByPrimaryKey(PlayerAchKey key);

    List<PlayerAch> selectByGame(PlayerAchGameKey key);

    int countAllAchByGame(PlayerAchGameKey key);

    int countHasAchGame(String steamid);

    int countUnlockAch(String steamid);

    int countAllAch(String steamid);

    int countPerfectGame(String steamid);

    int countAchievedByGame(PlayerAchGameKey key);

    List<Integer> getPerfectAppids(String steamid);

    int updateByPrimaryKeySelective(PlayerAch record);

    int updateByPrimaryKey(PlayerAch record);
}