package looko.looksteam.demo.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class ToGet {

    public HttpURLConnection sentGET(String location, String cookies){

        try
        {
            if ((location != null) && !location.equals("")){
                URL url = new URL(location);
                InetSocketAddress addr = new InetSocketAddress("127.0.0.1",1080);
                Proxy proxy = new Proxy(Proxy.Type.HTTP,addr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(20000);
                con.setReadTimeout(20000);
                con.setUseCaches(false);
                con.setInstanceFollowRedirects(false);
                con.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");

                if ((cookies != null) && !cookies.equals(""))
                    con.setRequestProperty("Cookie", cookies);

                con.setRequestMethod("GET");
                con.connect();

                return con;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
