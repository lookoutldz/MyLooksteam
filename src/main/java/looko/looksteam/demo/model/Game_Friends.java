package looko.looksteam.demo.model;

public class Game_Friends implements Comparable<Game_Friends> {

    private String steamid;
    private String avatar;
    private String personaname;
    private int play2week;
    private int playforever;
    private int achieved_count;
    private int achievement_all;

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public int getPlay2week() {
        return play2week;
    }

    public void setPlay2week(int play2week) {
        this.play2week = play2week;
    }

    public int getPlayforever() {
        return playforever;
    }

    public void setPlayforever(int playforever) {
        this.playforever = playforever;
    }

    public int getAchieved_count() {
        return achieved_count;
    }

    public void setAchieved_count(int achieved_count) {
        this.achieved_count = achieved_count;
    }

    public int getAchievement_all() {
        return achievement_all;
    }

    public void setAchievement_all(int achievement_all) {
        this.achievement_all = achievement_all;
    }

    @Override
    public int compareTo(Game_Friends o) {

        if (this.getPlayforever() < o.getPlayforever())
            return 1;
        else
            return -1;
    }
}
