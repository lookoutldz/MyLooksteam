package looko.looksteam.demo.ajax;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameAjax {

    @Autowired
    OwnedgameService ownedgameService;
    @Autowired
    AppService appService;

    @RequestMapping("/loadgamepic")
    @ResponseBody
    public List<Object> loadGamePics(HttpServletRequest request){

        List<Object> list = new ArrayList<>();
//        String steamid = request.getParameter("steamid");
        int appid = Integer.parseInt(request.getParameter("appid"));
        App app = appService.searchByID(appid);
//        OwnedGame ownedGame = ownedgameService.getOwnedgame(steamid,appid);

        List<String> piclist = new ArrayList<>();
        if (app.getPic1() != null)
            piclist.add(app.getPic1());
        if (app.getPic2() != null)
            piclist.add(app.getPic2());
        if (app.getPic3() != null)
            piclist.add(app.getPic3());
        if (app.getPic4() != null)
            piclist.add(app.getPic4());
        if ((app.getPic5() != null))
            piclist.add(app.getPic5());
        int pic_count = piclist.size();
//        String appname = app.getAppname();
//        int playtime2week = ownedGame.getPlaytime2week();
//        int playtimeforever = ownedGame.getPlaytimeForever();

        list.add(piclist);
        list.add(pic_count);


        return list;
    }
}
