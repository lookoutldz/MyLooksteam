package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.GetSteamLevel;
import looko.looksteam.demo.api.extra.GetPlayerOnlineStatus;
import looko.looksteam.demo.controller.threads.UpdateAchManager;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameController {

    @Autowired
    OwnedgameService ownedgameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private AppService appService;

    @RequestMapping("/gameController")
    public String gameController(HttpServletRequest request, ModelMap modelMap){

        String steamid = request.getParameter("steamid");
        int appid = Integer.parseInt(request.getParameter("appid"));
        //获取游戏
        OwnedGame ownedGame = ownedgameService.getOwnedgame(steamid,appid);

        //更新游戏成就
        UpdateAchManager updateAchManager = new UpdateAchManager();
        updateAchManager.goUpdate(ownedGame);

        //调用service获取各种需要的数据存入modelmap
        modelMap.addAttribute("player",playerService.selectPlayer(steamid));
        modelMap.addAttribute("onlineStatus",new GetPlayerOnlineStatus().getStatus(steamid));
        modelMap.addAttribute("level",new GetSteamLevel().getAsInt(steamid));
        modelMap.addAttribute("theGame",ownedGame);
        modelMap.addAttribute("steamid", steamid);
        modelMap.addAttribute("appid", appid);

        return "game";
    }
}
