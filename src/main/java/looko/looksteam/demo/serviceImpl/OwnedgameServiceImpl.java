package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.dao.OwnedGameMapper;
import looko.looksteam.demo.entity.MyGameKey;
import looko.looksteam.demo.entity.MyGameTimeKey;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.OwnedGameKey;
import looko.looksteam.demo.service.OwnedgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OwnedGameService")
public class OwnedgameServiceImpl implements OwnedgameService {

    @Autowired
    private OwnedGameMapper ownedGameMapper;

    @Override
    public int updateOwnedgames(List<OwnedGame> ownedGames) {
        int row = 0;
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
}
