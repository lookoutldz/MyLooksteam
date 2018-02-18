package looko.looksteam.demo.service;

import looko.looksteam.demo.entity.Player;
import org.springframework.stereotype.Service;

public interface PlayerService {

    int updatePlayer(Player player);

    Player selectPlayer(String steamid);
}
