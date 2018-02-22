package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.PlayerAch;

import java.util.List;

public interface PlayerachService {

    int updateOnesAchievements(String steamid);

    List<PlayerAch> getPlayersAch(String steamid);

    List<PlayerAch> getPlayersAchForGame(String steamid, int appid);

    int getFullAchCount(String steamid);

}
