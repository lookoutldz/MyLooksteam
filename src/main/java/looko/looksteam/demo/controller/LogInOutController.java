package looko.looksteam.demo.controller;

import looko.looksteam.demo.api.ResolveVanityURL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInOutController {

    @RequestMapping("/loginController")
    public String loginController(HttpServletRequest request){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (steamid == null && vanityurl == null)
            return "error";
        if (steamid == null)
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);
        return "profile";
    }

}
