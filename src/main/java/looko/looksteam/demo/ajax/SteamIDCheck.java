package looko.looksteam.demo.ajax;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.api.aSteamUser.ResolveVanityURL;
import looko.looksteam.demo.tool.X;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class SteamIDCheck {

    @RequestMapping("/steamidcheck")
    public void check(HttpServletRequest request, HttpServletResponse response){

        String steamid = request.getParameter("steamid");
        String vanityurl = request.getParameter("vanityurl");
        if (vanityurl != null){
            steamid = new ResolveVanityURL().resolveToSteamID(vanityurl);
        }
        //System.out.println("steamid="+steamid);
        try
        {
            String urlstr = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2?steamids="+steamid+"&key="+ X.key;
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (200 != con.getResponseCode()){
                System.out.println("connect wrong");
                return;
            }
            JsonParser parser = new JsonParser();
            JsonObject root = (JsonObject) parser.parse(new InputStreamReader(con.getInputStream()));
            JsonObject responseObj = root.get("response").getAsJsonObject();
            JsonArray players = responseObj.get("players").getAsJsonArray();

            String result = new Gson().toJson(players);
            //System.out.println("begin to print : " + result);
            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
