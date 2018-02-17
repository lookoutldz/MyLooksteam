package looko.looksteam.demo.webConnect;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGet {

    public InputStream getAsStream(String urlstr){

        if (urlstr == null || urlstr.equals("")){
            System.out.println("urlstr = null");
        }
        else {
            try
            {
                URL url = new URL(urlstr);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(10000);
                con.setReadTimeout(10000);
                con.setRequestMethod("GET");
                con.connect();
                int responseCode;
                if ((responseCode = con.getResponseCode()) != 200){
                    System.out.println("responseCode = " + responseCode);
                    return null;
                }
                return con.getInputStream();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}