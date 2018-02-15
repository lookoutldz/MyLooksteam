package looko.looksteam.demo.api.aSteamUser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ResolveVanityURL {

    public String resolveToSteamID(String vanityurl){

        String urlstr = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?vanityurl="+vanityurl+"&key="+X.key;

        InputStream is = new HttpGet().getAsStream(urlstr);
        if (is == null)
            return null;
        InputStreamReader isr = new InputStreamReader(is);
        JsonParser parser = new JsonParser();
        JsonObject root = (JsonObject) parser.parse(isr);
        JsonObject responseObj = root.get("response").getAsJsonObject();
        if (1 == responseObj.get("success").getAsInt()){
            return responseObj.get("steamid").getAsString();
        }
        return null;
    }
}
