package looko.looksteam.demo.ajax;

import looko.looksteam.demo.api.CheckVisibilityState;
import looko.looksteam.demo.api.ResolveVanityURL;
import looko.looksteam.demo.entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SteamIDCheck {

    /*
        取得steamid，并判断是否有访问资料的权限
     */

    @RequestMapping("/steamidcheck")
    @ResponseBody
    public Player check(HttpServletRequest request){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (vanityurl != null){
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);
        }

        return new CheckVisibilityState().check(steamid);
    }
}
