package looko.looksteam.demo.serviceImpl;

import looko.looksteam.demo.api.GetPlayerSummaries;
import looko.looksteam.demo.dao.PlayerMapper;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PlayerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public int updatePlayer(String steamid) {
        int row = 0;
        Player player = new GetPlayerSummaries().getAsPlayer(steamid);
        if (null != player){
            if (null != playerMapper.selectByPrimaryKey(player.getSteamid()))
                row += playerMapper.updateByPrimaryKey(player);
            else
                row += playerMapper.insert(player);
        }
        return row;
    }

    @Override
    public Player selectPlayer(String steamid) {

        return playerMapper.selectByPrimaryKey(steamid);
    }
}
