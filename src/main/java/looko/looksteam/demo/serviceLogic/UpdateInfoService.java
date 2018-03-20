package looko.looksteam.demo.serviceLogic;

import looko.looksteam.demo.controller.threads.UpdateAppPicManager;
import looko.looksteam.demo.controller.threads.UpdateFriendsManager;
import looko.looksteam.demo.controller.threads.UpdatePlayer;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UpdateInfoService")
public class UpdateInfoService {

    @Autowired
    PlayerService playerService;

    @Autowired
    OwnedgameService ownedgameService;

    /*
        要从API获取的信息和获取顺序：
            1.用户个人信息player,用于更新DB（额外线程）
            2.用户游戏列表ownedgames,用于更新DB
            3.利用ownedgames里的列表项更新app表的logo,icon,pic1~pic5（额外线程）
            4.利用ownedgames里的appid更新playerach（额外线程）
            5.用户好友列表friends，用于更新DB（额外线程）
     */
    public void loginUpdate(String steamid){

        //如果player表为空,表示用户首次登入
        if (null == playerService.selectPlayer(steamid)){

            //更新个人信息
            UpdatePlayer updatePlayer = new UpdatePlayer();
            updatePlayer.setSteamid(steamid);
            updatePlayer.start();

            System.out.println("update his games:"+ownedgameService.updateOwnedgames(steamid));

            UpdateAppPicManager updateAppPicManager = new UpdateAppPicManager();
            updateAppPicManager.goUpdate(steamid);

            UpdateFriendsManager updateFriendsManager = new UpdateFriendsManager();
            updateFriendsManager.goUpdate(steamid);

        }
        else{
            System.out.println("update his games:"+ownedgameService.updateOwnedgames(steamid));

            UpdateAppPicManager updateAppPicManager = new UpdateAppPicManager();
            updateAppPicManager.goUpdate(steamid);

            UpdateFriendsManager updateFriendsManager = new UpdateFriendsManager();
            updateFriendsManager.goUpdate(steamid);
        }
    }
}
