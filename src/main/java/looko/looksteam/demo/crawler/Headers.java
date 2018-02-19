package looko.looksteam.demo.crawler;

import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
    专门处理请求头的类
 */
public class Headers {

    public static String getCookies(HttpURLConnection con){

        String cookies = "";
        String cookieVal;
        String key;
        for (int i = 1; (key = con.getHeaderFieldKey(i)) != null; i++ ) {
            if (key.equalsIgnoreCase("set-cookie")) {
                cookieVal = con.getHeaderField(i);
                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
                cookies = cookies + cookieVal + ";";
            }
        }
        return cookies;
    }

    public static String getSessionId(String cookies){

        String regex = "(?<=sessionid=)\\w{24}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cookies);
        if (matcher.find()){
            return matcher.group(0);
        }
        return null;
    }

    public static String getSessionId(HttpURLConnection con){

        String setCookie = con.getHeaderField("Set-Cookie");
        String regex = "(?<=sessionid=)\\w{24}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(setCookie);
        if (matcher.find()){
            return matcher.group(0);
        }
        return null;
    }

    public static void showResponseHeaders(HttpURLConnection con){

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("RequestURL = " + con.getURL());
        for (int i = 1; con.getHeaderFieldKey(i) != null; i++ ) {
            System.out.println(con.getHeaderFieldKey(i) + " = " + con.getHeaderField(i));

        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static String getReferer(HttpURLConnection con){

        if (con != null)
            return con.getHeaderField("Referer");
        else{
            System.out.println("In getReferer, con = null");
            return null;
        }
    }

    public static String getLocation(HttpURLConnection con){

        if (con != null)
            return con.getHeaderField("Location");
        else{
            System.out.println("In getLocation, con = null");
            return null;
        }
    }

}
