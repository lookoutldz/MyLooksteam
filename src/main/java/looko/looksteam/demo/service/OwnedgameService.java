package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.OwnedGame;

import java.util.List;

public interface OwnedgameService {

    public int updateOwnedgames(List<OwnedGame> ownedGames);
    public List<OwnedGame> getOwnedgames_all(String steamid);
    public List<OwnedGame> getOwnedgames_perfect(String steamid);
    public List<OwnedGame> getOwnedgames_favorite(String steamid);
    public List<OwnedGame> getOwnedgames_notplay(String steamid);
    public List<OwnedGame> searchMyGames(String steamid, String appname);
}
