package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.api.GetOwnedGame;
import looko.looksteam.demo.dao.AppMapper;
import looko.looksteam.demo.dao.OwnedGameMapper;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.MyGameKey;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.OwnedGameKey;
import looko.looksteam.demo.service.OwnedgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("OwnedGameService")
public class OwnedgameServiceImpl implements OwnedgameService {

    @Autowired
    private OwnedGameMapper ownedGameMapper;

    @Autowired
    private AppMapper appMapper;

    @Override
    public int updateOwnedgames(String steamid) {
        int row = 0;
        List<OwnedGame> ownedGames = new GetOwnedGame().getAsOwnedGames(steamid);
        if (ownedGames != null && ownedGames.size() > 0){
            OwnedGameKey key = new OwnedGameKey();
            for (OwnedGame ownedgame : ownedGames) {
                key.setAppid(ownedgame.getAppid());
                key.setSteamid(ownedgame.getSteamid());
                if (null != ownedGameMapper.selectByPrimaryKey(key))
                    row += ownedGameMapper.updateByPrimaryKey(ownedgame);
                else
                    row += ownedGameMapper.insert(ownedgame);
            }
        }

        return row;
    }

    @Override
    public List<OwnedGame> getOwnedgames_all(String steamid) {

        return ownedGameMapper.selectBySteamid(steamid);
    }

    @Override
    public List<OwnedGame> getOwnedgames_perfect(String steamid) {

        return null;
    }

    @Override
    public List<OwnedGame> getOwnedgames_favorite(String steamid) {

        return ownedGameMapper.selectFavorite(steamid);
    }

    @Override
    public List<OwnedGame> getOwnedgames_notplay(String steamid) {

        return ownedGameMapper.selectNotPlay(steamid);
    }

    @Override
    public List<OwnedGame> searchMyGames(String steamid, String appname) {

        MyGameKey myGameKey = new MyGameKey();
        myGameKey.setSteamid(steamid);
        myGameKey.setAppname("%"+appname+"%");
        return ownedGameMapper.selectBySteamidAppname(myGameKey);
    }

    @Override
    public List<App> getMyFavoriteGamesApp(String steamid) {

        List<OwnedGame> ownedGames = ownedGameMapper.selectFavorite(steamid);
        List<App> apps = new ArrayList<>();
        if (ownedGames != null){
            for (int i = 0; (i < 5) && (i < ownedGames.size()); i++) {
                apps.add(appMapper.selectByPrimaryKey(ownedGames.get(i).getAppid()));
            }
        }
        return apps;
    }
}
