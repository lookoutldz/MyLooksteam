package looko.looksteam.demo.controller.threads;

import looko.looksteam.demo.service.FriendService;
import looko.looksteam.demo.tool.ApplicationContextHelper;

public class UpdateFriends extends Thread {

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    private String steamid;

    private static FriendService friendService = ApplicationContextHelper.getBean(FriendService.class);

    @Override
    public void run() {

        System.out.println("update his friends:"+friendService.updateFriends(steamid));
    }
}
