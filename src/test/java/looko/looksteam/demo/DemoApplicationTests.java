package looko.looksteam.demo;

import looko.looksteam.demo.api.*;
import looko.looksteam.demo.controller.threads.UpdateAchManager;
import looko.looksteam.demo.crawler.LaunchCrawler;
import looko.looksteam.demo.dao.FriendMapper;
import looko.looksteam.demo.entity.*;
import looko.looksteam.demo.service.*;
import looko.looksteam.demo.tool.GetNowTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

	@Autowired
	private PlayerachService playerachService;

	@Test
	public void contextLoads() {

		System.out.println("contextLoads-start");
	}

	/*
	@Test
	public void test_appUpdate(){

		System.out.println(appService.updateApps(new GetAppList().getAsApps()));
	}

	@Test
	public void test_appSearch () {

		System.out.println("start");
		long time1 = System.currentTimeMillis();
		List<App> apps = appService.searchByAppname("ys");
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

		playerService.updatePlayer("76561198367830998");
	}

	@Test
	public void test_updateOwnedgame(){

		System.out.println(ownedgameService.updateOwnedgames("76561198267348529"));
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

		System.out.println(friendService.updateFriends("76561198367830998"));

//		List<Friend> friends = friendService.getMyFriendsByTime("76561198328486894");
//		for (Friend friend : friends){
//			System.out.println(friend.getFriendsteamid()+" : "+friend.getFriendSince());
//		}
	}

	@Test
	public void test_crawler(){
		List<String> strings = new LaunchCrawler().getPicLinks(220);
		if (strings != null)
		    for (String str : strings) {
                System.out.println(str);
            }
        else{
            System.out.println("strings = null");
        }
	}

	@Test
	public void test_updateAppAndAppPic(){

		//System.out.println(appService.updateApps(new GetAppList().getAsApps()));
//		List<OwnedGame> ownedGames = ownedgameService.getOwnedgames_all("76561198267348529");
//		System.out.println(appService.updateAppsPic(ownedGames));
		List<OwnedGame> ownedGames2 = ownedgameService.getOwnedgames_all("76561198267348529");
		System.out.println(appService.updateAppsPic(ownedGames2));


	}

	@Test
	public void test_getRecentlyPlayedGames(){

		List<OwnedGame> recentlyGames = new GetRecentlyPlayedGames().getAsOwnedgames("76561198267348529",10);
		for (OwnedGame game : recentlyGames){
			System.out.println(game.getAppname()+" : "+game.getPlaytime2week()+"h/"+game.getPlaytimeForever()+"h");
			System.out.println(game.getImgIconUrl());
			System.out.println(game.getImgLogoUrl());
			System.out.println();
		}
	}

	@Test
	public void test_selectEqualsAppname(){
		List<App> apps = appService.searchEqualsAppname("Syberia");
		System.out.println("size = " + apps.size());
		for (App app : apps){
			System.out.println(app.getAppid() + " : " + app.getAppname());
		}
	}

	@Test
	public void test_API_getAchievement(){
		GetPlayerAchievements getAch = new GetPlayerAchievements();
		List<PlayerAch> playerAches = getAch.getAsPlayerAch("76561198367830998",533300);
		if (playerAches != null && playerAches.size() > 0)
			for (PlayerAch playerAch : playerAches){

				System.out.println(playerAch.getAchname());
			}
	}

	@Test
	public void test_getAchievements(){

		System.out.println(playerachService.updateAchievements("76561198367830998"));
	}

	@Test
	public void test_getPlayerAchForGame(){

		List<PlayerAch> playerAches = playerachService.getPlayersAchForGame("76561198367830998",533300);
		if (playerAches != null && playerAches.size() > 0){
			for (PlayerAch playerAch : playerAches){
				System.out.println(playerAch.getAppid()+"---"+playerAch.getAchname()+" : "+playerAch.getAchieved());
			}
		}
	}

	@Test
	public void test_getPerfectGame(){

		List<App> apps;
		apps = playerachService.getPerfectGame("76561198367830998");
		if (apps != null && apps.size() > 0){
			int i = 0;
			for (App app : apps){
				System.out.println((i+1)+" : "+app.getAppid()+" : "+app.getAppname());
				i++;
			}
		}
	}

	@Test
	public void test_countHasAchGame(){

		System.out.println(playerachService.countHasAchGame("76561198367830998"));
	}

	@Test
	public void test_countUnlockAch(){

		System.out.println(playerachService.countUnlockAch("76561198367830998"));
	}

	@Test
	public void test_countPerfectGame(){

		System.out.println(playerachService.countPerfectGame("76561198367830998"));
	}

	@Test
	public void test_countGameAch(){

		System.out.println(playerachService.countAllAchByGame("76561198367830998",222880));
	}

	@Test
	public void test_countAchieved(){

		System.out.println(playerachService.countAchievedByGame("76561198367830998",222880));
	}

	@Test
	public void  test_UpdateAch(){

		UpdateAchManager updateAchManager = new UpdateAchManager();
	}

	*/
}
