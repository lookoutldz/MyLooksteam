package looko.looksteam.demo.entity;

public class PlayerAch extends PlayerAchKey {
    private Integer achieved;

    private Integer unlocktime;

    private String updatetime;

    private String extraVarchar;

    private Integer extraInt;

    public Integer getAchieved() {
        return achieved;
    }

    public void setAchieved(Integer achieved) {
        this.achieved = achieved;
    }

    public Integer getUnlocktime() {
        return unlocktime;
    }

    public void setUnlocktime(Integer unlocktime) {
        this.unlocktime = unlocktime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getExtraVarchar() {
        return extraVarchar;
    }

    public void setExtraVarchar(String extraVarchar) {
        this.extraVarchar = extraVarchar == null ? null : extraVarchar.trim();
    }

    public Integer getExtraInt() {
        return extraInt;
    }

    public void setExtraInt(Integer extraInt) {
        this.extraInt = extraInt;
    }
}