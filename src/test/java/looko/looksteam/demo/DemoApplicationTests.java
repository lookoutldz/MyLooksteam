package looko.looksteam.demo;

import looko.looksteam.demo.api.GetAppList;
import looko.looksteam.demo.api.GetOwnedGame;
import looko.looksteam.demo.api.GetSteamLevel;
import looko.looksteam.demo.api.GetPlayerSummaries;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.MyGame;
import looko.looksteam.demo.entity.OwnedGame;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.service.OwnedgameService;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
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
	private OwnedgameService ownedgameService;

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
	public void test_log4j2 (){
	}

}
