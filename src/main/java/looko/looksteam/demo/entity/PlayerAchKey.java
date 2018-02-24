package looko.looksteam.demo.entity;

public class PlayerAchKey {
    private String steamid;

    private Integer appid;

    private String achname;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid == null ? null : steamid.trim();
    }

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