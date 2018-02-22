package looko.looksteam.demo.ajax;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GamesAjax {

    @Autowired
    OwnedgameService ownedgameService;


    @RequestMapping("/loadmylib")
    @ResponseBody
    public List<Object> loadOwnedGames(HttpServletRequest request){

        List<Object> list = new ArrayList<>();
        String steamid = request.getParameter("steamid");
        List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_favorite(steamid);
        int gameCount = ownedGames.size();
        int totalPages = (int) Math.ceil(gameCount*1.0/12);
        list.add(ownedGames);
        list.add(gameCount);
        list.add(totalPages);

        return list;
    }

    @RequestMapping("/loadmyfavorite")
    @ResponseBody
    public List<Object> loadFavoriveGames(HttpServletRequest request){

        List<Object> list = new ArrayList<>();
        String steamid = request.getParameter("steamid");
        List<App> apps = ownedgameService.getMyFavoriteGamesApp(steamid);
        int count = apps.size();
        list.add(apps);
        list.add(count);

        return list;
    }

    @RequestMapping("/searchmylib")
    @ResponseBody
    public List<Object> search(HttpServletRequest request){

        List<Object> list = new ArrayList<>();
        String steamid = request.getParameter("steamid");
        String keyword = request.getParameter("keyword");
        List<OwnedGame> ownedGames = ownedgameService.searchMyGames(steamid,keyword);
        int gameCount = ownedGames.size();
        int totalPages = (int) Math.ceil(gameCount*1.0/12);
        list.add(ownedGames);
        list.add(gameCount);
        list.add(totalPages);

        return list;
    }
}
