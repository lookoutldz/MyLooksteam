package looko.looksteam.demo.tool;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetNowTime
{
	/**
	   * 获取现在时间，存入数据库用
	   * 
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	   */

	public static String getAsString()
	{
		Calendar nowDay = new GregorianCalendar();
        int year = nowDay.get(Calendar.YEAR);  
        int month = nowDay.get(Calendar.MONTH)   +   1;    
        int day = nowDay.get(Calendar.DAY_OF_MONTH);    
        int hourOfDay = nowDay.get(Calendar.HOUR_OF_DAY);//24小时制    
        //int hour = todaysDate.get(Calendar.HOUR);          //12小时制    
        int minute = nowDay.get(Calendar.MINUTE);    
        int second = nowDay.get(Calendar.SECOND); 
        return year + "-" + month + "-" + day + " " + hourOfDay + ":" + minute + ":" +second;
	}
}
