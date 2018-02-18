package looko.looksteam.demo.entity;

public class FriendKey {
    private String steamid;

    private String friendsteamid;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid == null ? null : steamid.trim();
    }

    public String getFriendsteamid() {
        return friendsteamid;
    }

    public void setFriendsteamid(String friendsteamid) {
        this.friendsteamid = friendsteamid == null ? null : friendsteamid.trim();
    }
}