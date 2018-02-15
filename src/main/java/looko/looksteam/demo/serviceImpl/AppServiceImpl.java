package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.dao.AppMapper;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.service.AppService;
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
    public App searchByID(int appid) {
        return appMapper.selectByPrimaryKey(appid);
    }

    @Override
    public List<App> searchByName(String appname) {
        return null;
    }
}
