package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.entity.PlayerAch;

import java.util.List;

public interface PlayerachService {

    int updateOnesAchievements(String steamid);

    List<PlayerAch> getPlayersAch(String steamid);

    List<PlayerAch> getPlayersAchForGame(String steamid, int appid);

    int countPerfectGame(String steamid);

    int countHasAchGame(String steamid);

    int countUnlockAch(String steamid);

    int countAllAch(String steamid);

    int countAchievedByGame(String steamid, int appid);

    int countAllAchByGame(String steamid, int appid);

    int getAve_FriendsAchPercentage(List<Friend> friends);

    List<App> getPerfectGame(String steamid);
}
