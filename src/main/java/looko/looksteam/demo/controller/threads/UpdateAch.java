package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.entity.PlayerAch;
import looko.looksteam.demo.service.PlayerachService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

import java.util.List;

public class UpdateAch extends Thread {

    private PlayerAch playerAch;

    public PlayerAch getPlayerAch() {
        return playerAch;
    }

    public void setPlayerAch(PlayerAch playerAch) {
        this.playerAch = playerAch;
    }


    private PlayerachService playerachService = ApplicationContextHelper.getBean(PlayerachService.class);

    @Override
    public void run() {

        playerachService.updateAchievements(playerAch);
    }
}
