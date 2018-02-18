package looko.looksteam.demo.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.tool.GetNowTime;
import looko.looksteam.demo.tool.X;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetFriendList {

    public List<Friend> getAsFriends(String steamid){

        List<Friend> friends = new ArrayList<>();
        String url = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key="+ X.key+"&steamid="+steamid+"&relationship=friend";
        InputStream is = new HttpGet().getAsStream(url);
        if (is == null)
            return null;
        InputStreamReader isr = new InputStreamReader(is);

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(isr).getAsJsonObject();
        JsonObject friendslist = root.get("friendslist").getAsJsonObject();
        JsonArray friendsArray = friendslist.get("friends").getAsJsonArray();

        Friend friend;
        for (JsonElement jsonElement : friendsArray)
        {
            JsonObject friendObject = jsonElement.getAsJsonObject();
            friend = new Friend();
            friend.setSteamid(steamid);
            friend.setFriendsteamid(friendObject.get("steamid").getAsString());
            friend.setRelationship(friendObject.get("relationship").getAsString());
            friend.setFriendSince(friendObject.get("friend_since").getAsInt());
            friend.setUpdatetime(GetNowTime.getAsString());
            friends.add(friend);
        }

        return friends;
    }
}
