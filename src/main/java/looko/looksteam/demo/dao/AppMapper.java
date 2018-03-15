package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.App;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppMapper {
    int deleteByPrimaryKey(Integer appid);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer appid);

    List<App> selectByAppname(String appnamae);

    List<App> selectEqualsAppname(String appname);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}