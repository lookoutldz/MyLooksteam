package looko.looksteam.demo.crawler;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    专门处理请求返回的网页
 */
public class HTMLs {


    public static String saveToString(HttpURLConnection con){

        if(null != con) {

            int len;
            byte[] buffer = new byte[4096];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                InputStream is = con.getInputStream();
                if (is.available()>0)
                    while((len = is.read(buffer)) != -1){
                        bos.write(buffer, 0, len);
                    }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("begin:" + bos.toString());
            return bos.toString();
        }
        System.out.println("save to string fail, con = null");
        return null;
    }



    public static List<String> catch_1(String str1){

        List<String> results = new ArrayList<>();
        String regex = "(?<=<div class=\"screenshot_holder\">)[a-z0-9A-Z_./:?&\\ \t\n\r\"<>=-]*?(?=</div>)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str1);
        while (matcher.find()){
            //System.out.println(matcher.group(0));
            results.add(matcher.group(0));
        }
        //System.out.println("catch_1 over");
        return results;
    }

    public static List<String> catch_2(List<String> str2){

        List<String> results = new ArrayList<>();
        for (String str : str2) {

            String regex = "(?<=url=)[a-z0-9A-Z_./:?&\\ \t\n\r\"<>=-]*?(?=\")";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()){
                //System.out.println(matcher.group(0));
                results.add(matcher.group(0));
            }
        }
        //System.out.println("catch_2 over");
        return results;
    }

}
