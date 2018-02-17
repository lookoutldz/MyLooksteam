package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.App;

import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(Integer appid);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appid);

    List<App> selectByAppname(String appname);

    List<App> selectByChname(String chname);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}