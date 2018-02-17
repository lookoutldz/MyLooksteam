package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.App;

import java.util.List;

public interface AppService {
    public int updateApps(List<App> apps);
    public App searchByID(int appid);
    public List<App> searchByAppname(String appname);
}
