package looko.looksteam.demo;

import looko.looksteam.demo.api.GetAppList;
import looko.looksteam.demo.service.AppService;
import looko.looksteam.demo.tool.ApplicationContextHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("looko.looksteam.demo.dao")
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		AppService initialService = ApplicationContextHelper.getBean(AppService.class);
		System.out.println("update apps : " + initialService.updateApps(new GetAppList().getAsApps()));

	}
}
