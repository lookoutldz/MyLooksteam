package looko.looksteam.demo.entity;

public class PlayerAchKey {
    private String steamid;

    private String achname;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid == null ? null : steamid.trim();
    }

    public String getAchname() {
        return achname;
    }

    public void setAchname(String achname) {
        this.achname = achname == null ? null : achname.trim();
    }
}