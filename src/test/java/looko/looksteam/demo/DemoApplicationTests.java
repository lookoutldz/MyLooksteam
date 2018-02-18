package looko.looksteam.demo;

import looko.looksteam.demo.api.*;
import looko.looksteam.demo.dao.FriendMapper;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.Friend;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.FriendService;
import looko.looksteam.demo.service.OwnedgameService;
import looko.looksteam.demo.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private AppService appService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private OwnedgameService ownedgameService;

	@Autowired
	private FriendService friendService;

	@Test
	public void contextLoads() {
		System.out.println("contextLoads-start");
	}

	@Test
	public void test_appUpdate(){
		System.out.println(appService.updateApps(new GetAppList().getAsApps()));
	}

	@Test
	public void test_appSearch () {

		System.out.println("start");
		long time1 = System.currentTimeMillis();
		List<App> apps = appService.searchByAppname("%final fantasy%");
		if (apps.size() > 0){
			for (App app : apps){
				System.out.println(app.getAppid() + " : " + app.getAppname());
			}
		}
		else {
			System.out.println("apps = null");
		}
	}

	@Test
	public void test_api_GetPlayerSummaries(){

		//76561198367830998
		Player player = new GetPlayerSummaries().getAsPlayer("76561198367830998");
		System.out.println(player.getPersonaname());
		System.out.println(player.getRealname());
		System.out.println(player.getSteamid());
		System.out.println(player.getProfileurl());
		System.out.println(player.getTimecreated());
		System.out.println(new GetSteamLevel().getAsInt("76561198367830998"));
	}

	@Test
	public void test_updatePlayer(){
		playerService.updatePlayer(new GetPlayerSummaries().getAsPlayer("76561198367830998"));
	}

	@Test
	public void test_updateOwnedgame(){
		List<OwnedGame> ownedGames = new GetOwnedGame().getAsOwnedGames("76561198367830998");
		System.out.println(ownedgameService.updateOwnedgames(ownedGames));
	}

	@Test
	public void test_selectOwnedgameBySteamid(){

		List<OwnedGame> ownedGames = ownedgameService.searchMyGames("76561198367830998","");
		if (ownedGames.size() > 0){
			for (OwnedGame ownedGame : ownedGames){
				System.out.println(ownedGame.getAppid() + " : " + ownedGame.getAppname());
			}
		}
	}

	@Test
	public void test_selectFavorite(){
		List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_favorite("76561198367830998");
		if (ownedGames.size() > 0){
			for (OwnedGame ownedGame : ownedGames){
				System.out.println(ownedGame.getAppid() + " : " + ownedGame.getAppname());
			}
		}
	}

	@Test
	public void test_selectNotPlay(){
		List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_notplay("76561198367830998");
		if (ownedGames.size() > 0){
			for (OwnedGame ownedGame : ownedGames){
				System.out.println(ownedGame.getAppid() + " : " + ownedGame.getAppname());
			}
		}
	}

	@Test
	public void test_updateFriend (){
		//System.out.println(friendService.updateFriends(new GetFriendList().getAsFriends("76561198367830998")));
		List<Friend> friends = friendService.getMyFriendsByTime("76561198367830998");
		for (Friend friend : friends){
			System.out.println(friend.getFriendsteamid()+" : "+friend.getFriendSince());
		}
	}

}
