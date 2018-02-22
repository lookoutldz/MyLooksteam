package looko.looksteam.demo.api.extra;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GetPlayerOnlineStatus {

    public String getStatus(String steamid){

        String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2?steamids="+steamid+"&key="+ X.key;
        InputStream is = new HttpGet().getAsStream(url);
        if (is == null)
            return null;
        InputStreamReader isr = new InputStreamReader(is);

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(isr).getAsJsonObject();
        JsonObject responseObj = root.get("response").getAsJsonObject();
        JsonArray players = responseObj.get("players").getAsJsonArray();
        JsonObject result = players.get(0).getAsJsonObject();
        int statusCode = result.get("personastate").getAsInt();
        String status;
        if (0 == statusCode){
            status = "离线";
        }
        else if (1 == statusCode){
            status = "在线";
        }
        else if (2 == statusCode){
            status = "忙碌";
        }
        else if (3 == statusCode){
            status = "离开";
        }
        else if (4 == statusCode){
            status = "打盹中";
        }
        else if (5 == statusCode){
            status = "想交易";
        }
        else if (6 == statusCode){
            status = "寻找玩";
        }
        else{
            status = "神秘状态";
        }

        return status;
    }
}
