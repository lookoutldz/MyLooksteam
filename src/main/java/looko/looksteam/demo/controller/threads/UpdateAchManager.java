package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.api.GetPlayerAchievements;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.entity.PlayerAch;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateAchManager {

    private GetPlayerAchievements getAch;

    private OwnedgameService ownedgameService = ApplicationContextHelper.getBean(OwnedgameService.class);

    public void goUpdate(OwnedGame ownedGame){

        getAch = new GetPlayerAchievements();
        UpdateAch updateAch;
        List<PlayerAch> playerAches = getAch.getAsPlayerAch(ownedGame.getSteamid(),ownedGame.getAppid());
        if (playerAches != null && playerAches.size() > 0){
            for (PlayerAch playerAch : playerAches){
                updateAch = new UpdateAch();
                updateAch.setPlayerAch(playerAch);
                updateAch.start();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
