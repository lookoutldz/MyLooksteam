package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.ResolveVanityURL;
import looko.looksteam.demo.controller.threads.UpdateAppsPic;
import looko.looksteam.demo.controller.threads.UpdateFriends;
import looko.looksteam.demo.controller.threads.UpdatePlayer;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInOutController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private OwnedgameService ownedgameService;

    @RequestMapping("/loginController")
    public String loginController(HttpServletRequest request){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (steamid == null && vanityurl == null)
            return "error";
        if (steamid == null)
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);

        if (null == playerService.selectPlayer(steamid)){

            UpdatePlayer updatePlayer = new UpdatePlayer();
            updatePlayer.setSteamid(steamid);
            updatePlayer.start();

            System.out.println("update his games:"+ownedgameService.updateOwnedgames(steamid));

            UpdateAppsPic updateAppsPic = new UpdateAppsPic();
            updateAppsPic.setSteamid(steamid);
            updateAppsPic.start();

            UpdateFriends updateFriends = new UpdateFriends();
            updateFriends.setSteamid(steamid);
            updateFriends.start();

            try {
                updatePlayer.join();
                updateFriends.join();
                updateAppsPic.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/gamesController?steamid="+steamid;
    }

    @RequestMapping("/logoutController")
    public String logoutController(){

        return "login";
    }

}
