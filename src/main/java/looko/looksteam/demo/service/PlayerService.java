package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.Player;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PlayerService {

    int updatePlayer(String steamid);

    Player selectPlayer(String steamid);

    List<Player> selectAllPlayers();
}
