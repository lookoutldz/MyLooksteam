package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.GetSteamLevel;
import looko.looksteam.demo.api.extra.GetPlayerOnlineStatus;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("steamid")
public class GamesContrller {

    @Autowired
    private PlayerService playerService;

    @RequestMapping("/gamesController")
    public String gamesHTML(String steamid, ModelMap modelMap){

        //调用service获取各种需要的数据存入modelmap
        modelMap.addAttribute("player",playerService.selectPlayer(steamid));
        modelMap.addAttribute("onlineStatus",new GetPlayerOnlineStatus().getStatus(steamid));
        modelMap.addAttribute("level",new GetSteamLevel().getAsInt(steamid));
        modelMap.addAttribute("steamid", steamid);

        modelMap.addAttribute("currentPage",1);

        return "games";
    }

}
