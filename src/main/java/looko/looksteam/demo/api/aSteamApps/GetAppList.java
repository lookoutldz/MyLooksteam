package looko.looksteam.demo.api.aSteamApps;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.tool.GetNowTime;
import looko.looksteam.demo.webConnect.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetAppList {

    public List<App> getAsApps(){

        List<App> apps = new ArrayList<>();
        String url = "http://api.steampowered.com/ISteamApps/GetAppList/v2";
        InputStream is = new HttpGet().getAsStream(url);
        if (is == null)
            return  null;
        InputStreamReader isr = new InputStreamReader(is);

        JsonParser parser = new JsonParser();
        JsonObject root = parser.parse(isr).getAsJsonObject();
        JsonObject applist = root.get("applist").getAsJsonObject();
        JsonArray appArray = applist.get("apps").getAsJsonArray();

        App app;
        JsonObject object;
        for (JsonElement element : appArray){
            object = element.getAsJsonObject();
            app = new App();
            app.setAppid(object.get("appid").getAsInt());
            app.setAppname(object.get("name").getAsString());
            app.setUpdatetime(GetNowTime.getAsString());
            apps.add(app);
        }

        return apps;
    }
}
