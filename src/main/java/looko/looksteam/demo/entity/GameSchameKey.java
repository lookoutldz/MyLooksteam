package looko.looksteam.demo.entity;

public class GameSchameKey {
    private Integer appid;

    private String achname;

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getAchname() {
        return achname;
    }

    public void setAchname(String achname) {
        this.achname = achname == null ? null : achname.trim();
    }
}