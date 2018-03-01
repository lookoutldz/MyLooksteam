package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateAppPic extends Thread {

    private OwnedGame ownedGame;

    public OwnedGame getOwnedGame() {
        return ownedGame;
    }

    public void setOwnedGame(OwnedGame ownedGame) {
        this.ownedGame = ownedGame;
    }

    private static AppService appService = ApplicationContextHelper.getBean(AppService.class);


    @Override
    public void run() {

        appService.updateAppPic(ownedGame);
    }
}
