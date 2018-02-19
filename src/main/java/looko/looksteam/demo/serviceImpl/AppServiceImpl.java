package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.crawler.LaunchCrawler;
import looko.looksteam.demo.dao.AppMapper;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.tool.GetNowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AppService")
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public int updateApps(List<App> apps) {
        int row = 0;
        System.out.println("apps.size()="+apps.size());
        if (apps != null && apps.size() > 0){
            for (App app : apps){
                if (null == appMapper.selectByPrimaryKey(app.getAppid())){
                    row += appMapper.insert(app);
                }
            }
        }
        return row;
    }

    @Override
    public int updateAppPic(App app) {

        return appMapper.updateByPrimaryKeySelective(app);
    }

    //查询玩家游戏时同时更新的app数据
    @Override
    public int updateAppsPic(List<OwnedGame> ownedGames){
        int row = 0;
        if (ownedGames != null && ownedGames.size() > 0){
            for (OwnedGame ownedGame : ownedGames){
                int appid = ownedGame.getAppid();
                System.out.println(null == appMapper.selectByPrimaryKey(appid).getPic1());
                System.out.println("appid = " + appid);
                if(null == appMapper.selectByPrimaryKey(appid).getPic1()){
                    List<String> strings = new LaunchCrawler().getPicLinks(appid);
                    if (strings != null && strings.size() > 0) {
                        App app = new App();
                        app.setAppid(appid);
                        int size = strings.size();
                        if (size >= 5) {
                            app.setPic1(strings.get(0));
                            app.setPic2(strings.get(1));
                            app.setPic3(strings.get(2));
                            app.setPic4(strings.get(3));
                            app.setPic5(strings.get(4));
                        } else if (size == 4) {
                            app.setPic1(strings.get(0));
                            app.setPic2(strings.get(1));
                            app.setPic3(strings.get(2));
                            app.setPic4(strings.get(3));
                        } else if (size == 3) {
                            app.setPic1(strings.get(0));
                            app.setPic2(strings.get(1));
                            app.setPic3(strings.get(2));
                        } else if (size == 2) {
                            app.setPic1(strings.get(0));
                            app.setPic2(strings.get(1));
                        } else {
                            app.setPic1(strings.get(0));
                        }
                        app.setImgIconUrl(ownedGame.getImgIconUrl());
                        app.setImgLogoUrl(ownedGame.getImgLogoUrl());
                        app.setUpdatetime(GetNowTime.getAsString());
                        row += appMapper.updateByPrimaryKeySelective(app);
                    }
                }
            }
        }
        return row;
    }

    @Override
    public App searchByID(int appid) {

        return appMapper.selectByPrimaryKey(appid);
    }

    @Override
    public List<App> searchByAppname(String appname) {

        return appMapper.selectByAppname(appname);
    }
}
