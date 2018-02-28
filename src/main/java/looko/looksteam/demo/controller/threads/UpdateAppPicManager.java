package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateAppPicManager {

    OwnedgameService ownedgameService = ApplicationContextHelper.getBean(OwnedgameService.class);

    public void goUpdate(String steamid){

        List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_favorite(steamid);
        if (ownedGames != null && ownedGames.size() > 0){
            UpdateAppPic updateAppPic;
            for (OwnedGame ownedGame : ownedGames) {
                updateAppPic = new UpdateAppPic();
                updateAppPic.setOwnedGame(ownedGame);
                updateAppPic.start();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
