package looko.looksteam.demo.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.entity.PlayerAch;
import looko.looksteam.demo.tool.GetNowTime;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetPlayerAchievements {

    public List<PlayerAch> getAsPlayerAch(String steamid, int appid){

        List<PlayerAch> playerAches = new ArrayList<>();
        String getPlayerAchs = "http://api.steampowered.com/ISteamUserStats/GetPlayerAchievements/v1/?steamid="+steamid+"&appid="+appid+"&key="+ X.key;
        InputStream is = new HttpGet().getAsStream(getPlayerAchs);
        if (is == null)
            return  null;
        InputStreamReader isr = new InputStreamReader(is);

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(isr).getAsJsonObject();
        JsonObject playerstats = root.get("playerstats").getAsJsonObject();
        String gameName = playerstats.get("gameName").getAsString();
        if (playerstats.has("achievements")){
            JsonArray achievements = playerstats.get("achievements").getAsJsonArray();
            if(0 == achievements.size())
                return null;

            PlayerAch playerAch;
            for (JsonElement jsonElement : achievements){
                JsonObject achievement = jsonElement.getAsJsonObject();
                playerAch = new PlayerAch();

                playerAch.setSteamid(steamid);
                playerAch.setAppid(appid);
                playerAch.setAchname(achievement.get("apiname").getAsString());
                playerAch.setAchieved(achievement.get("achieved").getAsInt());
                playerAch.setUnlocktime(achievement.get("unlocktime").getAsInt());
                playerAch.setUpdatetime(GetNowTime.getAsString());
                playerAches.add(playerAch);
            }
            return playerAches;
        }
        return null;
    }

}
