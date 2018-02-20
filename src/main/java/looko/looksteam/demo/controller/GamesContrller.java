package looko.looksteam.demo.controller;

import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"steamid"})
public class GamesContrller {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private OwnedgameService ownedgameService;

    @Autowired
    private AppService appService;

    @RequestMapping("/gamesController")
    public String gamesPage(String steamid, ModelMap modelMap){

        //调用service获取各种需要的数据存入modelmap
        modelMap.addAttribute("player",playerService.selectPlayer(steamid));
        modelMap.addAttribute("ownedgames",ownedgameService.getOwnedgames_favorite(steamid));
        modelMap.addAttribute("apps",ownedgameService.getMyFavoriteGamesApp(steamid));
        modelMap.addAttribute("steamid", steamid);

        return "/games";
    }
}
