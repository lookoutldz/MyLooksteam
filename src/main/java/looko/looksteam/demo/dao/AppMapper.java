package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.App;

public interface AppMapper {
    int deleteByPrimaryKey(Integer appid);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appid);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}