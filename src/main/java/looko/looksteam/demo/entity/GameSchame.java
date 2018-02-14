package looko.looksteam.demo.entity;

public class GameSchame extends GameSchameKey {
    private Integer defaultvalue;

    private String displayname;

    private Integer hidden;

    private String icon;

    private String icongray;

    private String updatetime;

    private String extraVarchar;

    private Integer extraInt;

    public Integer getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(Integer defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname == null ? null : displayname.trim();
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getIcongray() {
        return icongray;
    }

    public void setIcongray(String icongray) {
        this.icongray = icongray == null ? null : icongray.trim();
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