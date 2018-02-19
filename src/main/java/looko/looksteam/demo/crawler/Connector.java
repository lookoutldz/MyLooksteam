package looko.looksteam.demo.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;

public class Connector {

    public List<String> sentRequest_1(String urlstr){
        try
        {
            HttpURLConnection con;
            int responseCode;
            con = new ToGet().sentGET(urlstr, null);
            responseCode = con.getResponseCode();
            System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
            if (200 == responseCode){
                //执行爬取操作
                List<String> strings =  HTMLs.catch_2(HTMLs.catch_1(HTMLs.saveToString(con)));
                con.disconnect();
                return strings;
            }
            else {
                con.disconnect();
                System.out.println("return");
                return null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> sentRequest_2(String urlstr) {

        String location = urlstr;
        try
        {
            HttpURLConnection con;
            int responseCode;
            String cookies;
            String sessionid;

            con = new ToGet().sentGET(location, null);
            responseCode = con.getResponseCode();
            System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
            if (302 == responseCode){
                location = Headers.getLocation(con);

                con = new ToGet().sentGET(location,null);
                responseCode = con.getResponseCode();
                System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
                if (200 == responseCode){
                    //获得cookies（添加到Cookie）和sessionid（添加到表单），设置连接参数并连接，将表单写到POST输出流中
                    cookies = Headers.getCookies(con);
                    cookies += "timezoneOffset=28800,0";//不加也没事，最好加上
                    sessionid = Headers.getSessionId(con);

                    String content = URLEncoder.encode("snr","UTF-8") + "=" + URLEncoder.encode("1_agecheck_agecheck__age-gate","UTF-8");
                    content += "&" + URLEncoder.encode("sessionid","UTF-8") + "=" + URLEncoder.encode(sessionid,"UTF-8");
                    content += "&" + URLEncoder.encode("ageDay","UTF-8") + "=" + URLEncoder.encode("1","UTF-8");
                    content += "&" + URLEncoder.encode("ageMonth","UTF-8") + "=" + URLEncoder.encode("January","UTF-8");
                    content += "&" + URLEncoder.encode("ageYear","UTF-8") + "=" + URLEncoder.encode("1993","UTF-8");

                    con = new ToPost().sentPOST(location, cookies, content);
                    responseCode = con.getResponseCode();
                    System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
                    if (302 == responseCode){
                        //处理302重定向,保存302重定向地址，以及Cookies,然后GET重新发送请求(模拟请求)
                        location = con.getHeaderField("Location");
                        cookies += ";birthtime=725817601;lastagecheckage=1-January-1993";

                        con = new ToGet().sentGET(location, cookies);
                        responseCode = con.getResponseCode();
                        System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
                        if(200 == responseCode){
                            //执行爬取操作
                            List<String> strings =  HTMLs.catch_2(HTMLs.catch_1(HTMLs.saveToString(con)));
                            con.disconnect();
                            return strings;
                        }
                        else{
                            con.disconnect();
                            System.out.println("return 4");
                            return null;
                        }
                    }
                    else {
                        con.disconnect();
                        System.out.println("return 3");
                        return null;
                    }
                }
                else {
                    con.disconnect();
                    System.out.println("return 2");
                    return null;
                }
            }
            else {
                con.disconnect();
                System.out.println("return 1");
                return null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> sentRequest_3(String urlstr){

        String location = urlstr;
        try
        {
            HttpURLConnection con;
            int responseCode;
            String cookies;

            con = new ToGet().sentGET(location, null);
            responseCode = con.getResponseCode();
            System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
            if (302 == responseCode){
                location = Headers.getLocation(con);
                cookies = Headers.getCookies(con);

                con = new ToGet().sentGET(location,null);
                responseCode = con.getResponseCode();
                System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
                if (200 == responseCode){
                    String sessionid = Headers.getSessionId(con);
                    cookies += "sessionid=" + sessionid + ";timezoneOffset=28800,0;mature_content=1";
                    location = urlstr;
                    con = new ToGet().sentGET(location,cookies);
                    responseCode = con.getResponseCode();
                    System.out.println(responseCode + "---" + con.getRequestMethod() + " : " + con.getURL());
                    if (200 == responseCode){
                        //执行爬取操作
                        List<String> strings =  HTMLs.catch_2(HTMLs.catch_1(HTMLs.saveToString(con)));
                        con.disconnect();
                        return strings;
                    }
                    else {
                        con.disconnect();
                        System.out.println("return 3");
                        return null;
                    }
                }
                else{
                    con.disconnect();
                    System.out.println("return 2");
                    return null;
                }
            }
            else{
                con.disconnect();
                System.out.println("return 1");
                return null;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
