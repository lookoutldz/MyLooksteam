package looko.looksteam.demo.serviceImpl;

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
    public Player showPlayers(String steamid) {
        return playerMapper.selectByPrimaryKey(steamid);
    }
}