package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.ResolveVanityURL;
import looko.looksteam.demo.controller.threads.UpdateAppPicManager;
import looko.looksteam.demo.controller.threads.UpdateFriendsManager;
import looko.looksteam.demo.controller.threads.UpdatePlayer;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import looko.looksteam.demo.serviceLogic.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInOutController {

    @Autowired
    UpdateInfoService updateInfoService;

    /*
        登入控制器，主要用于登录时信息从api的获取和更新

     */

    @RequestMapping("/loginController")
    public String loginController(HttpServletRequest request){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (steamid == null && vanityurl == null)
            return "error";
        if (steamid == null)
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);

        updateInfoService.loginUpdate(steamid);

        return "redirect:/gamesController?steamid="+steamid+"&fromPage=login";
    }

    @RequestMapping("/logoutController")
    public String logoutController(){

        return "login";
    }

}
