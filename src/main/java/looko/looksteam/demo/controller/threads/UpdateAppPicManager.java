package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateAppPicManager {

    OwnedgameService ownedgameService = ApplicationContextHelper.getBean(OwnedgameService.class);

    /*
        只更新5个pic
     */
    public void goUpdate(String steamid){

        List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_favorite(steamid);
        if (ownedGames != null && ownedGames.size() > 0){
            UpdateAppPic updateAppPic;
            //最多更新5个游戏的pic
            for (int i = 0; i < ownedGames.size() && i < 5; i++) {
                updateAppPic = new UpdateAppPic();
                updateAppPic.setOwnedGame(ownedGames.get(i));
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
