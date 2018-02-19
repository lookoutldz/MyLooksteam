package looko.looksteam.demo.crawler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ToPost {

    public HttpURLConnection sentPOST(String location, String cookies, String content){

        try
        {
            if ((location != null) && !location.equals("")){

                URL url = new URL(location);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(20000);
                con.setReadTimeout(20000);
                con.setUseCaches(false);
                con.setInstanceFollowRedirects(false);
                con.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestProperty("Cookie", cookies);
                
                con.setRequestMethod("POST");
                con.connect();
                if ((content != null) && !content.equals("")){
                    OutputStream os = con.getOutputStream();
                    os.write(content.getBytes());
                    os.flush();
                    os.close();
                }

                return con;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
