package looko.looksteam.demo.ajax;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.api.CheckVisibilityState;
import looko.looksteam.demo.api.ResolveVanityURL;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.tool.X;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class SteamIDCheck {

    /*
        取得steamid，并判断是否有访问资料的权限
     */

    @RequestMapping("/steamidcheck")
    @ResponseBody
    public Player check(HttpServletRequest request, HttpServletResponse response){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (vanityurl != null){
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);
        }

        return new CheckVisibilityState().check(steamid);
    }
}
