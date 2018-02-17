package looko.looksteam.demo.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.tool.X;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckVisibilityState {

    public Player check(String steamid){

        Player player = new Player();
        try
        {
            String urlstr = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2?steamids="+steamid+"&key="+ X.key;
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (200 != con.getResponseCode()){
                System.out.println("connect wrong");
                return null;
            }
            JsonParser parser = new JsonParser();
            JsonObject root = (JsonObject) parser.parse(new InputStreamReader(con.getInputStream()));
            JsonObject responseObj = root.get("response").getAsJsonObject();
            JsonArray players = responseObj.get("players").getAsJsonArray();

            if (players.size() > 0){
                player.setCommunityvisibilitystate(players.get(0).getAsJsonObject().get("communityvisibilitystate").getAsInt());
                player.setSteamid(players.get(0).getAsJsonObject().get("steamid").getAsString());
            }
            else
                player.setCommunityvisibilitystate(-1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return player;
    }
}
