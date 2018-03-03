package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.ResolveVanityURL;
import looko.looksteam.demo.controller.threads.*;
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


    /*
        登入控制器，主要用于登录时信息从api的获取和更新
        要从API获取的信息和获取顺序：
            1.用户个人信息player,用于更新DB（额外线程）
            2.用户游戏列表ownedgames,用于更新DB
            3.利用ownedgames里的列表项更新app表的logo,icon,pic1~pic5（额外线程）
            4.利用ownedgames里的appid更新playerach（额外线程）
            5.用户好友列表friends，用于更新DB（额外线程）
     */

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

            UpdateAppPicManager updateAppPicManager = new UpdateAppPicManager();
            updateAppPicManager.goUpdate(steamid);

            UpdateFriendsManager updateFriendsManager = new UpdateFriendsManager();
            updateFriendsManager.goUpdate(steamid);

        }
        else{
            System.out.println("update his games:"+ownedgameService.updateOwnedgames(steamid));

            UpdateAppPicManager updateAppPicManager = new UpdateAppPicManager();
            updateAppPicManager.goUpdate(steamid);

            UpdateFriendsManager updateFriendsManager = new UpdateFriendsManager();
            updateFriendsManager.goUpdate(steamid);
        }

        return "redirect:/gamesController?steamid="+steamid;
    }

    @RequestMapping("/logoutController")
    public String logoutController(){

        return "login";
    }

}
