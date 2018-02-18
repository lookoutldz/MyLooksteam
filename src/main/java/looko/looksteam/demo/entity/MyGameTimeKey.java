package looko.looksteam.demo.entity;

public class MyGameTimeKey {
    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public Integer getPlaytime2week() {
        return playtime2week;
    }

    public void setPlaytime2week(Integer playtime2week) {
        this.playtime2week = playtime2week;
    }

    public Integer getPlaytimeForever() {
        return playtimeForever;
    }

    public void setPlaytimeForever(Integer playtimeForever) {
        this.playtimeForever = playtimeForever;
    }

    private String steamid;
    private Integer playtime2week;
    private Integer playtimeForever;
}
