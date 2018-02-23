package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.service.PlayerService;
import looko.looksteam.demo.tool.ApplicationContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class UpdatePlayer extends Thread {

    private String steamid;

    private static PlayerService playerService = ApplicationContextHelper.getBean(PlayerService.class);

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    @Override
    public void run() {
        System.out.println("update player:"+playerService.updatePlayer(steamid));
    }
}
