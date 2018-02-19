package looko.looksteam.demo.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectManager {

    private HttpURLConnection con;
    private int responseCode;

    public List<String> go(String urlstr){
        try
        {
            String location = urlstr;
            con = new ToGet().sentGET(location, null);
            responseCode = con.getResponseCode();
            System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
            if (302 == responseCode) {
                location = Headers.getLocation(con);

                String regex = "(?<=.com/)\\w{8}(?=/app)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(location);
                System.out.println(location);
                if (matcher.find()){
                    System.out.println("way2"+matcher.group(0));
                    return new Connector().sentRequest_2(urlstr);
                }
                else {
                    System.out.println("way3");
                    return new Connector().sentRequest_3(urlstr);
                }
            }
            else if (200 == responseCode){
                System.out.println("way1");
                return new Connector().sentRequest_1(urlstr);
            }
            else{
                System.out.println("ConManager : return ");
                return null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
