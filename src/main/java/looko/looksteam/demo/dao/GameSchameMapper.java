package looko.looksteam.demo.dao;

import looko.looksteam.demo.entity.GameSchame;
import looko.looksteam.demo.entity.GameSchameKey;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSchameMapper {
    int deleteByPrimaryKey(GameSchameKey key);

    int insert(GameSchame record);

    int insertSelective(GameSchame record);

    GameSchame selectByPrimaryKey(GameSchameKey key);

    int updateByPrimaryKeySelective(GameSchame record);

    int updateByPrimaryKey(GameSchame record);
}