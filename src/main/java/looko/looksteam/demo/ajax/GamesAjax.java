package looko.looksteam.demo.ajax;

import looko.looksteam.demo.entity.OwnedGame;
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

        String steamid = request.getParameter("steamid");
        List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_favorite(steamid);
        int gameCount = ownedGames.size();
        int totalPages = (int) Math.ceil(gameCount*1.0/12);
        List<Object> list = new ArrayList<>();
        list.add(ownedGames);
        list.add(gameCount);
        list.add(totalPages);

        return list;
    }
}
