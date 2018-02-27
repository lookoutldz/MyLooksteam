package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.OwnedGame;

import java.util.List;

public interface OwnedgameService {

    int updateOwnedgames(String steamid);
    int updateOwnedgames(OwnedGame ownedGame);
    int updateOwnedgames(List<OwnedGame> ownedGames);
    List<OwnedGame> getOwnedgames_all(String steamid);
    List<OwnedGame> getOwnedgames_perfect(String steamid);
    List<OwnedGame> getOwnedgames_favorite(String steamid);
    List<OwnedGame> getOwnedgames_notplay(String steamid);
    List<OwnedGame> searchMyGames(String steamid, String appname);
    OwnedGame getOwnedgame(String steamid, int appid);
    List<App> getMyFavoriteGamesApp(String steamid);

}
