package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.ResolveVanityURL;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.FriendService;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInOutController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private OwnedgameService ownedgameService;
    @Autowired
    private FriendService friendService;

    @RequestMapping("/loginController")
    public String loginController(HttpServletRequest request){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (steamid == null && vanityurl == null)
            return "error";
        if (steamid == null)
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);

        if (null == playerService.selectPlayer(steamid)){
            System.out.println("update player:"+playerService.updatePlayer(steamid));
            System.out.println("update his games:"+ownedgameService.updateOwnedgames(steamid));
            System.out.println("update his friends:"+friendService.updateFriends(steamid));
        }

        return "redirect:/gamesController?steamid="+steamid;
    }

    @RequestMapping("/logoutController")
    public String logoutController(){

        return "login";
    }

}
