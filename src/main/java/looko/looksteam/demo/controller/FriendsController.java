package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.GetSteamLevel;
import looko.looksteam.demo.api.extra.GetPlayerOnlineStatus;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FriendsController {

    @Autowired
    PlayerService playerService;

    @RequestMapping("/friendsController")
    public String friendsHTML(String steamid, ModelMap modelMap){

        //调用service获取各种需要的数据存入modelmap
        modelMap.addAttribute("player",playerService.selectPlayer(steamid));
        modelMap.addAttribute("onlineStatus",new GetPlayerOnlineStatus().getStatus(steamid));
        modelMap.addAttribute("level",new GetSteamLevel().getAsInt(steamid));
        modelMap.addAttribute("steamid", steamid);

        return "friends";
    }
}
