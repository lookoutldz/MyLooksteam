package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.OwnedGame;

import java.util.List;

public interface AppService {

    int updateApps(List<App> apps);
    int updateAppPic(OwnedGame ownedGame);
    int updateAppsPic(List<OwnedGame> ownedGames);
    App searchByID(int appid);
    List<App> searchByAppname(String appname);
    List<App> searchEqualsAppname(String appname);
}
