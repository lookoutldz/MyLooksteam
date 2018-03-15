package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMapper {

    int deleteByPrimaryKey(String steamid);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(String steamid);

    List<Player> selectAll();

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);
}