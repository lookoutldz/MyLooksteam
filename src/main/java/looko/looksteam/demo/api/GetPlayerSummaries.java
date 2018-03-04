package looko.looksteam.demo.api;


import com.google.gson.*;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.tool.GetNowTime;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GetPlayerSummaries {

    /*
        通过API获得player封装
     */

    public Player getAsPlayer(String steamid){

        String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2?steamids="+steamid+"&key="+ X.key;
        InputStream is = new HttpGet().getAsStream(url);
        if (is == null)
            return null;
        Player player = new Player();
        InputStreamReader isr = new InputStreamReader(is);

        try {
            JsonParser parser = new JsonParser();
            JsonObject root = parser.parse(isr).getAsJsonObject();
            JsonObject responseObj = root.get("response").getAsJsonObject();
            JsonArray players = responseObj.get("players").getAsJsonArray();
            JsonObject result = players.get(0).getAsJsonObject();

            player.setSteamid(result.get("steamid").getAsString());
            player.setCommunityvisibilitystate(result.get("communityvisibilitystate").getAsInt());
            player.setProfilestate(result.get("profilestate").getAsInt());
            player.setPersonaname(result.get("personaname").getAsString());
            player.setLastlogoff(result.get("lastlogoff").getAsInt());
            if (result.has("commentpermission"))
                player.setCommentpermission(result.get("commentpermission").getAsInt());
            player.setProfileurl(result.get("profileurl").getAsString());
            player.setAvatar(result.get("avatar").getAsString());
            player.setAvatarmedium(result.get("avatarmedium").getAsString());
            player.setAvatarfull(result.get("avatarfull").getAsString());
            player.setPersonastate(result.get("personastate").getAsInt());
            player.setTimecreated(result.get("timecreated").getAsInt());
            player.setPersonastateflags(result.get("personastateflags").getAsInt());

            if (result.has("realname"))
                player.setRealname(result.get("realname").getAsString());
            if (result.has("primaryclanid"))
                player.setPrimaryclanid(result.get("primaryclanid").getAsString());
            if (result.has("gameextrainfo"))
                player.setGameextrainfo(result.get("gameextrainfo").getAsString());
            if (result.has("gameserverip"))
                player.setGameserverip(result.get("gameserverip").getAsString());
            if (result.has("gameid"))
                player.setGameid(result.get("gameid").getAsInt());
            if (result.has("loccountrycode"))
                player.setLoccountrycode(result.get("loccountrycode").getAsString());
            if (result.has("locstatecode"))
                player.setLocstatecode(result.get("locstatecode").getAsString());
            if (result.has("loccityid"))
                player.setLoccityid(result.get("loccityid").getAsString());

            player.setUpdatetime(GetNowTime.getAsString());
        }
        catch (JsonIOException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }

        return player;
    }
}
