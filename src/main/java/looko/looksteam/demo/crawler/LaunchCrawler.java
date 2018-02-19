package looko.looksteam.demo.crawler;

import java.util.List;

public class LaunchCrawler {

    public List<String> getPicLinks(int appid){

        String urlstr = "http://store.steampowered.com/app/" + appid;

        return new ConnectManager().go(urlstr);
    }
}
