package looko.looksteam.demo.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.tool.GetNowTime;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetRecentlyPlayedGames {

    public List<OwnedGame> getAsOwnedgames(String steamid, int count){

        List<OwnedGame> recentlyGames = new ArrayList<>(count);
        String getRecentlyPlayedGames = "http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v1/?steamid="+steamid+"&count="+count+"&key="+ X.key;
        InputStream is = new HttpGet().getAsStream(getRecentlyPlayedGames);
        if (is == null)
            return  null;
        InputStreamReader isr = new InputStreamReader(is);

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(isr).getAsJsonObject();
        JsonObject responseObject = root.get("response").getAsJsonObject();
        JsonArray games = responseObject.get("games").getAsJsonArray();
        if(0 == games.size())
            return null;

        OwnedGame ownedGame;
        String picheader = "http://media.steampowered.com/steamcommunity/public/images/apps/";
        for (JsonElement jsonElement : games)
        {
            JsonObject game = jsonElement.getAsJsonObject();
            ownedGame = new OwnedGame();

            ownedGame.setSteamid(steamid);
            ownedGame.setAppid(game.get("appid").getAsInt());
            ownedGame.setAppname(game.get("name").getAsString());
            ownedGame.setPlaytimeForever(game.get("playtime_forever").getAsInt()/60);
            if(game.has("playtime_2weeks"))
                ownedGame.setPlaytime2week(game.get("playtime_2weeks").getAsInt()/60);
            else
                ownedGame.setPlaytime2week(0);
            if (!game.get("img_icon_url").getAsString().trim().equals(""))
                ownedGame.setImgIconUrl(picheader+game.get("appid")+"/"+game.get("img_icon_url").getAsString()+".jpg");
            if (!game.get("img_logo_url").getAsString().trim().equals(""))
                ownedGame.setImgLogoUrl(picheader+game.get("appid")+"/"+game.get("img_logo_url").getAsString()+".jpg");

            recentlyGames.add(ownedGame);
        }
        return recentlyGames;
    }
}
