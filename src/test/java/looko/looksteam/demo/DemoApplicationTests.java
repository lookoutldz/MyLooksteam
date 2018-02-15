package looko.looksteam.demo;

import looko.looksteam.demo.api.aSteamApps.GetAppList;
import looko.looksteam.demo.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
		System.out.println(appService.searchByID(524220).getAppname());
	}

}
