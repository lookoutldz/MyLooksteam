package looko.looksteam.demo.controller;

import looko.looksteam.demo.service.OwnedgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameController {

    @Autowired
    OwnedgameService ownedgameService;

    @RequestMapping("/gameController")
    public String gameController(HttpServletRequest request, ModelMap modelMap){

        String steamid = request.getParameter("steamid");
        int appid = Integer.parseInt(request.getParameter("appid"));


        return "/game";
    }
}
