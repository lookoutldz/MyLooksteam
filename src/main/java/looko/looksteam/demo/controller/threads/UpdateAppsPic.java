package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateAppsPic extends Thread {

    private String steamid;

    private static AppService appService = ApplicationContextHelper.getBean(AppService.class);

    OwnedgameService ownedgameService = ApplicationContextHelper.getBean(OwnedgameService.class);

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    @Override
    public void run() {
        int row = 0;
        List<OwnedGame> ownedGames;
        ownedGames = ownedgameService.getOwnedgames_favorite(steamid);
        for (int i = 0; i < 5 && i < ownedGames.size(); i++) {
            row += appService.updateAppPic(ownedGames.get(i));
        }
        System.out.println("update pic:"+row);
    }
}
