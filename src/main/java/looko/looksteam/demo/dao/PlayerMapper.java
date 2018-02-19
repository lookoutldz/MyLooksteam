package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.Player;

public interface PlayerMapper {
    int deleteByPrimaryKey(String steamid);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(String steamid);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);
}