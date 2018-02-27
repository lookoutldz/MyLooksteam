package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.api.GetPlayerAchievements;
import looko.looksteam.demo.dao.AppMapper;
import looko.looksteam.demo.dao.OwnedGameMapper;
import looko.looksteam.demo.dao.PlayerAchMapper;
import looko.looksteam.demo.entity.*;
import looko.looksteam.demo.service.PlayerachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PlayerachService")
public class PlayerachServiceImpl implements PlayerachService {

    @Autowired
    PlayerAchMapper playerAchMapper;
    @Autowired
    OwnedGameMapper ownedGameMapper;
    @Autowired
    AppMapper appMapper;

    @Override
    public int updateOnesAchievements(String steamid) {

        int row = 0;
        List<OwnedGame> ownedGames = ownedGameMapper.selectBySteamid(steamid);//获得用户游戏列表
        if (ownedGames != null && ownedGames.size() > 0){
            GetPlayerAchievements getAch = new GetPlayerAchievements();
            List<PlayerAch> playerAches;
            PlayerAchKey key;
            for (OwnedGame ownedGame : ownedGames){//更新每个游戏的成就
                key = new PlayerAchKey();
                playerAches = getAch.getAsPlayerAch(steamid,ownedGame.getAppid());
                if (playerAches != null && playerAches.size() > 0){
                    for (PlayerAch playerAch : playerAches){
                        key.setSteamid(steamid);
                        key.setAchname(playerAch.getAchname());
                        if (null == playerAchMapper.selectByPrimaryKey(key))
                            row += playerAchMapper.insert(playerAch);
                        else
                            row += playerAchMapper.updateByPrimaryKey(playerAch);
                    }
                }
            }
        }
        return  row;
    }

    @Override
    public List<PlayerAch> getPlayersAch(String steamid) {

        return null;
    }

    @Override
    public List<PlayerAch> getPlayersAchForGame(String steamid, int appid) {

        PlayerAchGameKey key = new PlayerAchGameKey();
        key.setSteamid(steamid);
        key.setAppid(appid);
        return playerAchMapper.selectByGame(key);
    }

    @Override
    public int countPerfectGame(String steamid) {

        return playerAchMapper.countPerfectGame(steamid);
    }


    @Override
    public int countHasAchGame(String steamid) {

        return playerAchMapper.countHasAchGame(steamid);
    }

    @Override
    public int countUnlockAch(String steamid) {

        return playerAchMapper.countUnlockAch(steamid);
    }

    @Override
    public int countAllAch(String steamid) {

        return playerAchMapper.countAllAch(steamid);
    }

    @Override
    public int countAllAchByGame(String steamid, int appid) {

        PlayerAchGameKey key = new PlayerAchGameKey();
        key.setAppid(appid);
        key.setSteamid(steamid);
        return playerAchMapper.countAllAchByGame(key);
    }

    @Override
    public int countAchievedByGame(String steamid, int appid) {

        PlayerAchGameKey key = new PlayerAchGameKey();
        key.setAppid(appid);
        key.setSteamid(steamid);
        return playerAchMapper.countAchievedByGame(key);
    }

    @Override
    public int getAve_FriendsAchPercentage(List<Friend> friends) {

        return 0;
    }

    @Override
    public List<App> getPerfectGame(String steamid) {

        List<App> apps = new ArrayList<>();
        List<Integer> appids = playerAchMapper.getPerfectAppids(steamid);
        if (appids != null && appids.size() > 0){
            for (Integer appid : appids){
                apps.add(appMapper.selectByPrimaryKey(appid));
            }
        }
        return apps;
    }
}
