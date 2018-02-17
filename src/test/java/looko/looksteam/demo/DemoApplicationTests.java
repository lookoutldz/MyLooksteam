package looko.looksteam.demo;

import looko.looksteam.demo.api.GetSteamLevel;
import looko.looksteam.demo.api.GetPlayerSummaries;
import looko.looksteam.demo.entity.App;
import looko.looksteam.demo.entity.Player;
import looko.looksteam.demo.service.AppService;
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

	@Test
	public void contextLoads() {
		System.out.println("contextLoads-start");
	}

	@Test
	public void test_appService () {
		//System.out.println("i = " + appService.updateApps(new GetAppList().getAsApps()));
		//System.out.println(appService.searchByID(524220).getAppname());
		System.out.println("start");
		List<App> apps = appService.searchByAppname("%final fantasy%");
		if (apps != null){
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

		//76561198328486894
		//76561198367830998
		Player player = new GetPlayerSummaries().getAsPlayer("76561198328486894");
		System.out.println(player.getPersonaname());
		System.out.println(player.getRealname());
		System.out.println(player.getSteamid());
		System.out.println(player.getProfileurl());
		System.out.println(player.getTimecreated());
		System.out.println(new GetSteamLevel().getAsInt("76561198328486894"));
	}

	@Test
	public void test_log4j2 (){
	}

}
