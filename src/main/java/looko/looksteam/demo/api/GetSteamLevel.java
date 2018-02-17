package looko.looksteam.demo.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GetSteamLevel {

    public int getAsInt(String steamid){

        int level;
        String url = "http://api.steampowered.com/IPlayerService/GetSteamLevel/v1/?steamid="+steamid+"&key="+ X.key;
        InputStream is = new HttpGet().getAsStream(url);
        if (is == null)
            return 0;
        InputStreamReader isr = new InputStreamReader(is);

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(isr).getAsJsonObject();
        JsonObject responseObj = root.get("response").getAsJsonObject();
        level = responseObj.get("player_level").getAsInt();

        return level;
    }
}
